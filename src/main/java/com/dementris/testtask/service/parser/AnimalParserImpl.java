package com.dementris.testtask.service.parser;

import com.dementris.testtask.model.Animal;
import com.dementris.testtask.service.Mapper;
import com.dementris.testtask.service.dto.CsvAnimalDto;
import com.dementris.testtask.service.dto.XmlAnimalDto;
import com.dementris.testtask.service.dto.XmlAnimalsDto;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Service
public class AnimalParserImpl implements AnimalParser {

    private final AnimalValidator validator;
    private final Mapper mapper;
    @Autowired
    public AnimalParserImpl(AnimalValidator validator, Mapper mapper) {
        this.validator = validator;
        this.mapper = mapper;
    }


    @Override
    public List<Animal> parseCsv(Resource data) throws IOException {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = csvMapper.schemaFor(CsvAnimalDto.class).withHeader();
        MappingIterator<CsvAnimalDto> it = csvMapper.readerFor(CsvAnimalDto.class).with(schema).readValues(data.getContentAsByteArray());
        return validator.validate(it).stream().map(mapper::DtoToAnimal).toList();
    }

    @Override
    public List<Animal> parseXml(Resource data) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        XmlAnimalsDto entities = xmlMapper.readValue(data.getContentAsByteArray(), XmlAnimalsDto.class);
        Iterator<XmlAnimalDto> it = entities.animal().stream().iterator();
        return validator.validate(it).stream().map(mapper::DtoToAnimal).toList();
    }
}
