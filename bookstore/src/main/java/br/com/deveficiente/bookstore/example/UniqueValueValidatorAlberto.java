package br.com.deveficiente.bookstore.example;

import br.com.deveficiente.bookstore.validadores.UniqueValueAlberto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.Assert;

import java.util.List;

public class UniqueValueValidatorAlberto implements ConstraintValidator<UniqueValueAlberto, Object> {

    private String domainAttribute;
    private Class<?> klass;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void initialize(UniqueValueAlberto params) {
        domainAttribute = params.fieldName();
        klass = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        Query query = em.createQuery("select 1 from " + klass.getName() + " where " + domainAttribute + " = :value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();
        Assert.state(list.size() <= 1,
                "Foi encontrado mais de um " + klass + "com o atributo " + domainAttribute + " = " + value);

        return list.isEmpty();
    }
}
