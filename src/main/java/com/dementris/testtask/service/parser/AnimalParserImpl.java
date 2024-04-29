package com.dementris.testtask.service.parser;

import com.dementris.testtask.service.Mapper;
import com.dementris.testtask.service.dto.CreateAnimalDto;
import com.dementris.testtask.service.dto.CsvAnimalDto;
import com.dementris.testtask.service.dto.XmlAnimalDto;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CsvAnimalAnimalParser implements AnimalParser {

    private final CsvMapper csvMapper = new CsvMapper();
    mlMapper xmlMapper = new XmlMapper();
    @Override
    public List<CsvAnimalDto> parseCsv(Resource data) throws IOException {
        CsvSchema schema = csvMapper.schemaFor(CsvAnimalDto.class).withHeader();
        MappingIterator<CsvAnimalDto> it = csvMapper.readerFor(CsvAnimalDto.class).with(schema).readValues(data.getContentAsByteArray());

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        List<CsvAnimalDto> animals = new ArrayList<>();
        while (it.hasNext()){
            CsvAnimalDto animal = it.next();
            Set<ConstraintViolation<CsvAnimalDto>> violations = validator.validate(animal);
            if (!violations.isEmpty()) {
                continue;
            }
            animals.add(animal);
        }
        return animals;
    }

    @Override
    public List<XmlAnimalDto> parseXml(Resource data) throws IOException {
        byte[] contentAsByteArray = data.getContentAsByteArray();
        CreateAnimalDto createAnimalDto = xmlMapper.readValue(contentAsByteArray, CreateAnimalDto.class);
        String s = "Hello";
        return null;
    }
}
