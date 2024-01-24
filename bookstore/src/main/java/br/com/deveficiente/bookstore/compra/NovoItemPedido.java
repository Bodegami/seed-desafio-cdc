package br.com.deveficiente.bookstore.compra;

import br.com.deveficiente.bookstore.livro.Livro;
import br.com.deveficiente.bookstore.validadores.ExistsById;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record NovoItemPedido(
        @NotNull
        @ExistsById(domainClass = Livro.class, fieldName = "id", message = "ID do livro inválido!")
        Long idLivro,
        @NotNull
        @Positive
        Integer quantidade
) {

        ItemPedido toModel(EntityManager em) {
                //1
                Livro livro = em.find(Livro.class, idLivro);
                return new ItemPedido(livro, quantidade);
        }

}
