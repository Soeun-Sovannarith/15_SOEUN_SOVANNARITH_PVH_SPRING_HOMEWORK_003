package org.example.spring_homework_003.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.example.spring_homework_003.models.reponses.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .detail(ex.getMessage())
                .instance(request.getRequestURI())
                .status(404)
                .title("Not Found")
                .type("http://localhost:8080/errors/not-found")
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(404).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        ErrorResponse errorResponse = ErrorResponse.builder()
                .instance(request.getRequestURI())
                .status(400)
                .title("Bad Request")
                .timestamp(Instant.now())
                .errors(errors)
                .build();

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();

        ex.getConstraintViolations().forEach(violation -> {
            String propertyPath = violation.getPropertyPath().toString();
            String paramName = propertyPath.substring(propertyPath.lastIndexOf('.') + 1);
            errors.put(paramName, violation.getMessage());
        });

        ErrorResponse errorResponse = ErrorResponse.builder()
                .instance(request.getRequestURI())
                .status(400)
                .title("Bad Request")
                .timestamp(Instant.now())
                .errors(errors)
                .build();

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(DuplicationException.class)
    public ResponseEntity<ErrorResponse> handleDuplicationException(DuplicationException ex, HttpServletRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .detail(ex.getMessage())
                .instance(request.getRequestURI())
                .status(409)
                .title("Conflict")
                .type("http://localhost:8080/errors/duplicate-user")
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(409).body(errorResponse);
    }

}
