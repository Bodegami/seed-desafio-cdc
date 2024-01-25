package br.com.deveficiente.bookstore.compra;

import java.math.BigDecimal;

public record DetalhesItemPedidoResponse(
        String livro,
        Integer quantidade,
        BigDecimal preco
) {

    public DetalhesItemPedidoResponse(ItemPedido itemPedido) {
        this(itemPedido.getLivro().getTitulo(), itemPedido.getQuantidade(), itemPedido.getPrecoDoMomento());
    }

}
