package com.example.Java_May_Spring.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandling {


    private static final Logger logger =  LoggerFactory.getLogger(GlobalExceptionHandling.class);
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<String> handleArithmeticException(ArithmeticException exception){
        logger.warn("ArithmeticException is  called");
        return ResponseEntity.badRequest().body("An arithmetic exception occured: " + exception.getMessage());
    }
}
