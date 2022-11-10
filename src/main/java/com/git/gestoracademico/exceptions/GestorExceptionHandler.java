package com.git.gestoracademico.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@ControllerAdvice
public class GestorExceptionHandler {

    @ResponseBody
    @ExceptionHandler(GestorExceptionNotFound.class)
    public ResponseEntity<MessageExceptionHandler> notFound(GestorExceptionNotFound exception) {
        MessageExceptionHandler error = new MessageExceptionHandler(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage()
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
