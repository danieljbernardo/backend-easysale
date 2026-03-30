package com.easy_sale.backend.service;

import com.easy_sale.backend.domain.venda.NotaFiscal;
import com.easy_sale.backend.domain.venda.Venda;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;

@Service
public class GerarPdfService {

    public byte[] gerarPdfNotaFiscal(NotaFiscal notaFiscal, Venda venda) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();

            // Cabeçalho
            Paragraph cabecalho = new Paragraph("NOTA FISCAL DE VENDA",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18));
            cabecalho.setAlignment(Element.ALIGN_CENTER);
            document.add(cabecalho);
            document.add(Chunk.NEWLINE);

            // Número da Nota Fiscal
            Paragraph numNF = new Paragraph("Número: " + notaFiscal.getId(),
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
            document.add(numNF);
            document.add(Chunk.NEWLINE);

            // Dados do Cliente
            document.add(new Paragraph("DADOS DO CLIENTE",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));

            PdfPTable tableCliente = new PdfPTable(2);
            tableCliente.setWidthPercentage(100);
            tableCliente.addCell("Nome:");
            tableCliente.addCell(venda.getCliente().getNome());
            tableCliente.addCell("CPF:");
            tableCliente.addCell(venda.getCliente().getCpf());
            tableCliente.addCell("Telefone:");
            tableCliente.addCell(venda.getCliente().getTelefone());
            document.add(tableCliente);
            document.add(Chunk.NEWLINE);

            // Dados da Venda
            document.add(new Paragraph("DADOS DA VENDA",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));

            PdfPTable tableVenda = new PdfPTable(2);
            tableVenda.setWidthPercentage(100);
            tableVenda.addCell("Data:");
            tableVenda.addCell(new Date().toString());
            tableVenda.addCell("Forma Pagamento:");
            tableVenda.addCell(venda.getPagamento().getFormaPagamento().toString());
            document.add(tableVenda);
            document.add(Chunk.NEWLINE);

            // Itens da Venda
            document.add(new Paragraph("ITENS DA VENDA",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));

            PdfPTable tableItens = new PdfPTable(4);
            tableItens.setWidthPercentage(100);
            tableItens.setWidths(new float[]{3, 1, 1, 1});

            // Cabeçalho da tabela
            tableItens.addCell("Produto");
            tableItens.addCell("Quantidade");
            tableItens.addCell("Valor Unitário");
            tableItens.addCell("Subtotal");

            // Itens
            double valorTotal = 0;
            for (ItemVenda item : venda.getItens()) {
                tableItens.addCell(item.getProduto().getNome());
                tableItens.addCell(item.getQuantidade().toString());
                tableItens.addCell(String.format("R$ %.2f", item.getPrecoUnitario()));
                double subtotal = item.getQuantidade() * item.getPrecoUnitario();
                tableItens.addCell(String.format("R$ %.2f", subtotal));
                valorTotal += subtotal;
            }

            document.add(tableItens);
            document.add(Chunk.NEWLINE);

            // Total
            Paragraph total = new Paragraph(String.format("VALOR TOTAL: R$ %.2f", valorTotal),
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
            total.setAlignment(Element.ALIGN_RIGHT);
            document.add(total);

            document.close();
            return baos.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar PDF da nota fiscal", e);
        }
    }
}
