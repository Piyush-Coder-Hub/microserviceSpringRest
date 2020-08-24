package piyush.springframework.msscbeerservice.exception.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<List> validateErrorHandler(ConstraintViolationException e) {

		List<String> errors = new ArrayList<>(e.getConstraintViolations().size());
		System.out.println("from error handler");
		e.getConstraintViolations().forEach(constraintVoilation -> {
			errors.add(constraintVoilation.getPropertyPath() + " : " + constraintVoilation.getMessage());
		});

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

	}

}