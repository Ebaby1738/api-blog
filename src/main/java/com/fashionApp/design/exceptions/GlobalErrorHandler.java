package com.fashionApp.design.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException (final ResourceNotFoundException e){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setDebugMessage("please check your login details and try again");

        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<?> handleResourceAlreadyExistException (final ResourceAlreadyExistException e){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setDebugMessage("please check your login details and try again");

        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(CustomAppException.class)
    public ResponseEntity<?> handleCustomAppException (final CustomAppException e){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setDebugMessage("please check your login details and try again");

        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> handleUnauthorizedException (final UnauthorizedException e){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setDebugMessage("you are not authorized to make this request");

        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(NotNullException.class)
    public ResponseEntity<?> handleNotNullException (final NotNullException e){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setDebugMessage("field cannot be emmpty, please check fill the appropriate field before proceeding");

        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }



}
