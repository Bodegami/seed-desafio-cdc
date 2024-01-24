package br.com.deveficiente.bookstore.exception;

public class CupomInvalidoException extends RuntimeException {
    public CupomInvalidoException(String msg) {
        super(msg);
    }
}
