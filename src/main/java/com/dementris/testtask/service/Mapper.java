package com.dementris.testtask.service;

import com.dementris.testtask.model.Animal;
import com.dementris.testtask.service.dto.AnimalDto;
import com.dementris.testtask.service.dto.CreateAnimalDto;
import org.springframework.stereotype.Service;

@Service
public interface ServiceMapper {
    AnimalDto animalToDto(Animal animal);
    Animal CreateDtoToAnimal(CreateAnimalDto createAnimalDto);
    Animal DtoToAnimal(AnimalDto animalDto);
}
