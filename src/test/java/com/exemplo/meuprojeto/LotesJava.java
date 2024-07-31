package com.exemplo.meuprojeto;

import org.junit.jupiter.api.Test;

import com.exemplo.meuprojeto.show.entities.Lote;

import static org.junit.jupiter.api.Assertions.*;

class LotesTest {

    @Test
    void testCriacaoLote() {
        Lote lote = new Lote(1, 500, 10.00, 0.15);
        assertEquals(1, lote.getId());
        assertEquals(500, lote.getQuantidade());
        assertEquals(10.00, lote.getPrecoNormal());
        assertEquals(0.15, lote.getDesconto(), 0.01);
    }

    @Test
    void testAplicarDesconto() {
        Lote lote = new Lote(1, 500, 10.00, 0.15);
        double precoComDesconto = lote.calcularPrecoComDesconto("VIP");
        assertEquals(8.00, precoComDesconto, 0.01);
    }
}
