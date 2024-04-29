package com.dementris.testtask.model;

import com.dementris.testtask.service.dto.AnimalsParamsDto;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AnimalsOrchestrator {

    List<Animal> getAll(AnimalsParamsDto params);
    List<Animal> createFromFile(MultipartFile file);
}
