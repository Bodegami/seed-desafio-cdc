package br.com.deveficiente.bookstore.exception;

import java.util.List;

public record ErrorResponse(int status, String date, List<FieldError> errors) {
}
