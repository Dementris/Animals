package com.dementris.testtask.service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

import static jakarta.xml.bind.annotation.XmlAccessType.*;
public record XmlAnimalDto(
            @NotEmpty @JacksonXmlProperty(localName = "name") String name,
            @NotEmpty @JacksonXmlProperty(localName = "type") String type,
            @NotEmpty @JacksonXmlProperty(localName = "sex") String sex,
            @NotNull @JacksonXmlProperty(localName = "weight") Integer weight,
            @NotNull @JacksonXmlProperty(localName = "cost") Integer cost){
}
