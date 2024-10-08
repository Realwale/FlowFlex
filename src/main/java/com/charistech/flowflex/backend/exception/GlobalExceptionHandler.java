package com.charistech.flowflex.backend.exception;


import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(Exception e){
        return buildErrorResponse(new ExceptionResponse(HttpStatus.NOT_FOUND,
                e.getMessage(), HttpStatus.NOT_FOUND.value()));
    }


    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleResourceAlreadyExistsException(Exception e){
        return buildErrorResponse(new ExceptionResponse(HttpStatus.ALREADY_REPORTED,
                e.getMessage(), HttpStatus.ALREADY_REPORTED.value()));
    }


    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidArgumentException(Exception e){
        return buildErrorResponse(new ExceptionResponse(HttpStatus.BAD_REQUEST,
                e.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }


    private ResponseEntity<ExceptionResponse> buildErrorResponse(ExceptionResponse apiErrorResponse){
        return new ResponseEntity<>(apiErrorResponse, apiErrorResponse.getStatus());

    }
}
