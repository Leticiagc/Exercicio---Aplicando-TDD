package com.exemplo.meuprojeto.show.entities;


import java.util.List;
import com.exemplo.meuprojeto.show.tipos.TipoIngresso;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private List<Ingresso> ingressos;
    private double descontoIngresso;
    private Double preco;


    public Lote(Long id, List<Ingresso> ingressos, double descontoIngresso,Double preco) {
        this.id = id;
        this.ingressos = ingressos;
        this.descontoIngresso = descontoIngresso;
        this.preco = preco;
    }

    public Long getId() { 
        return id; 
    }
    public void setId(Long id) { 
        this.id = id; 
    }

    public List<Ingresso> getIngressos() { 
        return ingressos; 
    }

    public void setIngressos(List<Ingresso> ingressos) { 
        this.ingressos = ingressos; 
    }

    public double getDesconto() { 
        return descontoIngresso; 
    }
    public void setDesconto(double desconto) { this.descontoIngresso = desconto; }

    public double getPreco() { 
        return preco; 
    }

    public void setPreco(double preco) { this.preco = preco; }

    public void aplicarDesconto() {
        for (Ingresso ingresso : ingressos) {
            if (ingresso.getTipo() == TipoIngresso.NORMAL || ingresso.getTipo() == TipoIngresso.VIP) {
                ingresso.setPreco(ingresso.getPreco() * (1 - descontoIngresso / 100));
            }
        }

    }

    public void venderIngresso(int quantidade, TipoIngresso tipo) {
        int vendidos = 0;
        for (Ingresso ingresso : ingressos) {
            if (!ingresso.foiVendido() && ingresso.getTipo() == tipo && vendidos < quantidade) {
                ingresso.setVendido(true);
                vendidos++;
            }
        }
    }
}
