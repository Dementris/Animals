package com.dementris.testtask.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MongoDbContext extends MongoRepository<AnimalDao, String> {
    List<AnimalDao> findAllByTypeAndCategoryAndSex(String type, int category, String sex, Sort sort);
}
