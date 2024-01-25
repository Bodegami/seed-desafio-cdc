package br.com.deveficiente.bookstore.compra;

import br.com.deveficiente.bookstore.cupom.Cupom;
import br.com.deveficiente.bookstore.exception.CupomInvalidoException;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

@Embeddable
public class CupomAplicado {

    //1
    @ManyToOne
    private Cupom cupom;
    @Positive
    @NotNull
    private Double percentualDoMomento;
    @Future
    @NotNull
    private LocalDate validade;

    @Deprecated
    public CupomAplicado() {
    }

    public CupomAplicado(Cupom cupom) {
        if (!cupom.isValid(LocalDate.now())) {
            throw new CupomInvalidoException("Cupom com validade expirada em: " + cupom.getValidade().toString());
        }

        this.cupom = cupom;
        this.percentualDoMomento = cupom.getPercentualDesconto();
        this.validade = cupom.getValidade();
    }

    public Cupom getCupom() {
        return cupom;
    }

    public Double getPercentualDoMomento() {
        return percentualDoMomento;
    }

    public LocalDate getValidade() {
        return validade;
    }
}
