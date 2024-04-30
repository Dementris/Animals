package com.dementris.testtask.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AnimalNotFoundException extends RuntimeException{
    public AnimalNotFoundException() {
        super("No animals found by these criteria");
    }
}
