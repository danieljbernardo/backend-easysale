package com.easy_sale.backend.controller;

import com.easy_sale.backend.domain.CpfDTO;
import com.easy_sale.backend.domain.cliente.ClienteDTO;
import com.easy_sale.backend.domain.cliente.EditarClienteDTO;
import com.easy_sale.backend.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @PostMapping("/cadastrar-cliente")
    public ResponseEntity cadastrarCliente(@RequestBody @Valid ClienteDTO clienteDTO){
        return this.clienteService.cadastrandoCliente(clienteDTO);
    }

    @DeleteMapping("/deletar-cliente")
    public ResponseEntity deletarCliente(@RequestBody @Valid CpfDTO cpfDTO){
        return this.clienteService.deletandoCliente(cpfDTO.cpf());
    }

    @PostMapping("/buscar-cliente")
    public ResponseEntity buscarCliente(@RequestBody @Valid CpfDTO cpfDTO){
        return this.clienteService.buscandoCliente(cpfDTO.cpf());
    }

    @PatchMapping("/editar-cliente")
    public ResponseEntity editarCliente(@RequestBody @Valid EditarClienteDTO editarClienteDTO){
        return this.clienteService.editandoCliente(editarClienteDTO);
    }
}
