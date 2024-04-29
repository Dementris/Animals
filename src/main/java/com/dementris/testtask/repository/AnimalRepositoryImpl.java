package com.dementris.testtask.repository;

import com.dementris.testtask.model.Animal;
import com.dementris.testtask.model.AnimalRepository;
import com.dementris.testtask.service.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class AnimalRepositoryImpl implements AnimalRepository {

    private final MongoDbContext context;
    private final Mapper mapper;

    @Autowired
    public AnimalRepositoryImpl(MongoDbContext context, Mapper mapper) {
        this.context = context;
        this.mapper = mapper;
    }

    @Override
    public List<Animal> getAll() {
        List<AnimalDao> animals = context.findAll();
        return animals.stream().map(mapper::DaoToAnimal).toList();
    }

    @Override
    public List<Animal> createFromFile(List<Animal> entities) {
        List<AnimalDao> animals = entities.stream().map(mapper::AnimalToDao).toList();
        List<AnimalDao> createdAnimals = context.saveAll(animals);
        return createdAnimals.stream().map(mapper::DaoToAnimal).toList();
    }

}
