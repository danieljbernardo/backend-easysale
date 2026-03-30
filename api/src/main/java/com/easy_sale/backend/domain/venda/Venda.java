package com.easy_sale.backend.domain.venda;

import com.easy_sale.backend.domain.cliente.Cliente;
import com.easy_sale.backend.domain.venda.pagamento.Pagamento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @OneToOne(mappedBy = "venda")
    @JsonIgnore
    private Pagamento pagamento;

    private BigDecimal valorTotal=BigDecimal.ZERO;

    private LocalDateTime dataVenda;

    @PrePersist
    public void salvaData(){
        this.dataVenda=LocalDateTime.now();

    }

    public Venda() {
    }

    public Venda(Cliente cliente, NotaFiscal notaFiscal, Pagamento pagamento, BigDecimal valorTotal) {
        this.cliente = cliente;
        this.notaFiscal = notaFiscal;
        this.pagamento = pagamento;
        this.valorTotal=valorTotal;
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

    public List<ItemVenda> getItensVendidos() {
        return itensVendidos;
    }

    public void setItensVendidos(List<ItemVenda> itensVendidos) {
        this.itensVendidos = itensVendidos;
    }

    public NotaFiscal getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(NotaFiscal notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public LocalDateTime getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDateTime dataVenda) {
        this.dataVenda = dataVenda;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

}
