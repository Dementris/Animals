package com.dementris.testtask.service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
@JsonPropertyOrder({ "name", "type", "sex", "weight", "cost" })
public record CsvAnimalDto(@NotEmpty String name,
                           @NotEmpty String type,
                           @NotEmpty String sex,
                           @NotNull Integer weight,
                           @NotNull Integer cost) {
}
