package com.easy_sale.backend.service;

import com.easy_sale.backend.domain.produto.EditarProdutoDTO;
import com.easy_sale.backend.domain.produto.Produto;
import com.easy_sale.backend.domain.produto.ProdutoDTO;
import com.easy_sale.backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    public ResponseEntity cadastrandoProduto(ProdutoDTO produtoDTO){
        if(produtoRepository.findByCodigo(produtoDTO.codigo())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Produto já cadastrado no sistema");
        }
        Produto produto=new Produto(produtoDTO.nome(), produtoDTO.codigo(), produtoDTO.preco(),produtoDTO.descricao());
        produtoRepository.save(produto);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity deletandoProduto(Long codigo){
        if(produtoRepository.findByCodigo(codigo)==null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("O Produto já não existe no sistema");
        }
        Produto produto=produtoRepository.findByCodigo(codigo);
        produtoRepository.delete(produto);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity buscandoProduto(Long codigo){
        if(produtoRepository.findByCodigo(codigo)==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado no sistema");
        }
        Produto produto=produtoRepository.findByCodigo(codigo);
        return ResponseEntity.ok().body(new ProdutoDTO(produto.getNome(),produto.getPreco(),
                produto.getCodigo(), produto.getDescricao()));
    }

    public ResponseEntity editandoProduto(EditarProdutoDTO editarProdutoDTO) {
        if (this.produtoRepository.findByCodigo(editarProdutoDTO.codigo()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Produto já existente no sistema");
        } else if (editarProdutoDTO.nome().isEmpty() || editarProdutoDTO.nome().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nome inválido");
        } else if (editarProdutoDTO.descricao().isEmpty() || editarProdutoDTO.descricao().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Descrição inválida");
        }
        Produto produto = new Produto();
        produto.setNome(editarProdutoDTO.nome());
        produto.setDescricao(editarProdutoDTO.descricao());
        produto.setCodigo(editarProdutoDTO.codigo());
        produto.setPreco(editarProdutoDTO.preco());

        this.produtoRepository.save(produto);
        return ResponseEntity.ok().build();

    }
}
