package br.com.deveficiente.bookstore.cupom;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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

    public boolean isValid(LocalDate dataAtual) {
        long days = ChronoUnit.DAYS.between(dataAtual, validade);

        return days > 0;
    }

    public Double getPercentualDesconto() {
        return percentualDesconto;
    }

    public LocalDate getValidade() {
        return validade;
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
