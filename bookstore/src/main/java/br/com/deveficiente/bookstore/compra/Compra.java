package br.com.deveficiente.bookstore.compra;

import jakarta.persistence.*;

import java.util.function.Function;

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
    @OneToOne(mappedBy = "compra", cascade = CascadeType.PERSIST)
    private Pedido pedido;

    @Deprecated
    public Compra() {
    }

    public Compra(String nome, String sobrenome, String documento, String email, String telefone, Endereco endereco, Function<Compra, Pedido> funcaoCriacaoPedido) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.pedido = funcaoCriacaoPedido.apply(this);
    }

}
