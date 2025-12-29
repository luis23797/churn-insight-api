package com.alura.churnnsight.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {


    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationErrorData>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {

        logger.warn("Se recibió una petición con datos de validación incorrectos. Cantidad de errores: {}", ex.getBindingResult().getErrorCount());

        var errors = ex.getFieldErrors().stream()
                .map(ValidationErrorData::new)
                .toList();

        return ResponseEntity.badRequest().body(errors);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleServiceUnavailable(RuntimeException ex) {
        if (ex.getMessage().equals("Servicio de IA no disponible")) {

            return ResponseEntity.status(503).body("Error: El servicio de predicción (Python) no está respondiendo. Intente más tarde.");
        }
        return ResponseEntity.status(500).body("Error interno del servidor");
    }

    private record ValidationErrorData(String campo, String mensaje) {
        public ValidationErrorData(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}