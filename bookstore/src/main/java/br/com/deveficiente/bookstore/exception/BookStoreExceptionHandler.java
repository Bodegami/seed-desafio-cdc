package br.com.deveficiente.bookstore.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
      ErrorResponse error =  new ErrorResponse(HttpStatus.NOT_FOUND.value(), LocalDateTime.now().toString(), errors);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
   }

}
