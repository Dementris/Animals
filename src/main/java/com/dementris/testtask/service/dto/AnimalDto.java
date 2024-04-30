package com.dementris.testtask.service.dto;

import org.bson.types.ObjectId;

public record AnimalDto(ObjectId id,
                        String name,
                        String type,
                        String sex,
                        int weight,
                        int cost,
                        int category) {
}
