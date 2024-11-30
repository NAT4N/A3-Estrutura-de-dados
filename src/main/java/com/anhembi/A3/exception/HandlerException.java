package com.anhembi.A3.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler(QueueException.class)
    public ResponseEntity queueException(QueueException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(400, e.getMessage()));
    }

    @ExceptionHandler(StackException.class)
    public ResponseEntity stackException(StackException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(400, e.getMessage()));
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity userException(UserException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(400, e.getMessage()));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity noResourceFoundException(NoResourceFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity errorJsonProcessingException(JsonProcessingException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(500, "Erro ao processar JSON"));
    }
}
