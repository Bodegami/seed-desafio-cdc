package br.com.deveficiente.bookstore.compra;

import br.com.deveficiente.bookstore.livro.Livro;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Positive;


import java.math.BigDecimal;

@Embeddable
public class ItemPedido {

    //1
    @ManyToOne
    private Livro livro;
    @Positive
    private Integer quantidade;
    @Positive
    private BigDecimal precoDoMomento;

    @Deprecated
    public ItemPedido() {
    }

    public ItemPedido(Livro livro, Integer quantidade) {
        this.livro = livro;
        this.quantidade = quantidade;
        this.precoDoMomento = livro.getPreco();
    }

    public Livro getLivro() {
        return livro;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPrecoDoMomento() {
        return precoDoMomento;
    }

    @Override
    public String toString() {
        return "ItemPedido{" +
                "livro=" + livro +
                ", quantidade=" + quantidade +
                '}';
    }

    public BigDecimal total() {
        return precoDoMomento.multiply(new BigDecimal(quantidade));
    }
}
