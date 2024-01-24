package br.com.deveficiente.bookstore.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class BookStoreExceptionHandler {

   @ExceptionHandler(value = {MethodArgumentNotValidException.class})
   public ResponseEntity<ErrorResponse> methodArgumentNotValidException(
           MethodArgumentNotValidException ex, WebRequest request) {

      List<FieldError> errors = new ArrayList<>();
      ex.getBindingResult()
              .getFieldErrors()
              .forEach(e -> errors.add(new FieldError(e.getField(), e.getDefaultMessage())));

      ErrorResponse error =  new ErrorResponse(ex.getStatusCode().value(), LocalDateTime.now().toString(), errors);
      return ResponseEntity.badRequest().body(error);
   }

   @ExceptionHandler(value = {EntityNotFoundException.class})
   public ResponseEntity<ErrorResponse> entityNotFoundException(
           EntityNotFoundException ex, WebRequest request) {

      List<FieldError> errors = new ArrayList<>();
      errors.add(new FieldError("id", ex.getMessage()));
      ErrorResponse error =  new ErrorResponse(NOT_FOUND.value(), LocalDateTime.now().toString(), errors);
      return ResponseEntity.status(NOT_FOUND).body(error);
   }

   @ExceptionHandler(value = {EstadoNaoPertenceAPaisException.class})
   public ResponseEntity<ErrorResponse> estadoNaoPertenceAPaisException(
           EstadoNaoPertenceAPaisException ex, WebRequest request) {

      List<FieldError> errors = new ArrayList<>();
      errors.add(new FieldError("estado", ex.getMessage()));
      ErrorResponse error =  new ErrorResponse(BAD_REQUEST.value(), LocalDateTime.now().toString(), errors);
      return ResponseEntity.status(BAD_REQUEST).body(error);
   }

   @ExceptionHandler(value = {CompraComValorDivergenteException.class})
   public ResponseEntity<ErrorResponse> compraComValorDivergenteException(
           CompraComValorDivergenteException ex, WebRequest request) {

      List<FieldError> errors = new ArrayList<>();
      errors.add(new FieldError("total", ex.getMessage()));
      ErrorResponse error =  new ErrorResponse(UNPROCESSABLE_ENTITY.value(), LocalDateTime.now().toString(), errors);
      return ResponseEntity.status(UNPROCESSABLE_ENTITY).body(error);
   }

   @ExceptionHandler(value = {CupomInvalidoException.class})
   public ResponseEntity<ErrorResponse> cupomInvalidoException(
           CupomInvalidoException ex, WebRequest request) {

      List<FieldError> errors = new ArrayList<>();
      errors.add(new FieldError("cupom", ex.getMessage()));
      ErrorResponse error =  new ErrorResponse(BAD_REQUEST.value(), LocalDateTime.now().toString(), errors);
      return ResponseEntity.status(BAD_REQUEST).body(error);
   }

}
