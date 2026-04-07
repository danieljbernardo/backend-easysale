package com.easysale.backend.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO (

        @NotBlank
        String token,

        @NotBlank
        String role

){
}
