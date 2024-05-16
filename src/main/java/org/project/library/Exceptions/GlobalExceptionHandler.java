package org.project.library.Exceptions;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String ,String>> handleValidationExceptions(MethodArgumentNotValidException exception)
    {
        Map<String , String> errors = new LinkedHashMap<>() ;
        exception.getBindingResult().getAllErrors()
                .forEach(error -> {
                    String fieldName = ((FieldError) error).getField() ;
                    String errorMessage = error.getDefaultMessage() ;
                    errors.put(fieldName , errorMessage);
                });

        return new ResponseEntity<>(errors , HttpStatus.BAD_REQUEST) ;
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException badRequestException)
    {
        return new ResponseEntity<>(badRequestException.getMessage() , HttpStatus.BAD_REQUEST) ;
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<String> handleDataNotFoundException(DataNotFoundException dataNotFoundException)
    {
        return new ResponseEntity<>(dataNotFoundException.getMessage() , HttpStatus.NOT_FOUND) ;
    }
}
