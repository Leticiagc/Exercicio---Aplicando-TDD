package com.exemplo.meuprojeto.problema1;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.exemplo.meuprojeto.problema1.entities.Conta;
import com.exemplo.meuprojeto.problema1.entities.Fatura;
import com.exemplo.meuprojeto.problema1.entities.ProcessadorDeContas;


public class ProcessadorDeContasTest {

    @Test
    public void testInvoicePaid() {
        Fatura fatura = new Fatura(LocalDate.of(2023, 2, 20), 1500.00, "Client A");
        Conta conta1 = new Conta("001", LocalDate.of(2023, 2, 20), 500.00);
        Conta conta2 = new Conta("002", LocalDate.of(2023, 2, 20), 400.00);
        Conta conta3 = new Conta("003", LocalDate.of(2023, 2, 20), 600.00);

        ProcessadorDeContas processador = new ProcessadorDeContas();
        processador.process(Arrays.asList(conta1, conta2, conta3), fatura);

        assertEquals("PAGA", fatura.getStatus());
    }

    @Test
    public void testInvoicePendente() {
        Fatura fatura = new Fatura(LocalDate.of(2023, 2, 20), 1500.00, "Client B");
        Conta conta1 = new Conta("001", LocalDate.of(2023, 2, 20), 700.00);
        Conta conta2 = new Conta("002", LocalDate.of(2023, 2, 20), 800.00);

        ProcessadorDeContas processador = new ProcessadorDeContas();
        processador.process(Arrays.asList(conta1, conta2), fatura);

        assertEquals("PAGA", fatura.getStatus());
    }

    @Test
    public void testBoletoValorInvalido() {
        Fatura fatura = new Fatura(LocalDate.of(2023, 2, 20), 1500.00, "Client C");
        Conta conta1 = new Conta("001", LocalDate.of(2023, 2, 20), 5000.01);

        ProcessadorDeContas processador = new ProcessadorDeContas();
        processador.process(Arrays.asList(conta1), fatura);

        assertTrue(fatura.getPagamentos().isEmpty());
        assertEquals("PENDENTE", fatura.getStatus());
    }

    @Test
    public void testBoletoWithAtraso() {
        Fatura fatura = new Fatura(LocalDate.of(2023, 2, 20), 1500.00, "Client D");
        Conta conta1 = new Conta("001", LocalDate.of(2023, 2, 21), 500.00);

        ProcessadorDeContas processador = new ProcessadorDeContas();
        processador.process(Arrays.asList(conta1), fatura);

        assertEquals(550.00, fatura.getPagamentos().get(0).getValor(), 0.01);
        assertEquals("PENDENTE", fatura.getStatus());
    }

    @Test
    public void testBoletoValorInferior() {
        Fatura fatura = new Fatura(LocalDate.of(2023, 2, 20), 1500.00, "Client E");
        Conta conta1 = new Conta("001", LocalDate.of(2023, 2, 20), 0.005);

        ProcessadorDeContas processador = new ProcessadorDeContas();
        processador.process(Arrays.asList(conta1), fatura);

        assertTrue(fatura.getPagamentos().isEmpty());
        assertEquals("PENDENTE", fatura.getStatus());
    }
}
