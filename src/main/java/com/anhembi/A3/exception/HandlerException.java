package com.anhembi.A3.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler(QueueException.class)
    public ResponseEntity queueException(QueueException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(400, e.getMessage()));
    }

    @ExceptionHandler(StackException.class)
    public ResponseEntity stackException(StackException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(400, e.getMessage()));
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity userException(UserException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(400, e.getMessage()));
    }
}
