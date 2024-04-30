package com.dementris.testtask.service.dto;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record XmlAnimalDto(
            @NotEmpty @JacksonXmlProperty(localName = "name") String name,
            @NotEmpty @JacksonXmlProperty(localName = "type") String type,
            @NotEmpty @JacksonXmlProperty(localName = "sex") String sex,
            @NotNull @JacksonXmlProperty(localName = "weight") Integer weight,
            @NotNull @JacksonXmlProperty(localName = "cost") Integer cost){
}
