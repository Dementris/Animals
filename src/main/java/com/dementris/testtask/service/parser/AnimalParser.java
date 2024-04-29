package com.dementris.testtask.service.parser;

import com.dementris.testtask.model.Animal;
import com.dementris.testtask.service.dto.CreateAnimalDto;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

public interface Parser {
    List<CreateAnimalDto> parse(Resource data) throws IOException;
}
