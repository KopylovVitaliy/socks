package com.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NotQuantityException extends RuntimeException{
    public NotQuantityException(int quantity) {
        super("Quantity is:" + quantity);
    }
}
