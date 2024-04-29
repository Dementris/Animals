package com.dementris.testtask.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoDbContext extends MongoRepository<AnimalDao, String> {
}
