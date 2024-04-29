package com.dementris.testtask.service.dto;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "animals")
public record XmlAnimalsDto(
        @JacksonXmlElementWrapper(useWrapping = false) List<XmlAnimalDto> animal
){

}
