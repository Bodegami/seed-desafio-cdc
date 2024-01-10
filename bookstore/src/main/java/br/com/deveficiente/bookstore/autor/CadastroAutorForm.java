package br.com.deveficiente.bookstore.autor;

import br.com.deveficiente.bookstore.validadores.UniqueEmail;
import br.com.deveficiente.bookstore.validadores.UniqueValue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CadastroAutorForm(
        @NotBlank(message = "O nome não pode estar em branco!")
        String nome,
        @NotBlank(message = "O email não pode estar em branco!")
        @Email(message = "Email informado está num formato inválido!")
        @UniqueValue(message = "O email informado já está cadastrado!")
        String email,
        @NotBlank(message = "A descrição não pode estar em branco!")
        @Size(max = 400, message = "Limite máximo de 400 caracteres")
        String descricao
) {

        public Autor toModel() {
                return new Autor(
                        this.nome.trim(),
                        this.email.trim().toLowerCase(),
                        this.descricao);
        }

}
