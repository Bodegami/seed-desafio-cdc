package br.com.deveficiente.bookstore.compra;


import jakarta.persistence.*;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Compra compra;

    @ElementCollection
    private Set<ItemPedido> itens = new HashSet<>();

    public Pedido() {
    }

    public Pedido(Compra compra, Set<ItemPedido> itens) {
        Assert.isTrue(!itens.isEmpty(), "todo pedido deve ter pelo menos um item!");
        this.compra = compra;
        this.itens.addAll(itens);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(itens, pedido.itens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itens);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "compra=" + compra +
                ", itensDoPedido=" + itens +
                '}';
    }

    public boolean totalIgual(BigDecimal total) {
        BigDecimal totalPedido = itens.stream().map(ItemPedido::total).reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalPedido.doubleValue() == total.doubleValue();
    }
}
