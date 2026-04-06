package com.easy_sale.backend.controller;

import com.easy_sale.backend.domain.produto.CodigoDTO;
import com.easy_sale.backend.domain.produto.EditarProdutoDTO;
import com.easy_sale.backend.domain.produto.ProdutoDTO;
import com.easy_sale.backend.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/easysale/produto")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @PostMapping("/cadastrar-produto")
    public ResponseEntity cadastrarProduto(@RequestBody @Valid ProdutoDTO produtoDTO){
        return this.produtoService.cadastrandoProduto(produtoDTO);
    }

    @DeleteMapping("/deletar-produto")
    public ResponseEntity deletarProduto(@RequestBody @Valid CodigoDTO codigoDTO){
        return this.produtoService.deletandoProduto(codigoDTO.codigo());
    }

    @GetMapping("/buscar-produto")
    public ResponseEntity buscarProduto(@RequestBody @Valid CodigoDTO codigoDTO){
        return this.produtoService.buscandoProduto(codigoDTO.codigo());
    }

    @PatchMapping("/editar-produto")
    public ResponseEntity editarProduto(@RequestBody @Valid EditarProdutoDTO editarProdutoDTO){
        return this.produtoService.editandoProduto(editarProdutoDTO);
    }
}
