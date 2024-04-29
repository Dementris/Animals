package com.dementris.testtask.repository;

import com.dementris.testtask.model.Animal;
import com.dementris.testtask.model.AnimalRepository;
import com.dementris.testtask.service.Mapper;
import com.mongodb.client.model.Sorts;
import com.mongodb.internal.connection.ClusterDescriptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
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
    public List<Animal> getAll(String type, Integer category, String sex, String orderBy) {
        List<AnimalDao> animals = context.findAllByTypeAndCategoryAndSex(type, category, sex,Sort.by(orderBy));
        return animals.stream().map(mapper::DaoToAnimal).toList();
    }

    @Override
    public List<Animal> createFromFile(List<Animal> entities) {
        List<AnimalDao> animals = entities.stream().map(mapper::AnimalToDao).toList();
        List<AnimalDao> createdAnimals = context.saveAll(animals);
        return createdAnimals.stream().map(mapper::DaoToAnimal).toList();
    }

}
