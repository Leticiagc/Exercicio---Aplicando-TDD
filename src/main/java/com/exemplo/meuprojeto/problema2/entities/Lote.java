package com.exemplo.meuprojeto.problema2.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.exemplo.meuprojeto.problema2.types.TipoIngresso;

@Entity
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private List<Ingresso> ingressos;
    private double descontoIngresso;
    private double preco;
    private Map<TipoIngresso, Integer> ingressosVendidos;

    public Lote(Long id, List<Ingresso> ingressos, double descontoIngresso, double preco) {
        this.id = id;
        this.ingressos = ingressos;
        this.descontoIngresso = descontoIngresso;
        this.preco = preco;
        this.ingressosVendidos = new HashMap<>();
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

    public void setDesconto(double descontoIngresso) {
        this.descontoIngresso = descontoIngresso;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void aplicarDesconto() {
        for (Ingresso ingresso : ingressos) {
            if (ingresso.getTipo() == TipoIngresso.NORMAL || ingresso.getTipo() == TipoIngresso.VIP) {
                ingresso.setPreco(ingresso.getPreco() * (1 - descontoIngresso / 100));
            }
        }
    }

    public void venderIngresso(int quantidade, TipoIngresso tipo) {
        int vendidos = 0;
        for (Ingresso ingresso : ingressos) {
            if (!ingresso.foiVendido() && ingresso.getTipo() == tipo && vendidos < quantidade) {
                ingresso.setVendido(true);
                vendidos++;
                ingressosVendidos.put(tipo, ingressosVendidos.getOrDefault(tipo, 0) + 1);
            }
        }
    }

    public int getQtdVendida(TipoIngresso tipoIngresso) {
        return ingressosVendidos.getOrDefault(tipoIngresso, 0);
    }

    public double calcularReceita() {
        double receita = 0.0;
        for (Ingresso ingresso : ingressos) {
            if (ingresso.foiVendido()) {
                receita += ingresso.getPreco();
            }
        }
        return receita;
    }
}
