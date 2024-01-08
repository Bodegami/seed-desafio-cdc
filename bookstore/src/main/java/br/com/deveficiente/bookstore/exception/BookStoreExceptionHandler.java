package br.com.deveficiente.bookstore.exception;

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
   public ResponseEntity<ErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
      List<FieldError> errors = new ArrayList<>();
      ex.getBindingResult()
              .getFieldErrors()
              .forEach(e -> {
         errors.add(new FieldError(e.getField(), e.getDefaultMessage()));
      });

      ErrorResponse error =  new ErrorResponse(ex.getStatusCode().value(), LocalDateTime.now().toString(), errors);
      return ResponseEntity.badRequest().body(error);
   }

}
