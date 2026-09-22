package com.example.exception;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorApi> handleException(ProductNotFoundException ex){
		ErrorApi errorApi=new ErrorApi();
		errorApi.setLocalDateTime(LocalDateTime.now());
		errorApi.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
		errorApi.setMessage("Validation Error");
		errorApi.setError(ex.getMessage());
		return new ResponseEntity<>(errorApi, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorApi> handleException(Exception ex){
		ErrorApi errorApi=new ErrorApi();
		errorApi.setLocalDateTime(LocalDateTime.now());
		errorApi.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		errorApi.setMessage("Something went wrong");
		errorApi.setError(ex.getMessage());
		return new ResponseEntity<>(errorApi, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorApi> handleException(HttpMessageNotReadableException ex){
		ErrorApi errorApi=new ErrorApi();
		errorApi.setLocalDateTime(LocalDateTime.now());
		errorApi.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
		errorApi.setMessage("Malformed JSON data");
		errorApi.setError(ex.getMessage());
		return new ResponseEntity<>(errorApi, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorApi> handleException(MethodArgumentNotValidException ex){
		
		String errors = ex.getBindingResult()
							.getFieldErrors()
							.stream()
							.map(obj->obj.getField()+":"+obj.getDefaultMessage())
							.collect(Collectors.joining(","));
		
		
		ErrorApi errorApi=new ErrorApi();
		errorApi.setLocalDateTime(LocalDateTime.now());
		errorApi.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
		errorApi.setMessage("Client side validation error");
		errorApi.setError(errors);
		return new ResponseEntity<>(errorApi, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorApi> handleException(ResourceNotFoundException ex){
		ErrorApi errorApi=new ErrorApi();
		errorApi.setLocalDateTime(LocalDateTime.now());
		errorApi.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		errorApi.setMessage("external service error");
		errorApi.setError(ex.getMessage());
		return new ResponseEntity<>(errorApi, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
