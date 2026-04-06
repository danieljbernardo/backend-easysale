package com.easy_sale.backend.controller;

import com.easy_sale.backend.domain.venda.itemVenda.CriarItemVendaDTO;
import com.easy_sale.backend.domain.venda.itemVenda.DeletarItemVendaDTO;
import com.easy_sale.backend.domain.venda.itemVenda.EditarItemVendaDTO;
import com.easy_sale.backend.service.ItemVendaService;
import com.easy_sale.backend.service.VendaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/easysale/item-venda")
public class ItemVendaController {

    @Autowired
    ItemVendaService itemVendaService;

    @PostMapping("/criar-item-venda")
    public ResponseEntity criarItemVenda(@RequestBody @Valid CriarItemVendaDTO criarItemVendaDTO){
        return itemVendaService.criandoItemVenda(criarItemVendaDTO);
    }

    @DeleteMapping("/deletar-item-venda")
    public ResponseEntity deletarItemVenda(@RequestBody @Valid DeletarItemVendaDTO deletarItemVendaDTO){
        return itemVendaService.deletandoItemVenda(deletarItemVendaDTO);
    }

    @PostMapping("/editar-item-venda")
    public ResponseEntity editarItemVenda(@RequestBody @Valid EditarItemVendaDTO editarItemVendaDTO){
        return itemVendaService.editandoItemVenda(editarItemVendaDTO);
    }
}
