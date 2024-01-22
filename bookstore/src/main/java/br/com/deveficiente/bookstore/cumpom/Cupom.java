package br.com.deveficiente.bookstore.cumpom;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "cupons")
public class Cupom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;
    private Double percentualDesconto;
    private LocalDate validade;

    @Deprecated
    public Cupom() {
    }

    public Cupom(String codigo, Double percentualDesconto, LocalDate validade) {
        this.codigo = codigo;
        this.percentualDesconto = percentualDesconto;
        this.validade = validade;
    }

    @Override
    public String toString() {
        return "Cupom{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", percentualDesconto=" + percentualDesconto +
                ", validade=" + validade +
                '}';
    }
}
