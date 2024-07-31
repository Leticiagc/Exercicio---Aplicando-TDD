package com.exemplo.meuprojeto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RelatorioDoShowTest {

    @Test
    void testGerarRelatorio() {
        Show show = new Show("Data Especial", "Artista", 1000.00, 2000.00, true);
        Lote lote = new Lote(1, 500, 10.00, 0.15);
        lote.venderIngresso(200, "VIP");
        show.adicionarLote(lote);

        RelatorioShow relatorio = show.gerarRelatorio();
        assertEquals(200, relatorio.getNumeroIngressosVendidos("VIP"));
        assertEquals(4925.00, relatorio.getReceitaLiquida(), 0.01);
        assertEquals("LUCRO", relatorio.getStatusFinanceiro());
    }
}
