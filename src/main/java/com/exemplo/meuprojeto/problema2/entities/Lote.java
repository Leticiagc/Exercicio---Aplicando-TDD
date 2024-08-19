package com.exemplo.meuprojeto.problema2.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.exemplo.meuprojeto.problema2.types.TipoIngresso;

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
        if (descontoIngresso < 10.0 && ingressos.stream().anyMatch(i -> i.getTipo() == TipoIngresso.MEIA_ENTRADA)) {
            throw new IllegalArgumentException("Desconto para ingressos MEIA_ENTRADA deve ser pelo menos 10%");
        }

        if (descontoIngresso < 20.0 || descontoIngresso > 30.0) {
            if (ingressos.stream().anyMatch(i -> i.getTipo() == TipoIngresso.VIP)) {
                throw new IllegalArgumentException("Desconto para ingressos VIP deve estar entre 20% e 30%");
            }
        }

        for (Ingresso ingresso : ingressos) {
            if (ingresso.getTipo() == TipoIngresso.VIP) {
                ingresso.setPreco(preco * 2); // VIP custa o dobro do Normal
            } else if (ingresso.getTipo() == TipoIngresso.MEIA_ENTRADA) {
                ingresso.setPreco(preco * 0.5); // MEIA_ENTRADA custa a metade do Normal
            } else if (ingresso.getTipo() == TipoIngresso.NORMAL) {
                ingresso.setPreco(preco); // Normal mantém o preço base
            }
        }

        for (Ingresso ingresso : ingressos) {
            ingresso.setPreco(ingresso.getPreco() * (1 - descontoIngresso / 100));
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
