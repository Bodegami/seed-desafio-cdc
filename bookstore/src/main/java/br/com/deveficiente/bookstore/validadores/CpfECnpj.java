package br.com.deveficiente.bookstore.validadores;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import java.lang.annotation.*;


@CPF
@CNPJ
@Constraint(validatedBy = {})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ConstraintComposition(CompositionType.OR)
public @interface CpfECnpj {

    String message() default "Categoria já cadastrada!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
