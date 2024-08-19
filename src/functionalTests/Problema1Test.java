import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.exemplo.meuprojeto.problema1.entities.Fatura;
import com.exemplo.meuprojeto.problema1.entities.Pagamento;
import com.exemplo.meuprojeto.problema1.types.TipoDoPagamento;

public class Problema1Test {
    //Técnica tabela de decisão


    @Test
    public void testFaturaEmDiaComBoleto() {
        Fatura fatura = new Fatura(LocalDate.of(2023, 2, 20), 1500.00, "Cliente1");
        Pagamento pagamento1 = new Pagamento(500.00, LocalDate.of(2023, 2, 20), TipoDoPagamento.BOLETO);
        Pagamento pagamento2 = new Pagamento(1000.00, LocalDate.of(2023, 2, 20), TipoDoPagamento.BOLETO);

        fatura.addPayment(pagamento1);
        fatura.addPayment(pagamento2);

        assertEquals("PAGA", fatura.getStatus());
    }

    @Test
    public void testPagamentoAtrasadoComBoleto() {
        Fatura fatura = new Fatura(LocalDate.of(2023, 2, 20), 1500.00, "Cliente2");
        Pagamento pagamento1 = new Pagamento(500.00, LocalDate.of(2023, 2, 21), TipoDoPagamento.BOLETO);
        Pagamento pagamento2 = new Pagamento(1000.00, LocalDate.of(2023, 2, 21), TipoDoPagamento.BOLETO);

        fatura.addPayment(pagamento1);
        fatura.addPayment(pagamento2);

        assertEquals("PAGA", fatura.getStatus());
    }

    @Test
    public void testPagamentoAtrasadoSemAcrescimo() {
        Fatura fatura = new Fatura(LocalDate.of(2023, 2, 20), 2000.00, "Cliente3");
        Pagamento pagamento1 = new Pagamento(500.00, LocalDate.of(2023, 2, 21), TipoDoPagamento.BOLETO);
        Pagamento pagamento2 = new Pagamento(1000.00, LocalDate.of(2023, 2, 21), TipoDoPagamento.BOLETO);

        fatura.addPayment(pagamento1);
        fatura.addPayment(pagamento2);

        assertEquals("PENDENTE", fatura.getStatus());
    }

    @Test
    public void testPagamentoComCartaoDeCreditoEmDia() {
        Fatura fatura = new Fatura(LocalDate.of(2023, 2, 20), 1500.00, "Cliente4");
        Pagamento pagamento = new Pagamento(1500.00, LocalDate.of(2023, 2, 5), TipoDoPagamento.CARTAO_DE_CREDITO);

        fatura.addPayment(pagamento);

        assertEquals("PAGA", fatura.getStatus());
    }

    @Test
    public void testPagamentoComCartaoDeCreditoEmAtraso() {
        Fatura fatura = new Fatura(LocalDate.of(2023, 2, 20), 1500.00, "Cliente5");
        Pagamento pagamento = new Pagamento(1500.00, LocalDate.of(2023, 2, 10), TipoDoPagamento.CARTAO_DE_CREDITO);

        fatura.addPayment(pagamento);

        assertEquals("PENDENTE", fatura.getStatus());
    }

    @Test
    public void testPagamentoComTransfBancariaEmDia() {
        Fatura fatura = new Fatura(LocalDate.of(2023, 2, 20), 1500.00, "Cliente6");
        Pagamento pagamento = new Pagamento(1500.00, LocalDate.of(2023, 2, 17), TipoDoPagamento.TRANSFERENCIA_BANCARIA);

        fatura.addPayment(pagamento);

        assertEquals("PAGA", fatura.getStatus());
    }

    @Test
    public void testPagamentoComTransfBancariaInsuficiente() {
        Fatura fatura = new Fatura(LocalDate.of(2023, 2, 20), 1500.00, "Cliente7");
        Pagamento pagamento = new Pagamento(1000.00, LocalDate.of(2023, 2, 17), TipoDoPagamento.TRANSFERENCIA_BANCARIA);

        fatura.addPayment(pagamento);

        assertEquals("PENDENTE", fatura.getStatus());
    }

    // Valor invalido
    @Test
    public void testValorDePagamentoInvalido() {
        Fatura fatura = new Fatura(LocalDate.of(2023, 2, 20), 1500.00, "Cliente8");
        Pagamento pagamento = new Pagamento(0.005, LocalDate.of(2023, 2, 20), TipoDoPagamento.BOLETO);

        fatura.addPayment(pagamento);

        assertEquals("PENDENTE", fatura.getStatus());
    }

    // Cartão de credito invalido com menos de 15 dias
    @Test
    public void testPagamentoPorBoletoECreditoAtrasado() {
        Fatura fatura = new Fatura(LocalDate.of(2023, 2, 20), 1500.00, "Cliente9");
        Pagamento pagamento1 = new Pagamento(700.00, LocalDate.of(2023, 2, 5), TipoDoPagamento.CARTAO_DE_CREDITO);
        Pagamento pagamento2 = new Pagamento(800.00, LocalDate.of(2023, 2, 17), TipoDoPagamento.TRANSFERENCIA_BANCARIA);
        Pagamento pagamento3 = new Pagamento(700.00, LocalDate.of(2023, 2, 25), TipoDoPagamento.BOLETO);

        fatura.addPayment(pagamento1);
        fatura.addPayment(pagamento2);
        fatura.addPayment(pagamento3);

        assertEquals("PENDENTE", fatura.getStatus());
    }
}
