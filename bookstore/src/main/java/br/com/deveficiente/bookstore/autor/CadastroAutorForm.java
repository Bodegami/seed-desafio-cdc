package br.com.deveficiente.bookstore.autor;

import br.com.deveficiente.bookstore.validadores.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CadastroAutorForm(
        @NotEmpty
        String nome,
        @NotEmpty @Email @UniqueEmail
        String email,
        @NotEmpty @Size(max = 400)
        String descricao
) {

        public Autor toModel() {
                return new Autor(this.nome.trim(), this.email, this.descricao);
        }

}
