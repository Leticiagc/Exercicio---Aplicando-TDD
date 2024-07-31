package com.exemplo.meuprojeto.show.entities;

import java.time.LocalDate;
import java.util.Map;
import com.exemplo.meuprojeto.show.tipos.StatusFinanceiro;
import com.exemplo.meuprojeto.show.tipos.TipoIngresso;
import jakarta.persistence.Entity;

@Entity
public class Show {

    private LocalDate data;
    private String artista;
    private double cache;
    private double despesasInfraestrutura;
    private Map<Long, Lote> lotesIngressos;
    private boolean isDataEspecial;

    public Show(LocalDate data, String artista, double cache, double despesasInfraestrutura,
            Map<Long, Lote> lotesIngressos, boolean isDataEspecial) {
        this.data = data;
        this.artista = artista;
        this.cache = cache;
        this.despesasInfraestrutura = despesasInfraestrutura;
        this.lotesIngressos = lotesIngressos;
        this.isDataEspecial = isDataEspecial;
    }

    public LocalDate getData() {
        return data;
    }

    public void addLoteShow(Lote lote) {
        lotesIngressos.put(lote.getId(), lote);
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

    public double getDespesasInfraestrutura() {
        return despesasInfraestrutura;
    }

    public void setDespesasInfraestrutura(double despesasInfraestrutura) {
        this.despesasInfraestrutura = despesasInfraestrutura;
    }

    public Map<Long, Lote> getLotesIngressos() {
        return lotesIngressos;
    }

    public void setLotesIngressos(Map<Long, Lote> lotesIngressos) {
        this.lotesIngressos = lotesIngressos;
    }

    public boolean isDataEspecial() {
        return isDataEspecial;
    }

    public void setDataEspecial(boolean dataEspecial) {
        this.isDataEspecial = dataEspecial;
    }

    public Relatorio gerarRelatorio() {
        double receitaBruta = 0;
        double receitaLiquida = 0;
        int qtdVIP = 0, qtdMeiaEntrada = 0, qtdNormal = 0;

        for (Lote lote : lotesIngressos.values()) {
            for (Ingresso ingresso : lote.getIngressos()) {
                if (ingresso.foiVendido()) {
                    if (ingresso.getTipo() == TipoIngresso.VIP) {
                        qtdVIP++;
                        receitaBruta += ingresso.getPreco();
                    } else if (ingresso.getTipo() == TipoIngresso.MEIA_ENTRADA) {
                        qtdMeiaEntrada++;
                        receitaBruta += ingresso.getPreco();
                    } else if (ingresso.getTipo() == TipoIngresso.NORMAL) {
                        qtdNormal++;
                        receitaBruta += ingresso.getPreco();
                    }
                }
            }
        }
        double descontoTotal = 0;
        for (Lote lote : lotesIngressos.values()) {
            descontoTotal += lote.getDesconto() * (qtdVIP + qtdNormal);
        }
        receitaLiquida = receitaBruta
                - (despesasInfraestrutura + (isDataEspecial ? 0.15 * despesasInfraestrutura : 0) + cache)
                - descontoTotal;

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
