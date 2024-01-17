package br.com.deveficiente.bookstore.livro;

public record LivroResponse(
        Long id,
        String titulo
) {

    public LivroResponse(Livro livro) {
        this(livro.getId(), livro.getTitulo());
    }

}
