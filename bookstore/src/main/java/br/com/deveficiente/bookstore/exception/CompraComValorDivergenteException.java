package br.com.deveficiente.bookstore.exception;

public class CompraComValorDivergenteException extends RuntimeException {
    public CompraComValorDivergenteException(String msg) {
        super(msg);
    }
}
