package com.dementris.testtask.model;

import java.util.List;


/**
 * AnimalOrchestrator interface has a get() and post() methods implemented in AnimalRepositoryImpl.
 */
public interface AnimalRepository {
    List<Animal> get(String type, Integer category, String sex, String orderBy);
    List<Animal> post(List<Animal> entities);
}
