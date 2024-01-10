package br.com.deveficiente.bookstore.validadores;

import br.com.deveficiente.bookstore.example.UniqueValueValidatorAlberto;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Constraint(validatedBy = UniqueValueValidatorAlberto.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UniqueValueAlberto {

    String message() default "Atributo já cadastrado!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String fieldName();

    Class<?> domainClass();

}
