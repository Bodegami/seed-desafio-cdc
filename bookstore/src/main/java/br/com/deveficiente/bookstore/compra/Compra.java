package br.com.deveficiente.bookstore.compra;

import jakarta.persistence.*;

@Entity
@Table(name = "compras")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String sobrenome;
    @Column(nullable = false, unique = true)
    private String documento;

    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String telefone;

    @Embedded
    private Endereco endereco;

    @Deprecated
    public Compra() {
    }

    public Compra(String nome, String sobrenome, String documento, String email, String telefone, Endereco endereco) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }
}
