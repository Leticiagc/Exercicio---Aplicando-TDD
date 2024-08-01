package com.exemplo.meuprojeto.problema1;

import org.junit.jupiter.api.Test;

import com.exemplo.meuprojeto.problema2.entities.Ingresso;
import com.exemplo.meuprojeto.problema2.entities.Lote;
import com.exemplo.meuprojeto.problema2.types.TipoIngresso;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

class LotesJava {

    @Test
    void testCriacaoLote01() {
        Ingresso ingresso1 = new Ingresso(10L,  TipoIngresso.NORMAL);
        Ingresso ingresso2 = new Ingresso(20L, TipoIngresso.VIP);
        Lote lote = new Lote(1L, Arrays.asList(ingresso1, ingresso2), 10.0, 15.0);

        assertEquals(1L, lote.getId());
        assertEquals(2, lote.getIngressos().size());
        assertEquals(10.0, lote.getDesconto());
        assertEquals(15.0, lote.getPreco());
    }


    @Test
    void testCriacaoLote02() {
        Ingresso ingresso10 = new Ingresso(10L,  TipoIngresso.MEIA_ENTRADA);
        Ingresso ingresso20 = new Ingresso(20L, TipoIngresso.VIP);
        Lote lote = new Lote(10L, Arrays.asList(ingresso10, ingresso20), 10.0, 15.0);

        assertEquals(10L, lote.getId());
        assertEquals(2, lote.getIngressos().size());
        assertEquals(10.0, lote.getDesconto());
        assertEquals(15.0, lote.getPreco());
    }

    @Test
    void testAplicarDesconto() {
        Ingresso ingresso1 = new Ingresso(50L, TipoIngresso.NORMAL);
        ingresso1.setPreco(10.0); 
        Ingresso ingresso2 = new Ingresso(70L, TipoIngresso.VIP);
        ingresso2.setPreco(20.0); 
        Lote lote = new Lote(1L, Arrays.asList(ingresso1, ingresso2), 20.0, 15.0);

        lote.aplicarDesconto();

        assertEquals(8.0, ingresso1.getPreco(), 0.01); 
        assertEquals(16.0, ingresso2.getPreco(), 0.01); 
    }
}

