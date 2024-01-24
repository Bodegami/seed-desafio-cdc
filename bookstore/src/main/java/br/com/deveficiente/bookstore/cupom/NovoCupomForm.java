package br.com.deveficiente.bookstore.cupom;

import br.com.deveficiente.bookstore.example.UniqueValueAlberto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record NovoCupomForm(
        @NotBlank
        @UniqueValueAlberto(domainClass = Cupom.class, fieldName = "codigo", message = "Cupom já cadastrado!")
        String codigo,
        @NotNull
        @Positive
        Double percentualDesconto,
        @NotNull
        @Future
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate validade
) {
    public Cupom toModel() {
        return new Cupom(codigo.toLowerCase(), percentualDesconto, validade);
    }

}
