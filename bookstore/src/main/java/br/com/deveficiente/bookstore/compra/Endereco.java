package br.com.deveficiente.bookstore.compra;

import br.com.deveficiente.bookstore.estado.Estado;
import br.com.deveficiente.bookstore.pais.Pais;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

@Embeddable
public class Endereco {

    private String endereco;
    private String complemento;
    private String cidade;

    //1
    @ManyToOne
    private Pais pais;

    //1
    @ManyToOne
    private Estado estado;
    private String cep;

    @Deprecated
    public Endereco() {
    }

    public Endereco(String endereco, String complemento, String cidade, Pais pais, Estado estado, String cep) {
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.pais = pais;
        this.estado = estado;
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public String getPais() {
        return pais.getNome();
    }

    public String getEstado() {
        return estado.getNome();
    }

    public String getCep() {
        return cep;
    }
}
