package br.com.deveficiente.bookstore.compra;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/compras")
public class DetalhesCompraController {

    @Autowired
    private CompraRepository compraRepository;

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesCompraResponse> detalhesCompra(@PathVariable Long id) {
        Optional<Compra> optCompra = compraRepository.findById(id);

        DetalhesCompraResponse detalhesCompraResponse = new DetalhesCompraResponse(optCompra.get());

        return ResponseEntity.ok(detalhesCompraResponse);
    }
}
