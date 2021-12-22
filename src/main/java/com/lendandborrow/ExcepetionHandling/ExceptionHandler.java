package com.lendandborrow.ExcepetionHandling;

import com.lendandborrow.ExcepetionHandling.exceptions.LendingProcessServiceException;
import com.lendandborrow.ExcepetionHandling.exceptions.UserServiceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(LendingProcessServiceException.class)
    public ResponseEntity<Object> handleLendingProcessServiceException(LendingProcessServiceException ex, WebRequest req){
        return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(  UserServiceException.class)
    public ResponseEntity<Object> handleUserServiceException(  UserServiceException ex, WebRequest req){
        return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
