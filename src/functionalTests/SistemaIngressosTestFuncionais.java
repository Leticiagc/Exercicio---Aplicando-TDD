package com.exemplo.meuprojeto.problema2;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.exemplo.meuprojeto.problema2.entities.Ingresso;
import com.exemplo.meuprojeto.problema2.entities.Lote;
import com.exemplo.meuprojeto.problema2.entities.Relatorio;
import com.exemplo.meuprojeto.problema2.entities.Show;
import com.exemplo.meuprojeto.problema2.types.StatusFinanceiro;
import com.exemplo.meuprojeto.problema2.types.TipoIngresso;

public class SistemaIngressosTestFuncionais {

    @Test
    public void testCT01() {

        Lote lote = new Lote(1L, Arrays.asList(new Ingresso(1L, TipoIngresso.VIP, 1000.0)), 20.0, 100.0);
        lote.aplicarDesconto();

        Assertions.assertEquals(800.0, lote.getIngressos().get(0).getPreco());
    }

    @Test
    public void testCT02() {

        Ingresso ingresso = new Ingresso(1L, TipoIngresso.MEIA_ENTRADA, 100.0);
        Lote lote = new Lote(1L, Arrays.asList(ingresso), 10.0, 100.0);
        lote.aplicarDesconto();

        Assertions.assertEquals(45.0, lote.getIngressos().get(0).getPreco());
    }

    @Test
    public void testCT03() {
        Lote lote = new Lote(1L, Arrays.asList(new Ingresso(1L, TipoIngresso.VIP, 200.0)), 19.0, 100.0);

        Assertions.assertThrows(IllegalArgumentException.class, lote::aplicarDesconto);
    }

    @Test
    public void testCT04() {

        Lote lote = new Lote(1L, Arrays.asList(new Ingresso(1L, TipoIngresso.VIP, 200.0)), 31.0, 100.0);

        Assertions.assertThrows(IllegalArgumentException.class, lote::aplicarDesconto);
    }

    @Test
    public void testCT05() {

        Ingresso ingresso = new Ingresso(1L, TipoIngresso.VIP, 200.0);
        Lote lote = new Lote(1L, Arrays.asList(ingresso), 25.0, 100.0);
        lote.aplicarDesconto();

        Assertions.assertEquals(150.0, lote.getIngressos().get(0).getPreco());
    }

    @Test
    public void testCT06() {
        Lote lote = new Lote(1L, Arrays.asList(new Ingresso(1L, TipoIngresso.MEIA_ENTRADA, 50.0)), 9.0, 100.0); // Desconto
                                                                                                                // de 9%
        Assertions.assertThrows(IllegalArgumentException.class, lote::aplicarDesconto);
    }

    @Test
    public void testCT07() {

        Lote loteVIP = new Lote(1L, Arrays.asList(new Ingresso(1L, TipoIngresso.VIP, 100.0)), 25.0, 100.0);
        Lote loteMeia = new Lote(2L, Arrays.asList(new Ingresso(2L, TipoIngresso.MEIA_ENTRADA, 50.0)), 10.0, 50.0);

        loteVIP.venderIngresso(500, TipoIngresso.VIP);
        loteMeia.venderIngresso(100, TipoIngresso.MEIA_ENTRADA);
        loteVIP.aplicarDesconto();
        loteMeia.aplicarDesconto();

        Map<Long, Lote> lotes = new HashMap<>();
        lotes.put(loteVIP.getId(), loteVIP);
        lotes.put(loteMeia.getId(), loteMeia);

        Show show = new Show(LocalDate.now(), "Artista", 3000.0, 300.0, lotes, false);
        Relatorio relatorio = show.gerarRelatorio();

        double receitaBruta = 500 * (100.0 * 2 * (1 - 25.0 / 100)) + 100 * (50.0 * 0.5 * (1 - 10.0 / 100));
        double despesasTotais = 3000.0 + 300.0;

        double receitaLiquida = receitaBruta - despesasTotais;

        Assertions.assertEquals(receitaLiquida, relatorio.getReceitaLiquida(), 0.01);
        Assertions.assertEquals(StatusFinanceiro.LUCRO, relatorio.getStatusFinanceiro());
    }

