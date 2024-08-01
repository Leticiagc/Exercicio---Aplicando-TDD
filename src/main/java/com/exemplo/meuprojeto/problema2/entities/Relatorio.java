package com.exemplo.meuprojeto.problema2.entities;

import com.exemplo.meuprojeto.problema2.types.StatusFinanceiro;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Relatorio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  
    private int qtdVIP;
    private int qtdMeia;
    private int qtdNormal;
    private double receitaLiquida;
    private StatusFinanceiro statusFinanceiro;

    public Relatorio(int qtdVIP, int qtdMeia, int qtdNormal, double receitaLiquida, StatusFinanceiro statusFinanceiro) {
        this.qtdVIP = qtdVIP;
        this.qtdMeia = qtdMeia;
        this.qtdNormal = qtdNormal;
        this.receitaLiquida = receitaLiquida;
        this.statusFinanceiro = statusFinanceiro;
    }

    public int getQtdVIP() { return qtdVIP; }
    public void setQtdVIP(int qtdVIP) { this.qtdVIP = qtdVIP; }

    public int getQtdMeiaEntrada() { return qtdMeia; }
    public void setQtdMeiaEntrada(int qtdMeiaEntrada) { this.qtdMeia = qtdMeiaEntrada; }

    public int getQtdNormal() { return qtdNormal; }
    public void setQtdNormal(int qtdNormal) { this.qtdNormal = qtdNormal; }

    public double getReceitaLiquida() { return receitaLiquida; }
    public void setReceitaLiquida(double receitaLiquida) { this.receitaLiquida = receitaLiquida; }

    public StatusFinanceiro getStatusFinanceiro() { return statusFinanceiro; }
    public void setStatusFinanceiro(StatusFinanceiro statusFinanceiro) { this.statusFinanceiro = statusFinanceiro; }
}
