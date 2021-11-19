package com.lucasladeira.workshopspring.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lucasladeira.workshopspring.services.exceptions.DataIntegrityException;
import com.lucasladeira.workshopspring.services.exceptions.ObjectNotFoundException;

//responsavel em interceptar as exceções
@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<BodyError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		BodyError err = new BodyError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(err);
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<BodyError> dataIntegrity(DataIntegrityException e, HttpServletRequest request){
		
		BodyError err = new BodyError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<BodyError> argumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request){
		
		ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validação", System.currentTimeMillis());
		
		for(FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(err);
	}
	
}
