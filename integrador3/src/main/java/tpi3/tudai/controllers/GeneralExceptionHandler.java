package tpi3.tudai.controllers;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import tpi3.tudai.dtos.ErrorDTO;

@RestControllerAdvice( basePackages = {"tpi3.tudai.controllers"})
public class GeneralExceptionHandler {
	
	@ExceptionHandler( NotFoundException.class )
	public ResponseEntity<ErrorDTO> get( NotFoundException ex  ) {
		return new ResponseEntity( new ErrorDTO( ex.getMessage() ), HttpStatus.BAD_REQUEST );
	}

}
