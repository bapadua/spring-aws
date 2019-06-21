package br.com.bapadua.aws.resource.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.bapadua.aws.exception.NotAllowedException;
import br.com.bapadua.aws.exception.NotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ApiError> handlerNotFoundException(NotFoundException ex) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND.value(), ex.getMessage(), new Date());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}

	@ExceptionHandler(NotAllowedException.class)
	public ResponseEntity<ApiError> handlerNotAllowed(NotAllowedException ex) {
		ApiError apiError = new ApiError(HttpStatus.FORBIDDEN.value(), ex.getMessage(), new Date());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiError);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = new ArrayList<String>();
		
		ex.getBindingResult().getAllErrors().forEach(erro ->{
			errors.add(erro.getDefaultMessage());
		});
		
		String defaultMessage = "Invalid Fields";
		ApiErrorList error = new ApiErrorList(HttpStatus.BAD_REQUEST.value(), defaultMessage, new Date(), errors);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}
