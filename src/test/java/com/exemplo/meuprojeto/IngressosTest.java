package com.exemplo.meuprojeto;

import org.junit.jupiter.api.Test;

import com.exemplo.meuprojeto.show.entities.Ingresso;
import com.exemplo.meuprojeto.show.tipos.TipoIngresso;

import static org.junit.jupiter.api.Assertions.*;

class IngressosTest {

    @Test
    void testCriacaoIngresso() {
        Ingresso ingresso = new Ingresso(TipoIngresso.NORMAL , 7);
        assertEquals(7, ingresso.getId());
        assertEquals(TipoIngresso.NORMAL , ingresso.getTipo());
        assertFalse(ingresso.foiVendido());
    }

    @Test
    void testMarcarIngressoComoVendido() {
        Ingresso ingresso = new Ingresso(1, "VIP");
        ingresso.marcarComoVendida();
        assertTrue(ingresso.isVendida());
    }
}
