package com.easy_sale.backend.service;

import com.easy_sale.backend.domain.produto.Produto;
import com.easy_sale.backend.domain.venda.*;
import com.easy_sale.backend.domain.venda.pagamento.FormaPagamento;
import com.easy_sale.backend.domain.venda.pagamento.Pagamento;
import com.easy_sale.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    @Autowired
    VendaRepository vendaRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    ItemVendaRepository itemVendaRepository;

    @Autowired
    NotaFiscalRepository notaFiscalRepository;

    @Autowired
    GerarPdfService pdfService;

    public ResponseEntity<byte[]> cadastrandoVenda(VendaDTO vendaDTO) {

        Venda venda = new Venda();
        venda.setCliente(this.clienteRepository.findByCpf(vendaDTO.cpf()));
        FormaPagamento formaPagamento = FormaPagamento.valueOf(vendaDTO.formaPagamento().toUpperCase());
        Pagamento pagamento = new Pagamento(venda, formaPagamento);
        venda.setPagamento(pagamento);

        List<Long> produtos = Arrays.stream(vendaDTO.produtos().split(","))
                .map(Long::parseLong)
                .toList();
        List<Long> quantidades = Arrays.stream(vendaDTO.quantidades().split(","))
                .map(Long::parseLong)
                .toList();

        if (produtos.size() != quantidades.size()) {
            throw new IllegalArgumentException("Número de produtos não corresponde ao número de quantidades");
        }

        BigDecimal valorDaVenda = BigDecimal.ZERO;

        for (int i = 0; i < produtos.size(); i++) {
            Long codigoProduto = produtos.get(i);
            Long quantidade = quantidades.get(i);

            ItemVenda itemVenda = new ItemVenda();
            itemVenda.setVenda(venda);

            Produto produto = this.produtoRepository.findByCodigo(codigoProduto);
            itemVenda.setProduto(produto);
            itemVenda.setQuantidade(quantidade);
            itemVenda.setPrecoUnitario(produto.getPreco());

            BigDecimal quantidadeBigDecimal = new BigDecimal(quantidade);
            valorDaVenda = itemVenda.getPrecoUnitario().multiply(quantidadeBigDecimal);

            itemVendaRepository.save(itemVenda);
            venda.setValorTotal(valorDaVenda);
        }

        NotaFiscal notaFiscal = new NotaFiscal(venda.getValorTotal(), venda);
        venda.setNotaFiscal(notaFiscal);
        vendaRepository.save(venda);
        notaFiscalRepository.save(notaFiscal);

        byte[] pdfNotaFiscal = pdfService.gerarPdfNotaFiscal(notaFiscal, venda);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment",
                "nota_fiscal_" + notaFiscal.getNumeroNota() + ".pdf");

        return ResponseEntity.ok().headers(headers).body(pdfNotaFiscal);
    }

    public ResponseEntity deletandoVenda(Long id) {
        if (vendaRepository.findByIdVenda(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("Essa venda já não existe no sistema"));
        }
        Venda venda = vendaRepository.findByIdVenda(id);
        vendaRepository.delete(venda);
        return ResponseEntity.ok().build();
    }

}
