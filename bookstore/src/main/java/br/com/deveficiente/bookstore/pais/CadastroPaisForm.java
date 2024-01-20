package br.com.deveficiente.bookstore.pais;

import br.com.deveficiente.bookstore.example.UniqueValueAlberto;
import jakarta.validation.constraints.NotBlank;

public record CadastroPaisForm(
        @NotBlank
        @UniqueValueAlberto(domainClass = Pais.class, fieldName = "nome", message = "Esse pais ja foi cadastrado")
        String nome
) {

    public Pais toModel() {
        return new Pais(
                nome.toLowerCase()
        );
    }

}
