package br.com.deveficiente.bookstore.autor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    boolean existsByEmail(String email);
    boolean existsAutorByEmail(String email);

}
