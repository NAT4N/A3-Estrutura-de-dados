package com.anhembi.demo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler(FilaVaziaException.class)
    public ResponseEntity filaVazia(FilaVaziaException e) {
        return ResponseEntity.badRequest().body(new ErrorRepsonse(400, e.getMessage()));
    }

    @ExceptionHandler(PilhaVaziaException.class)
    public ResponseEntity pilhaVazia(PilhaVaziaException e) {
        return ResponseEntity.badRequest().body(new ErrorRepsonse(400, e.getMessage()));
    }
}
