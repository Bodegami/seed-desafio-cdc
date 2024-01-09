package br.com.deveficiente.bookstore.validadores;

import br.com.deveficiente.bookstore.autor.AutorRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        boolean emailIsValid = autorRepository.existsByEmail(email);
        return  !emailIsValid;
    }
}
