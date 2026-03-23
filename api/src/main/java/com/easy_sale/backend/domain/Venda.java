package com.easy_sale.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "venda")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<ItemVenda> itensVendidos;

    @OneToOne(mappedBy = "venda")
    @JsonIgnore
    private NotaFiscal notaFiscal;

    private LocalDate dataVenda;

    public Venda(Cliente cliente, List<ItemVenda> itensVendidos, NotaFiscal notaFiscal, LocalDate dataVenda) {
        this.cliente = cliente;
        this.itensVendidos = itensVendidos;
        this.notaFiscal = notaFiscal;
        this.dataVenda = dataVenda;
    }

    public Venda() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public NotaFiscal getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(NotaFiscal nota_fiscal) {
        this.notaFiscal = nota_fiscal;
    }

    public List<ItemVenda> getItensVendidos() {
        return itensVendidos;
    }

    public void setItensVendidos(List<ItemVenda> itens_vendidos) {
        this.itensVendidos = itens_vendidos;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate data_venda) {
        this.dataVenda = data_venda;
    }
}
