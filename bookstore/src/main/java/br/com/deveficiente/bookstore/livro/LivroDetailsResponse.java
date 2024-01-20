package br.com.deveficiente.bookstore.livro;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

public record LivroDetailsResponse(
        String titulo,
        String resumo,
        String sumario,
        BigDecimal preco,
        Integer quantidadeDePaginas,
        String isbn,
        String dataPublicacao,
        String autorNome,
        String autorDescricao
) {

    public LivroDetailsResponse(Livro livro) {
        this(
                livro.getTitulo(),
                livro.getResumo(),
                livro.getSumario(),
                livro.getPreco(),
                livro.getQuantidadeDePaginas(),
                livro.getIsbn(),
                livro.getDataPublicacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                livro.getAutor().getNome(),
                livro.getAutor().getDescricao()
        );
    }

}
