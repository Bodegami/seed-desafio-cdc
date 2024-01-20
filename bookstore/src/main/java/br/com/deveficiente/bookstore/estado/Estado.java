package br.com.deveficiente.bookstore.estado;

import br.com.deveficiente.bookstore.pais.Pais;
import jakarta.persistence.*;

@Entity
@Table(name = "estados")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @ManyToOne
    private Pais pais;

    @Deprecated
    public Estado() {
    }

    public Estado(String nome, Pais pais) {
        this.nome = nome;
        this.pais = pais;
    }
}