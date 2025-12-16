package com.zulfan.personal_web.adivces;

import com.zulfan.personal_web.exceptions.DuplicateResourceException;
import com.zulfan.personal_web.exceptions.ResourceNotFoundException;
import com.zulfan.personal_web.exceptions.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handlerResourceNotFoundException(ResourceNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        Map.of(
                                "status", HttpStatus.NOT_FOUND.value(),
                                "error", "Not Found",
                                "message", ex.getMessage()
                        )
                );
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<?> handlerDuplicateException(DuplicateResourceException ex){
        Map<String, List<String>> errors = Map.of(
                ex.getField(), List.of(ex.getMessage())
        );

        return ResponseEntity.status(409).body(
                Map.of(
                        "status", 409,
                        "message", "Data duplicate",
                        "errors", errors
                )
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<?> handlerValidationException(MethodArgumentNotValidException ex){
        Map<String, List<String>> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(err -> {
            String field = err.getField();
            String message = err.getDefaultMessage();

            errors.computeIfAbsent(field, f -> new ArrayList<>()).add(message);
        });

        return ResponseEntity.badRequest().body(
                Map.of(
                        "status", 400,
                        "message", "Validation error",
                        "errors", errors
                )
        );
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handlerBadRequestException(BadRequestException ex){
        return ResponseEntity.badRequest().body(
                Map.of(
                        "status", 400,
                        "message", ex.getMessage(),
                        "errors", "bad request"
                )
        );
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handlerRuntimeException(RuntimeException ex){
        return ResponseEntity.internalServerError().build();
    }
}
