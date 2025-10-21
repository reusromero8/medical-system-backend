package com.health.exception;

import org.springframework.beans.factory.parsing.Problem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;
import java.time.LocalDateTime;

@RestControllerAdvice // Interceptar excepcion
public class ResponseExceptionHandler {

    /**
     * Maneja excepciones cuando un modelo no es encontrado
     */
    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<CustomErrorRecord> handleModelNotFoundException(
            ModelNotFoundException ex, WebRequest request) {

        CustomErrorRecord errorRecord = new CustomErrorRecord(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorRecord, HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja excepciones de validación de negocio
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<CustomErrorRecord> handleIllegalArgumentException(
            IllegalArgumentException ex, WebRequest request) {

        CustomErrorRecord errorRecord = new CustomErrorRecord(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorRecord, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja excepciones aritméticas
     */
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<CustomErrorRecord> handleArithmeticException(
            ArithmeticException ex, WebRequest request) {

        CustomErrorRecord err = new CustomErrorRecord(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(err, HttpStatus.NOT_ACCEPTABLE);
    }

    /**
     * Maneja cualquier otra excepción no capturada
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorRecord> handleGlobalException(
            Exception ex, WebRequest request) {

        CustomErrorRecord errorRecord = new CustomErrorRecord(
                LocalDateTime.now(),
                "An unexpected error occurred: " + ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorRecord, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
