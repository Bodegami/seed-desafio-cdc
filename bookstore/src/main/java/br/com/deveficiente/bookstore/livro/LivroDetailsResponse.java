package br.com.deveficiente.bookstore.livro;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LivroDetailsResponse(
        String titulo,
        String resumo,
        String sumario,
        BigDecimal preco,
        Integer quantidadeDePaginas,
        String isbn,
        LocalDate dataPublicacao,
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
                livro.getDataPublicacao(),
                livro.getAutor().getNome(),
                livro.getAutor().getDescricao()
        );
    }

}
