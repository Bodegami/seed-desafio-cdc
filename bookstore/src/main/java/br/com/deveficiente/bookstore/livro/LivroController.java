package br.com.deveficiente.bookstore.livro;

import br.com.deveficiente.bookstore.autor.Autor;
import br.com.deveficiente.bookstore.autor.AutorRepository;
import br.com.deveficiente.bookstore.categoria.Categoria;
import br.com.deveficiente.bookstore.categoria.CategoriaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @PersistenceContext
    private EntityManager em;

    @PostMapping
    @Transactional
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid CadastroLivroForm form) {
        Livro model = form.toModel(em);
        System.out.println(model);

        em.persist(model);

        return ResponseEntity.ok().build();
    }


    //TODO Falta testar atributos únicos, criar service para livros e isolar a lógica
    //TODO contagem da carga intrisica, revisar o codigo e procurar por melhorias


}
