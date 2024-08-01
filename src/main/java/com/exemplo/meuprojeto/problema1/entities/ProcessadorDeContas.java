package com.exemplo.meuprojeto.problema1.entities;

import java.util.List;

import com.exemplo.meuprojeto.problema1.types.TipoDoPagamento;

public class ProcessadorDeContas {

    public void process(List<Conta> contas, Fatura fatura) {
        for (Conta conta : contas) {
            Pagamento pagamento = new Pagamento(conta.getValorPago(), conta.getData(), TipoDoPagamento.BOLETO); 
            try {
                fatura.addPayment(pagamento);
            } catch (IllegalArgumentException e) {
                System.out.println("Error to add payment: " + e.getMessage());
            }
        }
    }
}
