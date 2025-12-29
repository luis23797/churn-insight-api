package com.alura.churnnsight.infrastructure;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Este método atrapa específicamente los errores de @Valid en los DTOs
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationErrorData>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {

        var errors = ex.getFieldErrors().stream()
                .map(ValidationErrorData::new)
                .toList();

        return ResponseEntity.badRequest().body(errors);
    }


    private record ValidationErrorData(String campo, String mensaje) {
        public ValidationErrorData(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}