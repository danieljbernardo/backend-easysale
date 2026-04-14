package com.easysale.backend.service;

import com.easysale.backend.domain.usuario.Usuario;
import com.easysale.backend.domain.venda.BuscarVendaDTO;
import com.easysale.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    PdfService pdfService;

    @Autowired
    UsuarioRepository usuarioRepository;

    public ResponseEntity gerarRelatorioVenda(BuscarVendaDTO buscarVendaDTO){
        byte[] relatorio=pdfService.gerarRelatorioVenda(buscarVendaDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment",
                "relatorio.pdf");

        return ResponseEntity.ok().headers(headers).body(relatorio);
    }

    public ResponseEntity excluindoUsuario(UserDetails usuario){
        this.usuarioRepository.delete((Usuario) usuario);
        return ResponseEntity.ok().build();
    }
}
