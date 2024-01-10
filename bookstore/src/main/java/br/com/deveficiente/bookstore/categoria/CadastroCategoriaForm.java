package br.com.deveficiente.bookstore.categoria;

import br.com.deveficiente.bookstore.validadores.UniqueCategoria;
import br.com.deveficiente.bookstore.validadores.UniqueValue;
import br.com.deveficiente.bookstore.validadores.UniqueValueAlberto;
import jakarta.validation.constraints.NotBlank;

public record CadastroCategoriaForm(
        @NotBlank(message = "O nome não pode estar em branco!")
        //@UniqueValue(message = "Categoria já existe no sistema!")
        @UniqueValueAlberto(domainClass = Categoria.class, fieldName = "nome")
        String nome
) {

    Categoria toModel() {
        return new Categoria(nome.trim().toLowerCase());
    }
}
