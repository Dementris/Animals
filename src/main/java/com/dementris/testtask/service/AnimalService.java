package com.dementris.testtask.service;

import com.dementris.testtask.model.Animal;
import com.dementris.testtask.model.AnimalRepository;
import com.dementris.testtask.model.AnimalsOrchestrator;
import com.dementris.testtask.service.parser.AnimalParser;
import com.dementris.testtask.service.parser.AnimalParserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AnimalService implements AnimalsOrchestrator {
    private final AnimalParser parser;
    private final AnimalRepository animalRepository;
    @Autowired
    public AnimalService(AnimalParser parser, AnimalRepository animalRepository) {
        this.parser = parser;
        this.animalRepository = animalRepository;
    }


    @Override
    public List<Animal> getAll() {
        return animalRepository.getAll();
    }

    @Override
    public List<Animal> createFromFile(MultipartFile file){
        List<Animal> animals = new ArrayList<>();
        if (Objects.equals(file.getContentType(), "text/csv")){
            try {
                animals = parser.parseCsv(file.getResource());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else if (Objects.equals(file.getContentType(), "text/xml")){
            try {
                animals = parser.parseXml(file.getResource());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            throw new RuntimeException();
        }

        return animalRepository.createFromFile(animals);
    }
}
