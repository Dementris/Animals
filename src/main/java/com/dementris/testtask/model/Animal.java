package com.dementris.testtask.model;

import lombok.*;
import org.apache.commons.lang3.builder.HashCodeExclude;

import java.util.UUID;

@Data
public class Animal {
    private String id = String.valueOf(UUID.randomUUID());
    private String name;
    private String type;
    private String sex;
    private Integer weight;
    private Integer cost;
    private Integer category;

    public void setCategory() {
        this.category = cost <= 20 ? 1 :
                        cost <= 40 ? 2 :
                        cost <= 60 ? 3 : 4;
    }
}
