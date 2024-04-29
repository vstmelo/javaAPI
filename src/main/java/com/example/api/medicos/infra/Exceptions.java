package com.example.api.medicos.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Exceptions {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity exception(){
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity exception400(MethodArgumentNotValidException ex){
        var error =  ex.getFieldErrors();
        return ResponseEntity.badRequest().body(error.stream().map(dataErrorValidation::new).toList());
    }
    private record dataErrorValidation(String data, String message){
        public dataErrorValidation(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }

}