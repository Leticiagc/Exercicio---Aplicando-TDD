package com.exemplo.meuprojeto.problema1.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.exemplo.meuprojeto.problema1.types.TipoDoPagamento;

public class Fatura {
    private LocalDate data;
    private double valorTotal;
    private String nomeCliente;
    private List<Pagamento> pagamentos;

    public Fatura(LocalDate data, double valorTotal, String nomeCliente) {
        this.data = data;
        this.valorTotal = valorTotal;
        this.nomeCliente = nomeCliente;
        this.pagamentos = new ArrayList<>();
    }

    public void addPayment(Pagamento pagamento) {
        if (pagamento.getTipo().equals(TipoDoPagamento.BOLETO)) {
            if (pagamento.getValor() < 0.01 || pagamento.getValor() > 5000.00) {
                System.out.println("Pagamento por boleto inválido: " + pagamento.getValor());
                return;
            }
            if (pagamento.getData().isAfter(this.data)) {
                pagamento.setValor(pagamento.getValor() * 1.1);
            }
        }
        this.pagamentos.add(pagamento);
    }

    public String getStatus() {
        double totalPago = 0.0;
        for (Pagamento pagamento : pagamentos) {
            if (pagamento.getTipo().equals(TipoDoPagamento.CARTAO_DE_CREDITO) && 
                pagamento.getData().plusDays(15).isBefore(this.data)) {
                totalPago += pagamento.getValor();
            } else if (!pagamento.getTipo().equals(TipoDoPagamento.CARTAO_DE_CREDITO) && 
                        pagamento.getData().isBefore(this.data.plusDays(1))) {
                totalPago += pagamento.getValor();
            }
        }
        return totalPago >= this.valorTotal ? "PAGA" : "PENDENTE";
    }

    public LocalDate getData() {
        return data;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }
}