package com.exemplo;

import org.junit.jupiter.api.Test;

import com.exemplo.meuprojeto.show.entities.Show;

import static org.junit.jupiter.api.Assertions.*;

class ShowTest {

    @Test
    void testCriacaoShow() {
        Show show = new Show("Data Especial", "Artista", 1000.00, 2000.00, true);
        assertEquals("Data Especial", show.getData());
        assertEquals("Artista", show.getArtista());
        assertEquals(1000.00, show.getCache());
        assertEquals(2000.00, show.getDespesasInfraestrutura());
        assertTrue(show.isDataEspecial());
    }

    @Test
    void testCalculoReceitaLiquida() {
        Show show = new Show("Data Especial", "Artista", 1000.00, 2000.00, true);
        Lote lote = new Lote(1, 500, 10.00, 0.15);
        lote.venderIngresso(200, "VIP");
        show.adicionarLote(lote);
        
        double receitaLiquida = show.calcularReceitaLiquida();
        assertEquals(4925.00, receitaLiquida, 0.01);
    }

    @Test
    void testStatusFinanceiroLucro() {
        Show show = new Show("Data Especial", "Artista", 1000.00, 2000.00, true);
        Lote lote = new Lote(1, 500, 10.00, 0.15);
        lote.venderIngresso(200, "VIP");
        show.adicionarLote(lote);
        
        String status = show.getStatusFinanceiro();
        assertEquals("LUCRO", status);
    }
}
