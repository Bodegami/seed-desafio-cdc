package br.com.deveficiente.bookstore.livro;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @PersistenceContext
    private EntityManager em;

    @PostMapping
    @Transactional
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid CadastroLivroForm form) {

        Livro model = form.toModel(em);
        em.persist(model);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity<List<LivroResponse>> listarLivros() {

        String query = "SELECT l FROM " + Livro.class.getSimpleName() + " l";
        List<Livro> resultList = em.createQuery(query, Livro.class).getResultList();
        List<LivroResponse> livrosResponse = resultList.stream().map(LivroResponse::new).toList();

        return ResponseEntity.ok(livrosResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDetailsResponse> buscaPeloId(@PathVariable Long id) {

        Livro livro = em.find(Livro.class, id);
        if (livro == null) {
            throw new EntityNotFoundException(String.format("Livro de id: %d - não encontrado", id));
        }
        LivroDetailsResponse response = new LivroDetailsResponse(livro);

        return ResponseEntity.ok(response);
    }

}
