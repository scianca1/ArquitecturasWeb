package com.example.microusuarios.web.rest;

import com.example.microusuarios.ecxeptions.ErrorDTO;
import com.example.microusuarios.ecxeptions.NotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice( basePackages = "com.example.web.rest" )
public class GeneralExceptionHandler {

    @ExceptionHandler( NotFoundException.class )
    public ErrorDTO getException(NotFoundException ex ){
        return new ErrorDTO( ex.getCode(), ex.getMessage() );
    }
}