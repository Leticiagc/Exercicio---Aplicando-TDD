package com.exemplo.meuprojeto.problema2.entities;


import com.exemplo.meuprojeto.problema2.types.TipoIngresso;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Ingresso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TipoIngresso tipoIngresso;

    private Double preco;

    private boolean foiVendido;

    private TipoIngresso tipo;


    public Ingresso(Long id, TipoIngresso tipo, Double preco) {
        this.id = id;
        this.tipo = tipo;
        this.preco = preco;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public TipoIngresso getTipo() {
        return tipoIngresso;
    }

    public void setTipo(TipoIngresso tipo) {
        this.tipoIngresso = tipo;
    }

    public boolean foiVendido() {
        return foiVendido;
    }

    public void setVendido(boolean foiVendido) {
        this.foiVendido = foiVendido;
    }

    
}