    @Test
    public void testCT08() {

        Lote loteVIP = new Lote(1L, Arrays.asList(new Ingresso(1L, TipoIngresso.VIP, 100.0)), 25.0, 100.0);
        Lote loteMeia = new Lote(2L, Arrays.asList(new Ingresso(2L, TipoIngresso.MEIA_ENTRADA, 50.0)), 10.0, 50.0);

        Map<Long, Lote> lotes = new HashMap<>();
        lotes.put(loteVIP.getId(), loteVIP);
        lotes.put(loteMeia.getId(), loteMeia);

        Show show = new Show(LocalDate.now(), "Artista", 3000.0, 300.0, lotes, false);

        loteVIP.aplicarDesconto();
        loteMeia.aplicarDesconto();

        Relatorio relatorio = show.gerarRelatorio();

        double receitaBruta = (100 * (100.0 * 2 * (1 - 0.15))) + (50 * (50.0 * 0.5 * (1 - 0.15)));
        double despesasTotais = 3000.0 + 300.0;

        Assertions.assertEquals(receitaBruta - despesasTotais, relatorio.getReceitaLiquida(), 0.01);
        Assertions.assertEquals(StatusFinanceiro.ESTAVEL, relatorio.getStatusFinanceiro());
    }

    @Test
    public void testCT09() {

        Lote loteVIP = new Lote(1L, Arrays.asList(new Ingresso(1L, TipoIngresso.VIP, 100.0)), 25.0, 100.0);
        Lote loteMeia = new Lote(2L, Arrays.asList(new Ingresso(2L, TipoIngresso.MEIA_ENTRADA, 50.0)), 10.0, 50.0);

        Map<Long, Lote> lotes = new HashMap<>();
        lotes.put(loteVIP.getId(), loteVIP);
        lotes.put(loteMeia.getId(), loteMeia);

        Show show = new Show(LocalDate.now(), "Artista", 3000.0, 300.0, lotes, false);

        loteVIP.aplicarDesconto();
        loteMeia.aplicarDesconto();

        Relatorio relatorio = show.gerarRelatorio();

        double receitaBruta = (100 * (100.0 * (1 - 0.15))) + (50 * (50.0 * (1 - 0.15)));
        double despesasTotais = 3000.0 + 300.0;

        Assertions.assertEquals(receitaBruta - despesasTotais, relatorio.getReceitaLiquida(), 0.01);
        Assertions.assertEquals(StatusFinanceiro.PREJUÍZO, relatorio.getStatusFinanceiro());
    }

    @Test
    public void testCT10() {

        Lote loteVIP = new Lote(1L, Arrays.asList(new Ingresso(1L, TipoIngresso.VIP, 100.0)), 25.0, 100.0);
        Lote loteMeia = new Lote(2L, Arrays.asList(new Ingresso(2L, TipoIngresso.MEIA_ENTRADA, 50.0)), 10.0, 50.0);

        Map<Long, Lote> lotes = new HashMap<>();
        lotes.put(loteVIP.getId(), loteVIP);
        lotes.put(loteMeia.getId(), loteMeia);

        Show show = new Show(LocalDate.now(), "Artista", 3000.0, 795.0, lotes, false);
        loteVIP.aplicarDesconto();
        loteMeia.aplicarDesconto();

        Relatorio relatorio = show.gerarRelatorio();

        double receitaBruta = (100 * (25.0 * 2 * (1 - 0.15))) + (50 * (10.0 * 0.5 * (1 - 0.15)));
        double despesasTotais = 3000.0 + 795.0;

        Assertions.assertEquals(receitaBruta - despesasTotais, relatorio.getReceitaLiquida(), 0.01);
        Assertions.assertEquals(StatusFinanceiro.LUCRO, relatorio.getStatusFinanceiro());
    }

