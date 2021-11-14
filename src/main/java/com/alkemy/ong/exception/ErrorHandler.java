package com.alkemy.ong.exception;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorHandler  extends ResponseEntityExceptionHandler{


    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<?> handleEmailAlreadyExist(HttpServletRequest request, EmailAlreadyExistException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFoundException(NotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<?> invalidCredentials(InvalidCredentialsException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
    }
    
    @ExceptionHandler(DataAlreadyExistException.class)
    public ResponseEntity<?> handleDataAlreadyExistException(HttpServletRequest request, DataAlreadyExistException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest webRequest) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : e.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        
        return handleExceptionInternal(e,errors, headers, HttpStatus.BAD_REQUEST, webRequest);
    }

    @ExceptionHandler(value= EntityNotFoundException.class)
	protected ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e, WebRequest webRequest){
		
		String message= "The entity does not exists:" + e.getMessage();
		
		return handleExceptionInternal(e, message, new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);
	}
}
