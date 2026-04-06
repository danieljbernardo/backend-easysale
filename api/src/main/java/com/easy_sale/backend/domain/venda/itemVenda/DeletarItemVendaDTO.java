package com.easy_sale.backend.domain.venda.itemVenda;

import jakarta.validation.constraints.NotNull;

public record DeletarItemVendaDTO (

        @NotNull
        Long itemVendaId,

        @NotNull
        boolean deletar

){
}
