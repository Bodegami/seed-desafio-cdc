package br.com.deveficiente.bookstore.validadores;

import br.com.deveficiente.bookstore.categoria.CategoriaRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueCategoriaValidator implements ConstraintValidator<UniqueCategoria, String> {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public boolean isValid(String categoriaNome, ConstraintValidatorContext constraintValidatorContext) {
        boolean categoriaIsValid = categoriaRepository.existsByNome(categoriaNome);

        return !categoriaIsValid;
    }
}
