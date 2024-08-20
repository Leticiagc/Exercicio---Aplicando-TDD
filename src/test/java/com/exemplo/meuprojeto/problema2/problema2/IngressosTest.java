package com.exemplo.meuprojeto.problema2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.exemplo.meuprojeto.problema2.entities.Ingresso;
import com.exemplo.meuprojeto.problema2.types.TipoIngresso;

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
        ingresso.foiVendido();
        assertTrue(ingresso.foiVendido());
    }
}
