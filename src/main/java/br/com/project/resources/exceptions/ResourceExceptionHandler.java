package br.com.project.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.project.services.exceptions.AuthorizationException;
import br.com.project.services.exceptions.DataIntegrityException;
import br.com.project.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException e,
			HttpServletRequest request) {

		String error = "Recurso not Found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> DataIntegrityException(DataIntegrityException e,
			HttpServletRequest request) {

		String error = "Erro de integridade com o modelo de dados";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> MethodArgumentNotValidException(MethodArgumentNotValidException e,
			HttpServletRequest request) {

		String error = "Erro de integridade com o modelo de dados";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ValidationError err = new ValidationError(Instant.now(), status.value(), error, "Erro de validação",
				request.getRequestURI());
		
		for(FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<StandardError> DataIntegrityException(AuthorizationException e,
            HttpServletRequest request) {

        String error = "Erro de autorização";
        HttpStatus status = HttpStatus.FORBIDDEN;
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