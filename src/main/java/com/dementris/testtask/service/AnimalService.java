package com.dementris.testtask.service;

import com.dementris.testtask.exceptions.AnimalNotFoundException;
import com.dementris.testtask.exceptions.IncorrectFileContentTypeException;
import com.dementris.testtask.exceptions.IncorrectFileDataException;
import com.dementris.testtask.model.Animal;
import com.dementris.testtask.model.AnimalRepository;
import com.dementris.testtask.model.AnimalsOrchestrator;
import com.dementris.testtask.service.dto.AnimalsParamsDto;
import com.dementris.testtask.service.parser.AnimalParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

/**
 * AnimalService implements AnimalOrchestrator.
 */
@Service
public class AnimalService implements AnimalsOrchestrator {
    private final AnimalParser parser;
    private final AnimalRepository animalRepository;
    @Autowired
    public AnimalService(AnimalParser parser, AnimalRepository animalRepository) {
        this.parser = parser;
        this.animalRepository = animalRepository;
    }


    /**
     * Method get() retrieves animals from repository and checks if they are empty.
     * Returns list af animals
     *
     * @param params Dto.
     * @return List<Animals> animals.
     * @throws AnimalNotFoundException animals not found
     */
    @Override
    public List<Animal> get(AnimalsParamsDto params) {
        List<Animal> animals = animalRepository.get(params.type(), params.category(), params.sex(), params.orderBy());
        if (animals.isEmpty()){
            throw new AnimalNotFoundException();
        }
        return animals;
    }

    /**
     * Method post() checks the file and retrieve animals from repository and checks on incorrect file data.
     * Returns list of created animals.
     *
     * @param file File with content type text/csv or text/xml.
     * @return List<Animal> animals
     */
    @Override
    public List<Animal> post(MultipartFile file){
        List<Animal> animals;
        if (Objects.equals(file.getContentType(), "text/csv")){
            try {
                animals = parser.parseCsv(file.getResource());
            } catch (Exception e) {
                throw new IncorrectFileDataException();
            }
        }else if (Objects.equals(file.getContentType(), "text/xml")){
            try {
                animals = parser.parseXml(file.getResource());
            } catch (Exception e) {
                throw new IncorrectFileDataException();
            }
        }else {
            throw new IncorrectFileContentTypeException();
        }
        animals = animalRepository.post(animals);
        return animals;
    }
}
