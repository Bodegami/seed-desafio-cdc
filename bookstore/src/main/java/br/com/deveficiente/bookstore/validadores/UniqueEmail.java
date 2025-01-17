package br.com.deveficiente.bookstore.validadores;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Constraint(validatedBy = UniqueEmailValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UniqueEmail {

    String message() default "Email já cadastrado!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
