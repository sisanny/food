package com.homework.orderapplication.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class CustomError {
    private String message;
    private HttpStatus httpStatus;
}
