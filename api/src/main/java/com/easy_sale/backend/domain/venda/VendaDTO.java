package com.easy_sale.backend.domain.venda;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

public record VendaDTO (

        @NotNull
        @NotBlank
        @CPF
        String cpf,

        @NotNull
        @NotBlank
        String formaPagamento,

        @NotNull
        @NotBlank
        String produtos,

        @NotNull
        @NotBlank
        String quantidades

){
}
