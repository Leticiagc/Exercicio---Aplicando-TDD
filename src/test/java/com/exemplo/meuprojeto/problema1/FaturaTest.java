package com.exemplo.meuprojeto.problema2;

import org.junit.jupiter.api.Test;
import com.exemplo.meuprojeto.problema1.entities.Fatura;
import com.exemplo.meuprojeto.problema1.entities.Pagamento;
import com.exemplo.meuprojeto.problema1.types.TipoDoPagamento;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class FaturaTest {

    @Test
    public void testAddPagamentoConcluido() {
        Fatura fatura = new Fatura(LocalDate.of(2023, 2, 20), 1500.00, "Cliente1");
        Pagamento pagamento = new Pagamento(1500.00, LocalDate.of(2023, 2, 20), TipoDoPagamento.BOLETO);

        fatura.addPayment(pagamento);

        assertEquals("PAGA", fatura.getStatus());
    }

    @Test
    public void testAddPagamentoCreditoTransf() {
        Fatura fatura = new Fatura(LocalDate.of(2023, 2, 20), 1500.00, "Cliente2");
        Pagamento pagamento1 = new Pagamento(700.00, LocalDate.of(2023, 2, 5), TipoDoPagamento.CARTAO_DE_CREDITO);
        Pagamento pagamento2 = new Pagamento(800.00, LocalDate.of(2023, 2, 17), TipoDoPagamento.TRANSFERENCIA_BANCARIA);

        fatura.addPayment(pagamento1);
        fatura.addPayment(pagamento2);

        assertEquals("PENDENTE", fatura.getStatus());
    }

    @Test
    public void testAddPagamentoPendente() {
        Fatura fatura = new Fatura(LocalDate.of(2023, 2, 20), 1500.00, "Cliente3");
        Pagamento pagamento1 = new Pagamento(700.00, LocalDate.of(2023, 2, 6), TipoDoPagamento.CARTAO_DE_CREDITO);
        Pagamento pagamento2 = new Pagamento(800.00, LocalDate.of(2023, 2, 25), TipoDoPagamento.TRANSFERENCIA_BANCARIA);

        fatura.addPayment(pagamento1);
        fatura.addPayment(pagamento2);

        assertEquals("PENDENTE", fatura.getStatus());
    }
}