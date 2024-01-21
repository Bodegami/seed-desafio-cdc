package br.com.deveficiente.bookstore.compra;

import br.com.deveficiente.bookstore.estado.Estado;
import br.com.deveficiente.bookstore.example.UniqueValueAlberto;
import br.com.deveficiente.bookstore.exception.EstadoNaoPertenceAPaisException;
import br.com.deveficiente.bookstore.pais.Pais;
import br.com.deveficiente.bookstore.validadores.CpfECnpj;
import br.com.deveficiente.bookstore.validadores.ExistsById;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CadastroCompraForm(
        @NotBlank
        String nome,
        @NotBlank
        String sobrenome,
        @NotBlank
        @Email
        @UniqueValueAlberto(domainClass = Compra.class, fieldName = "email")
        String email,
        @NotBlank
        @CpfECnpj
        @UniqueValueAlberto(domainClass = Compra.class, fieldName = "documento",message = "Documento já cadastrado!")
        String documento,
        @NotBlank
        String endereco,
        @NotBlank
        String complemento,
        @NotBlank
        String cidade,
        @NotNull
        @ExistsById(domainClass = Pais.class, fieldName = "id")
        Long paisId,
        @NotNull
        @ExistsById(domainClass = Estado.class, fieldName = "id")
        Long estadoId,
        @NotBlank
        @Pattern(regexp = "[0-9]{5}-[0-9]{4}")
        @UniqueValueAlberto(domainClass = Compra.class, fieldName = "telefone",message = "Telefone já cadastrado!")
        String telefone,
        @NotBlank
        @Pattern(regexp = "[0-9]{5}-[0-9]{3}")
        String cep
) {

    public Compra toModel(EntityManager em) {
        Estado estado = em.find(Estado.class, estadoId);
        Pais pais = em.find(Pais.class, paisId);

        if (!estado.isFrom(pais)) {
            throw new EstadoNaoPertenceAPaisException(String.format(
                    "O estado informado: %s - não pertence ao pais informado: %s", estado.getNome(), pais.getNome()));
        }

        return new Compra(
                nome,
                sobrenome,
                documento,
                email,
                telefone,
                new Endereco(
                        endereco,
                        complemento,
                        cidade,
                        pais,
                        estado,
                        cep
                )
        );
    }

}
