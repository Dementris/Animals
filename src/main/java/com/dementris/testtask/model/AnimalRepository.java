package com.dementris.testtask.model;

import com.dementris.testtask.service.dto.AnimalsParamsDto;

import java.util.List;


public interface AnimalRepository {
    List<Animal> getAll(String type, Integer category, String sex, String orderBy);
    List<Animal> createFromFile(List<Animal> entities);
}
