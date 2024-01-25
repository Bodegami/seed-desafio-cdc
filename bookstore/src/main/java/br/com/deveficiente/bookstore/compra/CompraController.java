package br.com.deveficiente.bookstore.compra;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/compras")
public class CompraController {

    //1
    @PersistenceContext
    private EntityManager em;

    //1
    @PostMapping
    @Transactional
    public ResponseEntity<Void> cadastraCompra(@RequestBody @Valid NovaCompraForm form) {
        //1
        Compra compra = form.toModel(em);

        em.persist(compra);
        em.close();

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(compra.getId())
                .toUri();
        System.out.println();

        return ResponseEntity.created(uri).build();
    }

}
