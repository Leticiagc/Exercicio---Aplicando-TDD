package com.exemplo.meuprojeto.problema1;

import org.junit.jupiter.api.Test;

import com.exemplo.meuprojeto.problema2.entities.Ingresso;
import com.exemplo.meuprojeto.problema2.types.TipoIngresso;

import static org.junit.jupiter.api.Assertions.*;

class IngressosTest {

    @Test
    void testCriacaoIngresso() {
        Ingresso ingresso = new Ingresso(7L, TipoIngresso.NORMAL );
        assertEquals(TipoIngresso.NORMAL , ingresso.getTipo());
        assertFalse(ingresso.foiVendido());
    }

    @Test
    void testMarcarIngressoComoVendido() {
        Ingresso ingresso = new Ingresso(1L, TipoIngresso.VIP);
        ingresso.setVendido(true);
        assertTrue(ingresso.foiVendido());
    }
}
