package com.dementris.testtask.service;

import com.dementris.testtask.model.Animal;
import com.dementris.testtask.repository.AnimalDao;
import com.dementris.testtask.service.dto.AnimalDto;
import com.dementris.testtask.service.dto.CsvAnimalDto;
import com.dementris.testtask.service.dto.XmlAnimalDto;

public interface Mapper {
    AnimalDto animalToDto(Animal animal);
    Animal DtoToAnimal(CsvAnimalDto animalDto);
    Animal DtoToAnimal(XmlAnimalDto animalDto);
    Animal DaoToAnimal(AnimalDao animalDao);
    AnimalDao AnimalToDao(Animal animal);
}
