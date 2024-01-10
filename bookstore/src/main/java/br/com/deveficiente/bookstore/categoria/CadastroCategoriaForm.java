package br.com.deveficiente.bookstore.categoria;

import br.com.deveficiente.bookstore.validadores.UniqueCategoria;
import jakarta.validation.constraints.NotBlank;

public record CadastroCategoriaForm(
        @NotBlank(message = "O nome não pode estar em branco!")
        @UniqueCategoria(message = "Categoria já existe no sistema!")
        String nome
) {

    Categoria toModel() {
        return new Categoria(nome.trim().toLowerCase());
    }
}
