package br.com.deveficiente.bookstore.validadores;

import br.com.deveficiente.bookstore.autor.AutorRepository;
import br.com.deveficiente.bookstore.categoria.CategoriaRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, String> {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public boolean isValid(String request, ConstraintValidatorContext constraintValidatorContext) {

        boolean isValidAutor = autorRepository.existsByEmail(request.trim());
        boolean isValidCategoria = categoriaRepository.existsByNome(request.trim());

        return !isValidAutor && !isValidCategoria;
    }
}
