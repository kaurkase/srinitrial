package kaur.srini.srinitrial.web.controllers;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import kaur.srini.srinitrial.web.ApiError;

@ControllerAdvice
public class RestResponseEntityExceptionHandler 
  extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                  HttpHeaders headers, HttpStatus status, WebRequest request) {
    	ApiError error = new ApiError("validation failed");
    	ex.getBindingResult().getFieldErrors().stream()
    			.forEach(fieldError -> error.getValidationErrors().put(fieldError.getField(), fieldError.getDefaultMessage()));
    	return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}