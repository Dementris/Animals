package com.dementris.testtask.model;

import lombok.*;
import org.bson.types.ObjectId;

@Data
public class Animal {
    private String id = String.valueOf(ObjectId.get());
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
