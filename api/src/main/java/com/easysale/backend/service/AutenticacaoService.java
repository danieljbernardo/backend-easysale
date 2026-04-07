package com.easysale.backend.service;

import com.easy_sale.backend.domain.usuario.*;
import com.easysale.backend.domain.usuario.*;
import com.easysale.backend.infra.security.TokenService;
import com.easysale.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AutenticacaoService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    TokenService tokenService;

    public ResponseEntity fazendoLogin(AutenticacaoDTO autenticacaoDTO){
        var emailSenha=new UsernamePasswordAuthenticationToken(autenticacaoDTO.email(), autenticacaoDTO.senha());
        var autenticacao=this.authenticationManager.authenticate(emailSenha);

        Usuario usuario=(Usuario) autenticacao.getPrincipal();
        var token=this.tokenService.gerarToken(usuario);

        LoginDTO resposta = new LoginDTO(
                token,
                usuario.getRole().name()
        );

        return ResponseEntity.ok().body(resposta);
    }

    public ResponseEntity fazendoCadastro(UsuarioDTO usuarioDTO){
        if(this.usuarioRepository.findByEmail(usuarioDTO.email())!=null) return ResponseEntity.badRequest().build();

        String senhaCriptografada=new BCryptPasswordEncoder().encode(usuarioDTO.senha());

        UsuarioRole usuarioRole=UsuarioRole.valueOf(usuarioDTO.role().toUpperCase());

        Usuario usuario=new Usuario(usuarioDTO.nome(), usuarioDTO.email(), senhaCriptografada, usuarioDTO.cpf(), usuarioRole);
        this.usuarioRepository.save(usuario);

        return ResponseEntity.ok().build();
    }

}
