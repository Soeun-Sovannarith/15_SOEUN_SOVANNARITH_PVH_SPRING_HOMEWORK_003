package org.example.spring_homework_003.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.example.spring_homework_003.models.reponses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;



@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        ApiResponse apiResponse = ApiResponse.builder()
                .detail(ex.getMessage())
                .instance(request.getRequestURI())
                .status(404)
                .title("Not Found")
                .type("http://localhost:8080/errors/not-found")
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(404).body(apiResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>>handleValidationException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(DuplicationException.class)
    public ResponseEntity<ApiResponse> handleDuplicationException(DuplicationException ex, HttpServletRequest request) {
        ApiResponse apiResponse = ApiResponse.builder()
                .detail(ex.getMessage())
                .instance(request.getRequestURI())
                .status(409)
                .title("Conflict")
                .type("http://localhost:8080/errors/duplicate-user")
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(409).body(apiResponse);
    }

}
