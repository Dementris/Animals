package com.dementris.testtask.model;

import com.dementris.testtask.service.dto.AnimalsParamsDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 *  AnimalOrchestrator interface has a get() and post() methods implemented in AnimalService.
 */
public interface AnimalsOrchestrator {

    List<Animal> get(AnimalsParamsDto params);
    List<Animal> post(MultipartFile file);
}
