package br.com.deveficiente.bookstore.compra;

import br.com.deveficiente.bookstore.cupom.Cupom;
import br.com.deveficiente.bookstore.exception.CupomInvalidoException;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

@Entity
@Table(name = "compras")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String sobrenome;
    @Column(nullable = false)
    private String documento;

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String telefone;

    //1
    @Embedded
    private Endereco endereco;

    //1
    @OneToOne(mappedBy = "compra", cascade = CascadeType.PERSIST)
    private Pedido pedido;

    //1
    @Embedded
    private CupomAplicado cupom;

    @Deprecated
    public Compra() {
    }

    public Compra(String nome, String sobrenome, String documento, String email, String telefone,
                  Endereco endereco, Function<Compra, Pedido> funcaoCriacaoPedido) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.pedido = funcaoCriacaoPedido.apply(this);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public CupomAplicado getCupom() {
        return cupom;
    }

    public void aplicaCupom(EntityManager em, String codigo) {
        //Assert.isNull(this.cupom, "");
        //1
        Cupom cupom;
        //1
        CupomAplicado cupomAplicado = null;

        //1
        if (codigo != null) {

            List resultList = em.createQuery("Select c From Cupom c Where c.codigo = :value")
                    .setParameter("value", codigo.toLowerCase())
                    .getResultList();

            //1
            if (resultList.isEmpty()) {
                throw new CupomInvalidoException("Codigo de cupom inválido: " + codigo);
            }

            cupom = (Cupom) resultList.get(0);

            cupomAplicado = new CupomAplicado(cupom);
        }

        this.cupom = cupomAplicado;
    }

    public BigDecimal calculaValorFinal() {
        double totalSemDesconto = pedido.getItens().stream()
                .mapToDouble(item -> item.getPrecoDoMomento().doubleValue() * item.getQuantidade())
                .sum();

        if (cupom != null) {
            Double percentualDoMomentoDaCompra = cupom.getCupom().getPercentualDesconto();
            return new BigDecimal(totalSemDesconto - ((percentualDoMomentoDaCompra / 100) * totalSemDesconto));
        }

        return new BigDecimal(totalSemDesconto);
    }

}
