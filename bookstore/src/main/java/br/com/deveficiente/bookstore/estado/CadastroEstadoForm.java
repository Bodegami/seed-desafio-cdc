package br.com.deveficiente.bookstore.estado;

import br.com.deveficiente.bookstore.example.UniqueValueAlberto;
import br.com.deveficiente.bookstore.pais.Pais;
import br.com.deveficiente.bookstore.validadores.IsValid;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.validation.constraints.NotBlank;

public record CadastroEstadoForm(
        @NotBlank
        @UniqueValueAlberto(domainClass = Estado.class, fieldName = "nome", message = "Estado já cadastrado!")
        String nome,
        @IsValid(domainClass = Pais.class, fieldName = "nome", message = "Pais não encontrado!")
        String pais
) {
    public Estado toModel(EntityManager em) {
        Query query = em.createQuery("SELECT p FROM Pais p WHERE p.nome = :value");
        query.setParameter("value", pais.toLowerCase());
        Pais paisEntity = (Pais) query.getResultList().get(0);
        return new Estado(nome.toLowerCase(), paisEntity);
    }
}
