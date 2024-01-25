package br.com.deveficiente.bookstore.compra;

import java.math.BigDecimal;

public record DetalhesCompraResponse(
        Long id,
        String nome,
        String sobrenome,
        String documento,
        String email,
        String telefone,
        DetalhesEnderecoResponse endereco,
        DetalhesPedidoResponse pedido,
        Boolean cupomAplicado,
        BigDecimal valorFinal
        ) {

    public DetalhesCompraResponse(Compra compra) {
        this(compra.getId(),
                compra.getNome(),
                compra.getSobrenome(),
                compra.getDocumento(),
                compra.getEmail(),
                compra.getTelefone(),
                new DetalhesEnderecoResponse(compra.getEndereco()),
                new DetalhesPedidoResponse(compra.getPedido()),
                compra.getCupom() == null,
                compra.calculaValorFinal()
                );
    }



}
