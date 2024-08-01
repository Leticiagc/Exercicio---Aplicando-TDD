package com.exemplo.meuprojeto.problema1.entities;

import java.time.LocalDate;

import com.exemplo.meuprojeto.problema1.types.TipoDoPagamento;

public class Pagamento {
    private double valor;
    private LocalDate data;
    private TipoDoPagamento tipo;

    public Pagamento(double valor, LocalDate data, TipoDoPagamento tipo) {
        this.valor = valor;
        this.data = data;
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public TipoDoPagamento getTipo() {
        return tipo;
    }
}
