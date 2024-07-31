package com.exemplo.meuprojeto.show.entities;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private List<Ingresso> ingressos;
    private double descontoIngresso;


    public Lote(Long id, List<Ingresso> ingressos, double descontoIngresso) {
        this.id = id;
        this.ingressos = ingressos;
        this.descontoIngresso = descontoIngresso;
    }

    public Long getId() { 
        return id; 
    }
    public void setId(Long id) { 
        this.id = id; 
    }

    public List<Ingresso> getIngressos() { 
        return ingressos; 
    }

    public void setIngressos(List<Ingresso> ingressos) { 
        this.ingressos = ingressos; 
    }

    public double getDesconto() { 
        return descontoIngresso; 
    }
    public void setDesconto(double desconto) { this.descontoIngresso = desconto; }






    
}
