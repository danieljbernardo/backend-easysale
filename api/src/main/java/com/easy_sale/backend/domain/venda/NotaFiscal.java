package com.easy_sale.backend.domain.venda;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroNota;

    @OneToOne
    @JoinColumn(name = "venda_id")
    private Venda venda;

    private BigDecimal valorTotal=BigDecimal.ZERO;
    private LocalDateTime dataEmissao;

    @PrePersist
    public void salvaData(){
        this.dataEmissao=LocalDateTime.now();

    }

    public NotaFiscal(BigDecimal valorTotal, Venda venda) {
        this.valorTotal = valorTotal;
        this.venda = venda;
    }

    public NotaFiscal() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Long getNumeroNota() {
        return numeroNota;
    }

    public void setNumeroNota(Long numeroNota) {
        this.numeroNota = numeroNota;
    }

    public LocalDateTime getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDateTime dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

}
