package br.com.bapadua.aws.resource.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.bapadua.aws.exception.NotAllowedException;
import br.com.bapadua.aws.exception.NotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ApiError> handlerNotFoundException(NotFoundException ex){
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND.value(), ex.getMessage(), new Date());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	@ExceptionHandler(NotAllowedException.class)
	public ResponseEntity<ApiError> handlerNotAllowed(NotAllowedException ex){
		ApiError apiError = new ApiError(HttpStatus.FORBIDDEN.value(), ex.getMessage(), new Date());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiError);
	}
}
