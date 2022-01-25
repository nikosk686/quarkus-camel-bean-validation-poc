package com.example;

import javax.validation.constraints.NotBlank;

public class ExampleDto {

    @NotBlank
    private String field;

    public ExampleDto() {
    }

    public ExampleDto(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "{\"field\":\"" + field + "\"}";
    }
}
