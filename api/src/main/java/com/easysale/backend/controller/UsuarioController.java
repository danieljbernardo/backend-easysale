package com.easysale.backend.controller;

import com.easysale.backend.domain.venda.BuscarVendaDTO;
import com.easysale.backend.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/easysale/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/gerar-relatorio")
    public ResponseEntity gerarRelatorioVenda(@RequestBody @Valid BuscarVendaDTO buscarVendaDTO){
        return this.usuarioService.gerarRelatorioVenda(buscarVendaDTO);
    }

    @DeleteMapping("/excluir-usuario")
    public ResponseEntity excluirUsuario(@AuthenticationPrincipal UserDetails usuario){
        return this.usuarioService.excluindoUsuario(usuario);
    }
}
