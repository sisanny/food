package com.homework.orderapplication.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class CustomErrorMap {
    private final String problem;
    private final Map<String, String> fields;

    public CustomErrorMap(Map<String, String> fields) {
        this.problem = "Issues with the following: ";
        this.fields = fields;
    }
}

