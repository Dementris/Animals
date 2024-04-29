package com.dementris.testtask.service.parser;

import com.dementris.testtask.model.Animal;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

public interface AnimalParser {

    List<Animal> parseCsv(Resource data) throws IOException;
    List<Animal> parseXml(Resource data) throws IOException;

}