    @Test
    public void testCT11() {

        Lote loteVIP = new Lote(1L, Arrays.asList(new Ingresso(1L, TipoIngresso.VIP, 25.0)), 25.0, 100.0);
        Lote loteMeia = new Lote(2L, Arrays.asList(new Ingresso(2L, TipoIngresso.MEIA_ENTRADA, 10.0)), 10.0, 50.0);

        Map<Long, Lote> lotes = new HashMap<>();
        lotes.put(loteVIP.getId(), loteVIP);
        lotes.put(loteMeia.getId(), loteMeia);

        Show show = new Show(LocalDate.now(), "Artista", 3000.0, 300.0, lotes, false);
        loteVIP.aplicarDesconto();
        loteMeia.aplicarDesconto();

        Relatorio relatorio = show.gerarRelatorio();

        double receitaBruta = (100 * (25.0 * 2 * (1 - 0.15))) + (50 * (10.0 * 0.5 * (1 - 0.15)));
        double despesasTotais = 3000.0 + 300.0;

        Assertions.assertEquals(receitaBruta - despesasTotais, relatorio.getReceitaLiquida(), 0.01);
        Assertions.assertEquals(StatusFinanceiro.LUCRO, relatorio.getStatusFinanceiro());
    }

    @Test
    public void testDecisionTable1() {

        Lote loteVIP = new Lote(1L, Arrays.asList(new Ingresso(1L, TipoIngresso.VIP, 50.0)), 500, 25.0);
        Map<Long, Lote> lotes = new HashMap<>();
        lotes.put(loteVIP.getId(), loteVIP);

        Show show = new Show(LocalDate.now(), "Artista", 3000.0, 500.0, lotes, true);
        loteVIP.aplicarDesconto();

        Relatorio relatorio = show.gerarRelatorio();

        double receitaBruta = 500 * (50.0 * (1 - 0.15));
        double despesasTotais = 3000.0 + 500.0;

        Assertions.assertEquals(receitaBruta - despesasTotais, relatorio.getReceitaLiquida(), 0.01);
        Assertions.assertEquals(StatusFinanceiro.PREJUÍZO, relatorio.getStatusFinanceiro());
    }

    @Test
    public void testDecisionTable2() {

        Lote loteMeia = new Lote(1L, Arrays.asList(new Ingresso(1L, TipoIngresso.MEIA_ENTRADA, 25.0)), 500, 25.0);
        Map<Long, Lote> lotes = new HashMap<>();
        lotes.put(loteMeia.getId(), loteMeia);

        Show show = new Show(LocalDate.now(), "Artista", 3000.0, 500.0, lotes, true);
        loteMeia.aplicarDesconto();

        Relatorio relatorio = show.gerarRelatorio();

        double receitaBruta = 500 * (25.0 * (1 - 0.25));
        double despesasTotais = 3000.0 + 500.0;

        Assertions.assertEquals(receitaBruta - despesasTotais, relatorio.getReceitaLiquida(), 0.01);
        Assertions.assertEquals(StatusFinanceiro.PREJUÍZO, relatorio.getStatusFinanceiro());
    }

    @Test
    public void testDecisionTable3() {

        Lote loteNormal = new Lote(1L, Arrays.asList(new Ingresso(1L, TipoIngresso.NORMAL, 25.0)), 500, 25.0);
        Map<Long, Lote> lotes = new HashMap<>();
        lotes.put(loteNormal.getId(), loteNormal);

        Show show = new Show(LocalDate.now(), "Artista", 3000.0, 500.0, lotes, true);

        Relatorio relatorio = show.gerarRelatorio();

        double receitaBruta = 500 * 25.0;
        double despesasTotais = 3000.0 + 500.0;

        Assertions.assertEquals(receitaBruta - despesasTotais, relatorio.getReceitaLiquida(), 0.01);
        Assertions.assertEquals(StatusFinanceiro.PREJUÍZO, relatorio.getStatusFinanceiro());
    }

