package com.dementris.testtask.service.parser;

import com.dementris.testtask.model.Animal;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

/**
 * AnimalParser interface has a parseCsv() and parseXml() methods implemented in AnimalParserImpl
 */
public interface AnimalParser {

    List<Animal> parseCsv(Resource data);
    List<Animal> parseXml(Resource data);

}
