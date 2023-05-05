package com.homework.orderapplication.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class CustomErrorMap {
    private String problem;
    private Map<String, String> fields;

    public CustomErrorMap(Map<String, String> fields) {
        this.problem = "Issues with the following: ";
        this.fields = fields;
    }
}

