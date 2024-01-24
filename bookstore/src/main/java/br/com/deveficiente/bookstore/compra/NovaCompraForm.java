package br.com.deveficiente.bookstore.compra;

import br.com.deveficiente.bookstore.estado.Estado;
import br.com.deveficiente.bookstore.exception.EstadoNaoPertenceAPaisException;
import br.com.deveficiente.bookstore.pais.Pais;
import br.com.deveficiente.bookstore.validadores.CpfECnpj;
import br.com.deveficiente.bookstore.validadores.ExistsById;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.function.Function;


public record NovaCompraForm(
        @NotBlank
        String nome,
        @NotBlank
        String sobrenome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @CpfECnpj
        String documento,
        @NotBlank
        String endereco,
        @NotBlank
        String complemento,
        @NotBlank
        String cidade,
        @ExistsById(domainClass = Pais.class, fieldName = "id")
        Long paisId,
        @ExistsById(domainClass = Estado.class, fieldName = "id")
        Long estadoId,
        @NotBlank
        @Pattern(regexp = "[0-9]{5}-[0-9]{4}")
        String telefone,
        @NotBlank
        @Pattern(regexp = "[0-9]{5}-[0-9]{3}")
        String cep,

        //1
        @Valid
        NovoPedidoForm pedido,
        String codigoDoCupom
) {

    //1
    public Compra toModel(EntityManager em) {
        //1
        Pais pais = em.find(Pais.class, paisId);
        //1
        Estado estado = null;

        //1
        if (estadoId != null) {
            estado = em.find(Estado.class, estadoId);

            //1
            if (!estado.isFrom(pais)) {
                throw new EstadoNaoPertenceAPaisException(String.format(
                        "O estado informado: %s - não pertence ao pais informado: %s", estado.getNome(), pais.getNome()));
            }

        }

        //1
        Function<Compra, Pedido> funcaoCriacaoPedido = pedido.toModel(em);

        Compra compra = new Compra(
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
                ),
                funcaoCriacaoPedido
        );

        compra.aplicaCupom(em, codigoDoCupom);

        return compra;
    }

}
