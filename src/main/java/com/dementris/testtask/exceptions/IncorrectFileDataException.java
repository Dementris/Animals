package com.dementris.testtask.exceptions;

import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncorrectFileDataException extends ValidationException {
    public IncorrectFileDataException() {
        super("File data can't be parsed");
    }
}
