package br.com.deveficiente.bookstore.validadores;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Constraint(validatedBy = CategoriaValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UniqueCategoria {

    String message() default "Categoria já cadastrada!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
