package com.dementris.testtask.model;

import java.util.List;


public interface AnimalRepository {
    List<Animal> getAll();
    List<Animal> createFromFile(List<Animal> entities);
}
