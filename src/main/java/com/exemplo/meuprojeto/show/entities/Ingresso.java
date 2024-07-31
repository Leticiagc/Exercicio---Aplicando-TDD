package com.exemplo.meuprojeto.show.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import com.exemplo.meuprojeto.show.tipos.TipoIngresso;

@Entity
public class Ingresso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TipoIngresso tipoIngresso;

    private Double preco;

    private boolean foiVendido;

  
    public Ingresso(Long id, TipoIngresso tipoIngresso) {
        this.id = id;
        this.tipoIngresso = tipoIngresso;
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
