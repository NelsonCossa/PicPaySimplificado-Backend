package com.devnelson.picpay_simplificado.infra;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.devnelson.picpay_simplificado.dtos.ExceptionDTO;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ControllerExceptionHandler {

	
	@ExceptionHandler(DataIntegrityViolationException.class)
		public ResponseEntity threatDuplicateEntry(DataIntegrityViolationException esception) {
		ExceptionDTO exceptionDTO=new ExceptionDTO ("Usuario ja cadastrado", "400");
		return ResponseEntity.badRequest().body(exceptionDTO);
		
	}
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity threatDuplicateEntry(EntityNotFoundException esception) {
	return ResponseEntity.notFound().build();
	
}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity threatGeneralException (Exception exception) {
		ExceptionDTO exceptionDTO=new ExceptionDTO (exception.getMessage(), "500");
		return ResponseEntity.internalServerError().body(exceptionDTO);
	}
	
}
