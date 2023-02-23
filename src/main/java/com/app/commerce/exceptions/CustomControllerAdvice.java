package com.app.commerce.exceptions;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Log4j2
@ControllerAdvice
public class CustomControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler({Exception.class})
    protected ResponseEntity<Object> handleGenericExceptions(final Exception ex) {

        var message = ex.getMessage();

        log.error("Data Exception", ex);

        var customError = HttpStatus.INTERNAL_SERVER_ERROR;

        if (ex instanceof IllegalArgumentException) {
            customError = HttpStatus.BAD_REQUEST;
        }

        return ResponseEntity.status(customError)
                .body(message);
    }
}
