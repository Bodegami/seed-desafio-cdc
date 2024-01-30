package br.com.deveficiente.bookstore.livro;

import br.com.deveficiente.bookstore.autor.Autor;
import br.com.deveficiente.bookstore.categoria.Categoria;
import br.com.deveficiente.bookstore.validadores.ExistsById;
import br.com.deveficiente.bookstore.example.UniqueValueAlberto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.*;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CadastroLivroForm(
        @NotBlank
        @UniqueValueAlberto(domainClass = Livro.class, fieldName = "titulo", message = "Titulo já registrado!")
        String titulo,
        @NotBlank @Size(max = 500)
        String resumo,
        @NotBlank
        String sumario,
        @NotNull @Min(20)
        BigDecimal preco,
        @NotNull @Min(100)
        Integer numeroPaginas,
        @NotBlank
        @UniqueValueAlberto(domainClass = Livro.class, fieldName = "isbn", message = "ISBN já resgistrado!")
        String isbn,
        @Future @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate dataPublicacao,
        @NotNull
        @ExistsById(domainClass = Categoria.class, fieldName = "id", message = "Categoria não encontrada!")
        Long categoriaId,
        @NotNull
        @ExistsById(domainClass = Autor.class, fieldName = "id", message = "Autor não encontrado!")
        Long autorId
) {

        public Livro toModel(EntityManager em) {
                Categoria categoria = em.find(Categoria.class, categoriaId);
                Autor autor = em.find(Autor.class, autorId);

                Assert.state(categoria != null, "Você não deveria criar um livro sem categoria!");
                Assert.state(autor != null, "Você não deveria criar um livro sem autor!");

                return new Livro(
                        titulo,
                        resumo,
                        sumario,
                        preco,
                        numeroPaginas,
                        isbn,
                        dataPublicacao,
                        categoria,
                        autor
                );
        }
}
