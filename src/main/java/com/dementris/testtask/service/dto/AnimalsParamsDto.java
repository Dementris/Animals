package com.dementris.testtask.service.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AnimalsParamsDto(
        @NotEmpty
        String type,
        @NotNull
        @Positive
        Integer category,
        @NotEmpty
        String sex,
        @NotEmpty
        String orderBy
) {
}
