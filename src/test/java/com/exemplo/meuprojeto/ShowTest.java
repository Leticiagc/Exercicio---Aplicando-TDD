package com.exemplo.meuprojeto;

import com.exemplo.meuprojeto.show.entities.Ingresso;
import com.exemplo.meuprojeto.show.entities.Lote;
import com.exemplo.meuprojeto.show.entities.Relatorio;
import com.exemplo.meuprojeto.show.entities.Show;
import com.exemplo.meuprojeto.show.tipos.StatusFinanceiro;
import com.exemplo.meuprojeto.show.tipos.TipoIngresso;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ShowTest {

    @Test
    void testCriacaoShow() {
        Map<Long, Lote> lotes = new HashMap<>();
        Lote lote1 = new Lote(1L, Arrays.asList(
                new Ingresso(1L, TipoIngresso.NORMAL),
                new Ingresso(2L, TipoIngresso.VIP)
        ), 10.0, 15.0);

        lotes.put(1L, lote1);

        Show show = new Show(LocalDate.now(), "Rihanna", 1000.0, 2000.0, lotes, true);

        assertEquals("Rihanna", show.getArtista());
        assertEquals(1000.0, show.getCache());
        assertEquals(2000.0, show.getDespesasInfraestrutura());
        assertTrue(show.isDataEspecial());
    }

    @Test
    void testMarcarIngressoVendido() {
        Ingresso ingresso = new Ingresso(1L, TipoIngresso.NORMAL);
        ingresso.setPreco(10.0);
        assertFalse(ingresso.foiVendido());
        ingresso.setVendido(true);
        assertTrue(ingresso.foiVendido());
    }

    @Test
    void testAplicarDescontoLote() {
        Ingresso ingresso1 = new Ingresso(1L, TipoIngresso.NORMAL);
        ingresso1.setPreco(10.0);
        Ingresso ingresso2 = new Ingresso(2L, TipoIngresso.VIP);
        ingresso2.setPreco(20.0);

        Lote lote = new Lote(1L, Arrays.asList(ingresso1, ingresso2), 15.0, 0.0);
        lote.aplicarDesconto();

        assertEquals(8.5, ingresso1.getPreco(), 0.01); // 10.0 - 15% = 8.5
        assertEquals(17.0, ingresso2.getPreco(), 0.01); // 20.0 - 15% = 17.0
    }

    @Test
    void testGerarRelatorioShow() {
        Map<Long, Lote> lotes = new HashMap<>();
        Lote lote1 = new Lote(1L, Arrays.asList(
                new Ingresso(1L, TipoIngresso.NORMAL) {{ setPreco(10.0); setVendido(true); }},
                new Ingresso(2L, TipoIngresso.VIP) {{ setPreco(20.0); setVendido(true); }},
                new Ingresso(3L, TipoIngresso.MEIA_ENTRADA) {{ setPreco(5.0); setVendido(true); }}
        ), 15.0, 0.0);

        lotes.put(1L, lote1);

        Show show = new Show(LocalDate.now(), "Rihanna", 1000.0, 2000.0, lotes, true);
        Relatorio relatorio = show.gerarRelatorio();

        assertEquals(1, relatorio.getQtdVIP());
        assertEquals(1, relatorio.getQtdMeiaEntrada());
        assertEquals(1, relatorio.getQtdNormal());
        assertEquals(StatusFinanceiro.PREJUÍZO, relatorio.getStatusFinanceiro());
    }
}
