package com.exemplo.meuprojeto.problema2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class SistemaIngressosTest {

    @Test
    void testPercentualIngressosVIPNoLimiteInferior() {
        int totalIngressos = 1000;
        int percentualVIP = 20;
        int quantidadeVIP = (totalIngressos * percentualVIP) / 100;
        assertEquals(200, quantidadeVIP, "Erro: Percentual de ingressos VIP no limite inferior.");
    }

    @Test
    void testPercentualIngressosVIPNoLimiteSuperior() {
        int totalIngressos = 1000;
        int percentualVIP = 30;
        int quantidadeVIP = (totalIngressos * percentualVIP) / 100;
        assertEquals(300, quantidadeVIP, "Erro: Percentual de ingressos VIP no limite superior.");
    }

    @Test
    void testPercentualIngressosVIPAbaixoDoLimite() {
        int totalIngressos = 1000;
        int percentualVIP = 19;
        int quantidadeVIP = (totalIngressos * percentualVIP) / 100;
        assertTrue(quantidadeVIP < 200, "Erro: Percentual de ingressos VIP abaixo do limite inferior.");
    }

    @Test
    void testPercentualIngressosVIPAcimaDoLimite() {
        int totalIngressos = 1000;
        int percentualVIP = 31;
        assertThrows(IllegalArgumentException.class, () -> {
            int quantidadeVIP = (totalIngressos * percentualVIP) / 100;
            if (quantidadeVIP > 300) {
                throw new IllegalArgumentException("Erro: Percentual de ingressos VIP acima do limite superior.");
            }
        });
    }

    @Test
    void testPercentualIngressosMEIAENTRADANoLimiteInferior() {
        int totalIngressos = 1000;
        int percentualMEIA_ENTRADA = 10;
        int quantidadeMEIA_ENTRADA = (totalIngressos * percentualMEIA_ENTRADA) / 100;
        assertEquals(100, quantidadeMEIA_ENTRADA, "Erro: Percentual de ingressos MEIA_ENTRADA no limite inferior.");
    }

    @Test
    void testPercentualIngressosMEIAENTRADABaixoDoLimite() {
        int totalIngressos = 1000;
        int percentualMEIA_ENTRADA = 9;
        int quantidadeMEIA_ENTRADA = (totalIngressos * percentualMEIA_ENTRADA) / 100;
        assertTrue(quantidadeMEIA_ENTRADA < 100, "Erro: Percentual de ingressos MEIA_ENTRADA abaixo do limite.");
    }

    @Test
    void testPercentualIngressosMEIAENTRADAAcimaDoLimite() {
        int totalIngressos = 1000;
        int percentualMEIA_ENTRADA = 11;
        int quantidadeMEIA_ENTRADA = (totalIngressos * percentualMEIA_ENTRADA) / 100;
        assertTrue(quantidadeMEIA_ENTRADA > 100, "Erro: Percentual de ingressos MEIA_ENTRADA acima do limite.");
    }

    @Test
    void testDistribuicaoCorretaIngressos() {
        int totalIngressos = 1000;
        int quantidadeVIP = (totalIngressos * 20) / 100;
        int quantidadeMEIA_ENTRADA = (totalIngressos * 10) / 100;
        int quantidadeNORMAL = totalIngressos - (quantidadeVIP + quantidadeMEIA_ENTRADA);
        assertEquals(200, quantidadeVIP, "Erro na distribuição de ingressos VIP.");
        assertEquals(100, quantidadeMEIA_ENTRADA, "Erro na distribuição de ingressos MEIA_ENTRADA.");
        assertEquals(700, quantidadeNORMAL, "Erro na distribuição de ingressos NORMAL.");
    }

    @Test
    void testDescontoAplicadoNoLimiteSuperiorVIP() {
        double precoVIP = 200;
        double desconto = 25;
        double precoFinalVIP = precoVIP - (precoVIP * desconto / 100);
        assertEquals(150, precoFinalVIP, "Erro: Desconto aplicado no limite superior para VIP.");
    }

    @Test
    void testDescontoAplicadoNoLimiteSuperiorNORMAL() {
        double precoNormal = 100;
        double desconto = 25;
        double precoFinalNormal = precoNormal - (precoNormal * desconto / 100);
        assertEquals(75, precoFinalNormal, "Erro: Desconto aplicado no limite superior para NORMAL.");
    }


    @Test
    void testReceitaLiquidaComShowEmDataNormal() {
        double precoIngressosVendidos = 130000;
        double despesasInfraestrutura = 50000;
        double cache = 30000;
        double receitaLiquida = precoIngressosVendidos - despesasInfraestrutura - cache;
        assertEquals(50000, receitaLiquida, "Erro: Receita líquida com show em data normal.");
        assertEquals("LUCRO", receitaLiquida > 0 ? "LUCRO" : receitaLiquida == 0 ? "ESTÁVEL" : "PREJUÍZO", "Status financeiro incorreto.");
    }

    @Test
    void testReceitaLiquidaComShowEmDataEspecial() {
        double precoIngressosVendidos = 130000;
        double despesasInfraestrutura = 50000 * 1.15; // adicional de 15%
        double cache = 30000;
        double receitaLiquida = precoIngressosVendidos - despesasInfraestrutura - cache;
        assertEquals(42500, receitaLiquida, "Erro: Receita líquida com show em data especial.");
        assertEquals("LUCRO", receitaLiquida > 0 ? "LUCRO" : receitaLiquida == 0 ? "ESTÁVEL" : "PREJUÍZO", "Status financeiro incorreto.");
    }

    @Test
    void testReceitaLiquidaResultandoEmPrejuizo() {
        double precoIngressosVendidos = 80000;
        double despesasInfraestrutura = 50000;
        double cache = 40000;
        double receitaLiquida = precoIngressosVendidos - despesasInfraestrutura - cache;
        assertEquals(-10000, receitaLiquida, "Erro: Receita líquida resultando em prejuízo.");
        assertEquals("PREJUÍZO", receitaLiquida < 0 ? "PREJUÍZO" : receitaLiquida == 0 ? "ESTÁVEL" : "LUCRO", "Status financeiro incorreto.");
    }

    @Test
    void testReceitaLiquidaEstavel() {
        double precoIngressosVendidos = 80000;
        double despesasInfraestrutura = 40000;
        double cache = 40000;
        double receitaLiquida = precoIngressosVendidos - despesasInfraestrutura - cache;
        assertEquals(0, receitaLiquida, "Erro: Receita líquida estável.");
        assertEquals("ESTÁVEL", receitaLiquida == 0 ? "ESTÁVEL" : receitaLiquida > 0 ? "LUCRO" : "PREJUÍZO", "Status financeiro incorreto.");
    }
}