    @Test
    public void testDecisionTable4() {

        Lote loteVIP = new Lote(1L, Arrays.asList(new Ingresso(1L, TipoIngresso.VIP, 50.0)), 500, 25.0);
        Map<Long, Lote> lotes = new HashMap<>();
        lotes.put(loteVIP.getId(), loteVIP);

        Show show = new Show(LocalDate.now(), "Artista", 3000.0, 300.0, lotes, false);
        loteVIP.aplicarDesconto();

        Relatorio relatorio = show.gerarRelatorio();

        double receitaBruta = 500 * (50.0 * (1 - 0.15));
        double despesasTotais = 3000.0 + 300.0;

        Assertions.assertEquals(receitaBruta - despesasTotais, relatorio.getReceitaLiquida(), 0.01);
        Assertions.assertEquals(StatusFinanceiro.PREJUÍZO, relatorio.getStatusFinanceiro());
    }

    @Test
    public void testDecisionTable5() {

        Lote loteMeia = new Lote(1L, Arrays.asList(new Ingresso(1L, TipoIngresso.MEIA_ENTRADA, 25.0)), 500, 25.0);
        Map<Long, Lote> lotes = new HashMap<>();
        lotes.put(loteMeia.getId(), loteMeia);

        Show show = new Show(LocalDate.now(), "Artista", 3000.0, 300.0, lotes, false);
        loteMeia.aplicarDesconto();

        Relatorio relatorio = show.gerarRelatorio();

        double receitaBruta = 500 * (25.0 * (1 - 0.25));
        double despesasTotais = 3000.0 + 300.0;

        Assertions.assertEquals(receitaBruta - despesasTotais, relatorio.getReceitaLiquida(), 0.01);
        Assertions.assertEquals(StatusFinanceiro.PREJUÍZO, relatorio.getStatusFinanceiro());
    }

    @Test
    public void testDecisionTable6() {

        Lote loteNormal = new Lote(1L, Arrays.asList(new Ingresso(1L, TipoIngresso.NORMAL, 25.0)), 500, 25.0);
        Map<Long, Lote> lotes = new HashMap<>();
        lotes.put(loteNormal.getId(), loteNormal);

        Show show = new Show(LocalDate.now(), "Artista", 3000.0, 300.0, lotes, false);

        Relatorio relatorio = show.gerarRelatorio();

        double receitaBruta = 500 * 25.0;
        double despesasTotais = 3000.0 + 300.0;

        Assertions.assertEquals(receitaBruta - despesasTotais, relatorio.getReceitaLiquida(), 0.01);
        Assertions.assertEquals(StatusFinanceiro.PREJUÍZO, relatorio.getStatusFinanceiro());
    }

    @Test
    public void testDecisionTable7() {
       
        Lote loteVIP = new Lote(1L, Arrays.asList(new Ingresso(1L, TipoIngresso.VIP, 50.0)), 500, 25.0);
        Map<Long, Lote> lotes = new HashMap<>();
        lotes.put(loteVIP.getId(), loteVIP);

        Show show = new Show(LocalDate.now(), "Artista", 3000.0, 500.0, lotes, true);

        Relatorio relatorio = show.gerarRelatorio();

        double receitaBruta = 500 * 50.0;
        double despesasTotais = 3000.0 + 500.0;

        Assertions.assertEquals(receitaBruta - despesasTotais, relatorio.getReceitaLiquida(), 0.01);
        Assertions.assertEquals(StatusFinanceiro.PREJUÍZO, relatorio.getStatusFinanceiro());
    }

    @Test
    public void testDecisionTable8() {
        Lote loteMeia = new Lote(1L, Arrays.asList(new Ingresso(1L, TipoIngresso.MEIA_ENTRADA, 25.0)), 500, 25.0);
        Map<Long, Lote> lotes = new HashMap<>();
        lotes.put(loteMeia.getId(), loteMeia);

        Show show = new Show(LocalDate.now(), "Artista", 3000.0, 300.0, lotes, false);
        loteMeia.aplicarDesconto();

        Relatorio relatorio = show.gerarRelatorio();

        double receitaBruta = 500 * (25.0 * (1 - 0.15));
        double despesasTotais = 3000.0 + 300.0;

        Assertions.assertEquals(receitaBruta - despesasTotais, relatorio.getReceitaLiquida(), 0.01);
        Assertions.assertEquals(StatusFinanceiro.PREJUÍZO, relatorio.getStatusFinanceiro());
    }

