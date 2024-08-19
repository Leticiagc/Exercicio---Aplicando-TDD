package com.exemplo.meuprojeto.problema2.entities;

import com.exemplo.meuprojeto.problema2.types.StatusFinanceiro;
import com.exemplo.meuprojeto.problema2.types.TipoIngresso;
import jakarta.persistence.Entity;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Show {
    private LocalDate data;
    private String artista;
    private double cache;
    private double despesas;
    private Map<Long, Lote> lotesIngressos;
    private boolean dataEspecial;

    public Show(LocalDate data, String artista, double cache, double despesas, Map<Long, Lote> lotesIngressos, boolean dataEspecial) {
        this.data = data;
        this.artista = artista;
        this.cache = cache;
        this.despesas = despesas;
        this.lotesIngressos = lotesIngressos != null ? lotesIngressos : new HashMap<>();
        this.dataEspecial = dataEspecial;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public double getCache() {
        return cache;
    }

    public void setCache(double cache) {
        this.cache = cache;
    }

    public double getDespesas() {
        return despesas;
    }

    public void setDespesas(double despesas) {
        this.despesas = despesas;
    }

    public Map<Long, Lote> getLotesIngressos() {
        return lotesIngressos;
    }

    public void setLotesIngressos(Map<Long, Lote> lotesIngressos) {
        this.lotesIngressos = lotesIngressos;
    }

    public boolean isDataEspecial() {
        return dataEspecial;
    }

    public void setDataEspecial(boolean dataEspecial) {
        this.dataEspecial = dataEspecial;
    }

    public void addLoteShow(Lote lote) {
        if (lote != null) {
            this.lotesIngressos.put(lote.getId(), lote);
        }
    }

    public Relatorio gerarRelatorio() {
        int qtdVIP = 0;
        int qtdNormal = 0;
        int qtdMeiaEntrada = 0;
        double receitaBruta = 0.0;

        for (Lote lote : lotesIngressos.values()) {
            qtdVIP += lote.getQtdVendida(TipoIngresso.VIP);
            qtdNormal += lote.getQtdVendida(TipoIngresso.NORMAL);
            qtdMeiaEntrada += lote.getQtdVendida(TipoIngresso.MEIA_ENTRADA);
            receitaBruta += lote.calcularReceita();
        }

        double despesasTotais = despesas + cache;
        if (dataEspecial) {
            despesasTotais += despesas * 0.15;
        }

        double receitaLiquida = receitaBruta - despesasTotais;
        StatusFinanceiro statusFinanceiro;

        if (receitaLiquida > 0) {
            statusFinanceiro = StatusFinanceiro.LUCRO;
        } else if (receitaLiquida == 0) {
            statusFinanceiro = StatusFinanceiro.ESTAVEL;
        } else {
            statusFinanceiro = StatusFinanceiro.PREJUÍZO;
        }

        return new Relatorio(qtdVIP, qtdMeiaEntrada, qtdNormal, receitaLiquida, statusFinanceiro);
    }
}
