package br.com.deveficiente.bookstore.pais;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paises")
public class PaisController {

    @PersistenceContext
    private EntityManager em;

    @PostMapping
    @Transactional
    public ResponseEntity<Void> cadastraPais(@RequestBody @Valid CadastroPaisForm form) {
        Pais pais = form.toModel();

        em.persist(pais);

        return ResponseEntity.ok().build();
    }

}
