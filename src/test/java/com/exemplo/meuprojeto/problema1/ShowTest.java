package com.exemplo.meuprojeto.problema1;

import org.junit.jupiter.api.Test;
import com.exemplo.meuprojeto.problema2.entities.Ingresso;
import com.exemplo.meuprojeto.problema2.entities.Lote;
import com.exemplo.meuprojeto.problema2.entities.Relatorio;
import com.exemplo.meuprojeto.problema2.entities.Show;
import com.exemplo.meuprojeto.problema2.types.StatusFinanceiro;
import com.exemplo.meuprojeto.problema2.types.TipoIngresso;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ShowTest {

    @Test
    void testCriacaoShow01() {
        Map<Long, Lote> lotes = new HashMap<>();
        Lote lote1 = new Lote(1L, Arrays.asList(
                new Ingresso(1L, TipoIngresso.NORMAL),
                new Ingresso(2L, TipoIngresso.VIP)
        ), 10.0, 15.0);

        lotes.put(1L, lote1);

        Show show = new Show(LocalDate.now(), "Rihanna", 1000.0, 2000.0, lotes, true);

        assertEquals("Rihanna", show.getArtista());
        assertEquals(1000.0, show.getCache());
        assertTrue(show.isDataEspecial());
    }

    @Test
    void testCriacaoShow02() {
        Map<Long, Lote> lotes = new HashMap<>();
        Lote lote1 = new Lote(1L, Arrays.asList(
                new Ingresso(1L, TipoIngresso.NORMAL),
                new Ingresso(2L, TipoIngresso.VIP)
        ), 10.0, 15.0);

        lotes.put(1L, lote1);

        Show show = new Show(LocalDate.now(), "Maddona", 1000.0, 2000.0, lotes, true);

        assertEquals("Maddona", show.getArtista());
        assertEquals(1000.0, show.getCache());
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

        assertEquals(8.5, ingresso1.getPreco(), 0.01); 
        assertEquals(17.0, ingresso2.getPreco(), 0.01);
    }

}
