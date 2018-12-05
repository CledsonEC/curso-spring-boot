package br.com.devdojo.awesome.handler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.devdojo.awesome.error.ResourceNotFoundDetails;
import br.com.devdojo.awesome.error.ResourceNotFoundException;
import br.com.devdojo.awesome.error.ValidationErrorDetails;

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
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handlerMethodArgumentNotValidException(MethodArgumentNotValidException manvException){
		
		List<FieldError> fildErros = manvException.getBindingResult().getFieldErrors();
		String fields = fildErros.stream().map(FieldError::getField).collect(Collectors.joining(","));
		String fieldMessages = fildErros.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));

		ValidationErrorDetails rfnDetails = ValidationErrorDetails.Builder
				.newBuilder()
				.timeStamp(new Date().getTime())
				.status(HttpStatus.BAD_REQUEST.value())
				.title("Field Validation Error")
				.detail("Field Validation Error")
				.developerMessage(manvException.getClass().getName())
				.field(fields)
				.fieldMessage(fieldMessages)
				.build();
		
		return new ResponseEntity<>(rfnDetails, HttpStatus.BAD_REQUEST);
	}
	
}
