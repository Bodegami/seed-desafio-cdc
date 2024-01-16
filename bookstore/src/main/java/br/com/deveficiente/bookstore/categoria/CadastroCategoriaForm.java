package br.com.deveficiente.bookstore.categoria;

import br.com.deveficiente.bookstore.example.UniqueValueAlberto;
import jakarta.validation.constraints.NotBlank;

public record CadastroCategoriaForm(
        @NotBlank(message = "O nome não pode estar em branco!")
        //@UniqueValue(message = "Categoria já existe no sistema!")
        @UniqueValueAlberto(domainClass = Categoria.class, fieldName = "nome")
        String nome
) {

    public Categoria toModel() {
        return new Categoria(nome.trim().toLowerCase());
    }
}
