package com.dementris.testtask.service.dto;

public record AnimalDto(String id,
                        String name,
                        String type,
                        String sex,
                        int weight,
                        int cost,
                        int category) {
}
