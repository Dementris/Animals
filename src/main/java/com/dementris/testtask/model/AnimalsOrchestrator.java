package com.dementris.testtask.model;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AnimalsOrchestrator {

    List<Animal> getAll();
    List<Animal> createFromFile(MultipartFile file);
}
