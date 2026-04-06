package com.easy_sale.backend.service;

import com.easy_sale.backend.domain.produto.Produto;
import com.easy_sale.backend.domain.venda.Venda;
import com.easy_sale.backend.domain.venda.itemVenda.CriarItemVendaDTO;
import com.easy_sale.backend.domain.venda.itemVenda.DeletarItemVendaDTO;
import com.easy_sale.backend.domain.venda.itemVenda.EditarItemVendaDTO;
import com.easy_sale.backend.domain.venda.itemVenda.ItemVenda;
import com.easy_sale.backend.repository.ItemVendaRepository;
import com.easy_sale.backend.repository.ProdutoRepository;
import com.easy_sale.backend.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ItemVendaService {

    @Autowired
    ItemVendaRepository itemVendaRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    VendaRepository vendaRepository;

    public ResponseEntity criandoItemVenda(CriarItemVendaDTO criarItemVendaDTO){
        Venda venda=this.vendaRepository.findByIdVenda(criarItemVendaDTO.vendaId());
        Produto produto=this.produtoRepository.findByCodigo(criarItemVendaDTO.produtoCodigo());

        if(this.itemVendaRepository.findByVendaProduto(venda,produto)!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Já existe um item correspondente nesta venda");
        }

        ItemVenda itemVenda=new ItemVenda(venda, produto, criarItemVendaDTO.quantidade(),
                criarItemVendaDTO.precoUnitario(), criarItemVendaDTO.subtotal());

        return ResponseEntity.ok().build();
    }

    public ResponseEntity deletandoItemVenda(DeletarItemVendaDTO deletarItemVendaDTO){
        if(deletarItemVendaDTO.deletar()){
            itemVendaRepository.delete(this.itemVendaRepository.findByIdItemVenda(deletarItemVendaDTO.itemVendaId()));
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível deletar ste item da venda");
    }

    public ResponseEntity editandoItemVenda(EditarItemVendaDTO editarItemVendaDTO){
        ItemVenda itemVenda=this.itemVendaRepository.findByIdItemVenda(editarItemVendaDTO.itemVendaId());

        if(editarItemVendaDTO.produtoCodigo()!=null){
            itemVenda.setProduto(this.produtoRepository.findByCodigo(editarItemVendaDTO.produtoCodigo()));
            itemVenda.setPrecoUnitario(this.produtoRepository.findByCodigo(editarItemVendaDTO.produtoCodigo()).getPreco());
            BigDecimal qntdBigDecimal=new BigDecimal(itemVenda.getQuantidade());
            itemVenda.setSubtototal(itemVenda.getPrecoUnitario().multiply(qntdBigDecimal));
        }
        if(editarItemVendaDTO.quantidade()!=null){
            itemVenda.setQuantidade(editarItemVendaDTO.quantidade());
            BigDecimal qntdBigDecimal=new BigDecimal(itemVenda.getQuantidade());
            itemVenda.setSubtototal(itemVenda.getPrecoUnitario().multiply(qntdBigDecimal));
        }
        itemVendaRepository.save(itemVenda);
        return ResponseEntity.ok().build();
    }
}
