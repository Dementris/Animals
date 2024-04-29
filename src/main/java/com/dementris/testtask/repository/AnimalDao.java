package com.dementris.testtask.repository;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

@Document("animals")
@AllArgsConstructor
@Getter
@Setter
@Validated
public class AnimalDao {
    @Id
    @NotEmpty
    private String id;
    @NotEmpty
    @Size(min=2, max = 256)
    private String name;
    @NotEmpty
    private String type;
    @NotEmpty
    private String sex;
    @NotNull
    private int weight;
    @NotNull
    private int cost;
    @NotNull
    private int category;

}
