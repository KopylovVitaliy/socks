package com.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundSocks extends RuntimeException{
    public NotFoundSocks(String color) {
        super(color + " socks not found");
    }
}
