package br.com.deveficiente.bookstore.livro;

import br.com.deveficiente.bookstore.autor.Autor;
import br.com.deveficiente.bookstore.categoria.Categoria;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
public class CadastroLivroFormTest {

    private CadastroLivroForm form = new CadastroLivroForm(
            "titulo",
            "resumo",
            "sumario",
            new BigDecimal("10.0"),
            10,
            "isbn",
            LocalDate.now().plusDays(1),
            1L,
            1L
    );


    @DisplayName("Deve criar livro com sucesso!")
    @Test
    public void teste1() {

        EntityManager em = Mockito.mock(EntityManager.class);

        Mockito.when(em.find(Categoria.class, 1L)).thenReturn(new Categoria("informatica"));
        Mockito.when(em.find(Autor.class, 1L)).thenReturn(new Autor("Uncle Bob", "bob_uncle@email.com", "Só mais um programador..."));

        Livro model = form.toModel(em);

        Assert.notNull(model, "não deveria ser nulo");
        Assertions.assertEquals("titulo", model.getTitulo());
        Assertions.assertEquals("resumo", model.getResumo());
        Assertions.assertEquals("sumario", model.getSumario());
        Assertions.assertEquals(new BigDecimal("10.0"), model.getPreco());
        Assertions.assertEquals(10, model.getQuantidadeDePaginas());
        Assertions.assertEquals("isbn", model.getIsbn());
        Assertions.assertEquals(LocalDate.now().plusDays(1).getDayOfMonth(), model.getDataPublicacao().getDayOfMonth());

        Mockito.verify(em, Mockito.times(2));
    }

    @DisplayName("nao deve criar livro caso a categoria nao exista!")
    @Test
    public void teste2() {

        EntityManager em = Mockito.mock(EntityManager.class);

        Mockito.when(em.find(Categoria.class, 1L)).thenReturn(null);
        Mockito.when(em.find(Autor.class, 1L)).thenReturn(new Autor("", "", ""));

        Assertions.assertThrows(IllegalStateException.class, () -> form.toModel(em));

        Mockito.verify(em, Mockito.times(2));
    }

    @DisplayName("nao deve criar livro caso o autor nao exista!")
    @Test
    public void teste3() {

        EntityManager em = Mockito.mock(EntityManager.class);

        Mockito.when(em.find(Categoria.class, 1L)).thenReturn(new Categoria(""));
        Mockito.when(em.find(Autor.class, 1L)).thenReturn(null);

        Assertions.assertThrows(IllegalStateException.class, () -> form.toModel(em));

        Mockito.verify(em, Mockito.times(2));
    }

}
