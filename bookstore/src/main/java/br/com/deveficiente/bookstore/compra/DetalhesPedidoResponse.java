package br.com.deveficiente.bookstore.compra;

import java.util.Set;
import java.util.stream.Collectors;

public record DetalhesPedidoResponse(
        Set<DetalhesItemPedidoResponse> itens
) {

    public DetalhesPedidoResponse(Pedido pedido) {
        this(pedido.getItens().stream()
                .map(DetalhesItemPedidoResponse::new)
                .collect(Collectors.toSet())
        );
    }

}
