package com.easy_sale.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<ItemVenda> itensVendidos;

    private String nome;
    private BigDecimal preco;
    private String descricao;

    public Produto(String descricao, BigDecimal preco, String nome, List<ItemVenda> itensVendidos) {
        this.descricao = descricao;
        this.preco = preco;
        this.nome = nome;
        this.itensVendidos = itensVendidos;
    }

    public Produto() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ItemVenda> getItensVendidos() {
        return itensVendidos;
    }

    public void setItensVendidos(List<ItemVenda> itens_vendidos) {
        this.itensVendidos = itens_vendidos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
