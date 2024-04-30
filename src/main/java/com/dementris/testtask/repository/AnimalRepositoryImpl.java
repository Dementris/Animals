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

/**
 * AnimalRepositoryImpl implements AnimalRepository.
 */
@Repository
public class AnimalRepositoryImpl implements AnimalRepository {

    private final MongoDbContext context;
    private final Mapper mapper;

    @Autowired
    public AnimalRepositoryImpl(MongoDbContext context, Mapper mapper) {
        this.context = context;
        this.mapper = mapper;
    }

    /**
     * Method get() that retrieves all Animals from MongoDB by filters and ordered By.
     *
     * @param type Animal type
     * @param category Animal category from 1 to 4
     * @param sex Animal sex "male" or "female"
     * @param orderBy Order by "id", "name", "type", "category", "sex", "weight", "cost"
     * @return List<Animal> animals
     */
    @Override
    public List<Animal> get(String type, Integer category, String sex, String orderBy) {
        List<AnimalDao> animals = context.findAllByTypeAndCategoryAndSex(type, category, sex,Sort.by(orderBy));
        return animals.stream().map(mapper::DaoToAnimal).toList();
    }

    /**
     * Method post() accept the validated list and adds all items to MongoDB.
     * Returns list with created animals.
     *
     * @param entities Validated animals list.
     * @return List<Animal> created animals
     */
    @Override
    public List<Animal> post(List<Animal> entities) {
        List<AnimalDao> animals = entities.stream().map(mapper::AnimalToDao).toList();
        List<AnimalDao> createdAnimals = context.saveAll(animals);
        return createdAnimals.stream().map(mapper::DaoToAnimal).toList();
    }

}