    @Test
    public void testDecisionTable9() {
        Lote loteNormal = new Lote(1L, Arrays.asList(new Ingresso(1L, TipoIngresso.NORMAL, 25.0)), 500, 25.0);
        Map<Long, Lote> lotes = new HashMap<>();
        lotes.put(loteNormal.getId(), loteNormal);

        Show show = new Show(LocalDate.now(), "Artista", 3000.0, 300.0, lotes, false);
        loteNormal.aplicarDesconto();

        Relatorio relatorio = show.gerarRelatorio();

        double receitaBruta = 500 * (25.0 * (1 - 0.25));
        double despesasTotais = 3000.0 + 300.0;

        Assertions.assertEquals(receitaBruta - despesasTotais, relatorio.getReceitaLiquida(), 0.01);
        Assertions.assertEquals(StatusFinanceiro.PREJUÍZO, relatorio.getStatusFinanceiro());
    }

    @Test
    public void testDecisionTable10() {
        Lote loteVIP = new Lote(1L, Arrays.asList(new Ingresso(1L, TipoIngresso.VIP, 50.0)), 500, 25.0);
        Map<Long, Lote> lotes = new HashMap<>();
        lotes.put(loteVIP.getId(), loteVIP);

        Show show = new Show(LocalDate.now(), "Artista", 3000.0, 300.0, lotes, false);

        Relatorio relatorio = show.gerarRelatorio();

        double receitaBruta = 500 * 50.0;
        double despesasTotais = 3000.0 + 300.0;

        Assertions.assertEquals(receitaBruta - despesasTotais, relatorio.getReceitaLiquida(), 0.01);
        Assertions.assertEquals(StatusFinanceiro.PREJUÍZO, relatorio.getStatusFinanceiro());
    }

    @Test
    public void testDecisionTable11() {
        Lote loteMeia = new Lote(1L, Arrays.asList(new Ingresso(1L, TipoIngresso.MEIA_ENTRADA, 25.0)), 500, 25.0);
        Map<Long, Lote> lotes = new HashMap<>();
        lotes.put(loteMeia.getId(), loteMeia);

        Show show = new Show(LocalDate.now(), "Artista", 3000.0, 500.0, lotes, true);
        loteMeia.aplicarDesconto();

        Relatorio relatorio = show.gerarRelatorio();

        double receitaBruta = 500 * (25.0 * (1 - 0.15));
        double despesasTotais = 3000.0 + 500.0;

        Assertions.assertEquals(receitaBruta - despesasTotais, relatorio.getReceitaLiquida(), 0.01);
        Assertions.assertEquals(StatusFinanceiro.PREJUÍZO, relatorio.getStatusFinanceiro());
    }

    @Test
    public void testDecisionTable12() {
        Lote loteNormal = new Lote(1L, Arrays.asList(new Ingresso(1L, TipoIngresso.NORMAL, 25.0)), 500, 25.0);
        Map<Long, Lote> lotes = new HashMap<>();
        lotes.put(loteNormal.getId(), loteNormal);

        Show show = new Show(LocalDate.now(), "Artista", 3000.0, 500.0, lotes, true);
        loteNormal.aplicarDesconto();

        Relatorio relatorio = show.gerarRelatorio();

        double receitaBruta = 500 * (25.0 * (1 - 0.25));
        double despesasTotais = 3000.0 + 500.0;

        Assertions.assertEquals(receitaBruta - despesasTotais, relatorio.getReceitaLiquida(), 0.01);
        Assertions.assertEquals(StatusFinanceiro.PREJUÍZO, relatorio.getStatusFinanceiro());
    }

}
