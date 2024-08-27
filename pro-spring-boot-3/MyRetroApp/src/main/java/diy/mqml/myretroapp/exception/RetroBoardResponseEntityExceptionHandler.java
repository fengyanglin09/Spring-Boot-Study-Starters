package diy.mqml.myretroapp.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

//this is only for controllers

/**
 * @ControllerAdvice: This annotation uses AOP to implement the Around Advice (this means that it will intercept all the method calls defined in the controller and execute them inside a try and catch, and if there is any exception, the method handleNotFound will be called; here is where you add you own logic to handle the error), and is registered to catch any error thrown during runtime. It declares a method that is marked with the @ExceptionHandler annotation.
 * */

@ControllerAdvice
public class RetroBoardResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    /**
     * @ExceptionHandler: This annotation catches any exception declared as part of the parameter value. In this case, is telling to execute the handleNotFound method when a RetroBoardNotFoundException or CardNotFoundException is thrown.
     * */

    @ExceptionHandler(value
            = { CardNotFoundException.class,RetroBoardNotFoundException.class })
    protected ResponseEntity<Object> handleNotFound(
            RuntimeException ex, WebRequest request) {

        Map<String, Object> response = new HashMap<>();

        response.put("msg","There is an error");
        response.put("code",HttpStatus.NOT_FOUND.value());
        response.put("time", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm:ss")));

        Map<String, String> errors = new HashMap<>();
        errors.put("msg",ex.getMessage());
        response.put("errors",errors);


         //handleExceptionInternal: This method belongs to the extended class, and it will prepare all the common handling and will create the ResponseEntity.


        return handleExceptionInternal(ex, response,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
