package com.lendandborrow.ExpetionHandeling;

import com.lendandborrow.ExpetionHandeling.exceptions.LendingProcessServiceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(LendingProcessServiceException.class)
    public ResponseEntity<Object> handleUserServiceException(LendingProcessServiceException ex, WebRequest req){
        return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
