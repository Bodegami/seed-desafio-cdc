package br.com.deveficiente.bookstore.compra;

import br.com.deveficiente.bookstore.livro.Livro;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public record NovoPedidoForm(
        @NotNull
        @Min(value = 1)
        BigDecimal total,

        @Valid
        @Size(min = 1, message = "O pedido deve conter pelo menos 1 item...")
        List<NovoItemPedido> itens
) {

        public Function<Compra, Pedido> toModel(EntityManager em) {

                Set<ItemPedido> itensDoPedido = itens.stream().map(item -> item.toModel(em)).collect(Collectors.toSet());
                return (compra) -> {
                        Pedido pedido = new Pedido(compra, itensDoPedido);

                        Assert.isTrue(pedido.totalIgual(total), "Total enviado não corresponde ao total real!");
                        return pedido;
                };
        }
}
