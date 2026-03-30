package com.easy_sale.backend.service.usuario;

import com.easy_sale.backend.domain.usuario.Usuario;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

@Service
public class UsuarioAutenticacaoService {

    public Usuario usuarioAutenticado(@AuthenticationPrincipal Usuario usuario){
        return usuario;
    }
}
