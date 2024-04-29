package com.dementris.testtask.service;


import com.dementris.testtask.model.Animal;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AnimalRepository {
    List<Animal> getAll();
    List<Animal> postFile();
}
