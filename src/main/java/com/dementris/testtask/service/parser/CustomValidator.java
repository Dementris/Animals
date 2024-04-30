package com.dementris.testtask.service.parser;

import com.dementris.testtask.exceptions.IncorrectFileDataException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * AnimalValidator
 */
@Service
public class CustomValidator {

    /**
     * Method validate() validate objects in Iterator.
     *
     * @param it Iterator
     * @param <T> type
     * @return List<T> list of entities.
     */
    public <T> List<T> validate(Iterator<T> it) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        List<T> entities = new ArrayList<>();
        while (it.hasNext()){
            T next = it.next();
            Set<ConstraintViolation<T>> violations = validator.validate(next);
            if (!violations.isEmpty()) {
                continue;
            }
            entities.add(next);
        }
        return entities;
    }
}
