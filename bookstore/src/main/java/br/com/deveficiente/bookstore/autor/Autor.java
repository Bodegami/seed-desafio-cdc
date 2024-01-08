package br.com.deveficiente.bookstore.autor;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String descricao;
    private LocalDateTime criadoEm;

    public Autor() {
    }

    public Autor(String nome, String email, String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
        this.criadoEm = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", descricao='" + descricao + '\'' +
                ", criadoEm=" + criadoEm +
                '}';
    }
}
