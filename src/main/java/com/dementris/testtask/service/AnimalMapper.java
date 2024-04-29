package com.dementris.testtask.service;

import com.dementris.testtask.model.Animal;
import com.dementris.testtask.repository.AnimalDao;
import com.dementris.testtask.service.dto.AnimalDto;
import com.dementris.testtask.service.dto.CsvAnimalDto;
import com.dementris.testtask.service.dto.XmlAnimalDto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class AnimalMapper implements Mapper {
    @Override
    public AnimalDto animalToDto(Animal animal) {
        return new AnimalDto(
                animal.getId(),
                animal.getName(),
                animal.getType(),
                animal.getSex(),
                animal.getWeight(),
                animal.getCost(),
                animal.getCategory()
        );
    }


    @Override
    public Animal DtoToAnimal(CsvAnimalDto animalDto) {
        Animal animal = new Animal();
        animal.setName(animalDto.name());
        animal.setType(animalDto.type());
        animal.setSex(animalDto.sex());
        animal.setWeight(animalDto.weight());
        animal.setCost(animalDto.cost());
        animal.setCategory();
        return animal;
    }

    @Override
    public Animal DtoToAnimal(XmlAnimalDto animalDto) {
        Animal animal = new Animal();
        animal.setName(animalDto.name());
        animal.setType(animalDto.type());
        animal.setSex(animalDto.sex());
        animal.setWeight(animalDto.weight());
        animal.setCost(animalDto.cost());
        animal.setCategory();
        return animal;
    }

    @Override
    public Animal DaoToAnimal(AnimalDao animalDao) {
        Animal animal = new Animal();
        animal.setId(animalDao.getId());
        animal.setName(animalDao.getName());
        animal.setType(animalDao.getType());
        animal.setSex(animalDao.getSex());
        animal.setWeight(animalDao.getWeight());
        animal.setCost(animalDao.getCost());
        animal.setCategory(animalDao.getCategory());
        return animal;
    }

    @Override
    public AnimalDao AnimalToDao(Animal animal) {
        if (animal == null) return null;
        return new AnimalDao(
                animal.getId(),
                animal.getName(),
                animal.getType(),
                animal.getSex(),
                animal.getWeight(),
                animal.getCost(),
                animal.getCategory()
        );
    }
}
