package com.javanauta.bff_agendadortarefas.controller;

import com.javanauta.bff_agendadortarefas.infrastructure.exceptions.ConflictException;
import com.javanauta.bff_agendadortarefas.infrastructure.exceptions.IlegalArgumentException;
import com.javanauta.bff_agendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.javanauta.bff_agendadortarefas.infrastructure.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<String> handleConflicException(ConflictException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> handleUnauthorizedException(UnauthorizedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(IlegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IlegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
