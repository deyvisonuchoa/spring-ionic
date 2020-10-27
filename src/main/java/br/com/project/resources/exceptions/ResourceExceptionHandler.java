package br.com.project.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.project.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException e,
			HttpServletRequest request) {

		String error = "Resource not Found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

//	@ExceptionHandler(DatabaseException.class)
//	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
//		String error = "Database Error";
//		HttpStatus status = HttpStatus.BAD_REQUEST;
//		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
//				request.getRequestURI());
//
//		return ResponseEntity.status(status).body(err);
//	}
//
//	@ExceptionHandler(BusinessException.class)
//	public ResponseEntity<StandardError> business(BusinessException e, HttpServletRequest request) {
//		String error = "Bussiness Logic Error";
//		HttpStatus status = HttpStatus.BAD_REQUEST;
//		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
//				request.getRequestURI());
//
//		return ResponseEntity.status(status).body(err);
//	}
}