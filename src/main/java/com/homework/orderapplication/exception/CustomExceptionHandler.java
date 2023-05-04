package com.homework.orderapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomError> handleCustomException(CustomException exception) {
        CustomError error = new CustomError(exception.getMessage(), exception.getHttpStatus());
        return ResponseEntity.status(error.getHttpStatus()).body(error);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<CustomErrorMap> handleArgumentValidationAndTypeMismatchException(Exception ex) {
        Map<String, String> errorMap = new HashMap<>();
        if (ex instanceof MethodArgumentNotValidException) {
            for (FieldError error : ((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
        } else if (ex instanceof MethodArgumentTypeMismatchException) {
            errorMap.put(((MethodArgumentTypeMismatchException) ex).getName(), "Invalid argument type");
        }
        return ResponseEntity.badRequest().body(new CustomErrorMap(errorMap));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<CustomErrorMap> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errorMap = new HashMap<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            String field = violation.getPropertyPath().toString().split("\\.")[1];
            String message = violation.getMessage();
            errorMap.put(field, message);
        }
        return ResponseEntity.badRequest().body(new CustomErrorMap(errorMap));
    }
}
