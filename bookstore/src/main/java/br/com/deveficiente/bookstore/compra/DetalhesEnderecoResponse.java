package br.com.deveficiente.bookstore.compra;

public record DetalhesEnderecoResponse(
        String endereco,
        String complemento,
        String cidade,
        String pais,
        String estado,
        String cep
) {

    public DetalhesEnderecoResponse(Endereco entity) {
        this(
                entity.getEndereco(),
                entity.getComplemento(),
                entity.getCidade(),
                entity.getPais().toUpperCase(),
                entity.getEstado().toUpperCase(),
                entity.getCep()
        );
    }

}
