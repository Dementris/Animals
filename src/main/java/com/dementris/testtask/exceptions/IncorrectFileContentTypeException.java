package com.dementris.testtask.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncorrectFileContentTypeException extends RuntimeException{
    public IncorrectFileContentTypeException() {
        super("The file content type must be text/csv or text/xml");
    }
}
