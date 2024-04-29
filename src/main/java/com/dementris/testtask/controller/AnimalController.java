package com.dementris.testtask.controller;

import com.dementris.testtask.model.Animal;
import com.dementris.testtask.model.AnimalsOrchestrator;
import com.dementris.testtask.service.Mapper;
import com.dementris.testtask.service.dto.AnimalDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/api/v1")
public class AnimalController {

    private final AnimalsOrchestrator animalsOrchestrator;
    private final Mapper mapper;
    @Autowired
    public AnimalController(AnimalsOrchestrator animalsOrchestrator, Mapper mapper) {
        this.animalsOrchestrator = animalsOrchestrator;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<AnimalDto>> getAll(){
        List<Animal> entities = animalsOrchestrator.getAll();
        return ResponseEntity.ok(entities.stream().map(mapper::animalToDto).toList());
    }

    @PostMapping(value="file/upload", consumes = MULTIPART_FORM_DATA_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AnimalDto>> createFromFile(@RequestPart(value = "file") MultipartFile file){
        List<Animal> animals = animalsOrchestrator.createFromFile(file);
        return ResponseEntity.ok(animals.stream().map(mapper::animalToDto).toList());
    }

}
