package br.com.devdojo.awesome.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.devdojo.awesome.error.ResourceNotFoundDetails;
import br.com.devdojo.awesome.error.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handlerResourceNotFoundException(ResourceNotFoundException rfnException){
		ResourceNotFoundDetails rfnDetails = ResourceNotFoundDetails.Builder
				.newBuilder()
				.timeStamp(new Date().getTime())
				.status(HttpStatus.NOT_FOUND.value())
				.title("Resource not found")
				.detail(rfnException.getMessage())
				.developerMessage(rfnException.getClass().getName()).build();
		
		return new ResponseEntity<>(rfnDetails, HttpStatus.NOT_FOUND);
	}
	
}
