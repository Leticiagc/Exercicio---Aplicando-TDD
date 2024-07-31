package com.exemplo.meuprojeto;

import org.junit.jupiter.api.Test;

import com.exemplo.meuprojeto.show.entities.Ingresso;
import com.exemplo.meuprojeto.show.entities.Lote;
import com.exemplo.meuprojeto.show.entities.Relatorio;
import com.exemplo.meuprojeto.show.entities.Show;
import com.exemplo.meuprojeto.show.tipos.TipoIngresso;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RelatorioDoShowTest {

    @Test
    void testGerarRelatorio() {
        Show show = new Show(LocalDate.now(), "Flavin da Flauta", 1000.00, 2000.00, null, true);

        Ingresso ingressoVIP = new Ingresso(1L, TipoIngresso.VIP);
        ingressoVIP.setPreco(20.00);
        Ingresso ingressoNormal = new Ingresso(2L, TipoIngresso.NORMAL);
        ingressoNormal.setPreco(10.00);
        Ingresso ingressoMeia = new Ingresso(3L, TipoIngresso.MEIA_ENTRADA);
        ingressoMeia.setPreco(5.00);

        Lote lote = new Lote(1L, Arrays.asList(ingressoVIP, ingressoNormal, ingressoMeia), 0.15, null);
        lote.venderIngresso(1, TipoIngresso.VIP);
        lote.venderIngresso(1, TipoIngresso.NORMAL);
        lote.venderIngresso(1, TipoIngresso.MEIA_ENTRADA);

        show.addLoteShow(lote);

        Relatorio relatorio = show.gerarRelatorio();

        assertEquals(1, relatorio.getQtdVIP());
        assertEquals(1, relatorio.getQtdNormal());
        assertEquals(1, relatorio.getQtdMeiaEntrada());
        assertEquals(4925.00, relatorio.getReceitaLiquida(), 0.01);
        assertEquals(TipoIngresso.MEIA_ENTRADA, relatorio.getStatusFinanceiro());
    }
}
