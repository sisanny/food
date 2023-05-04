package com.homework.orderapplication.model;

public enum Status {
    RECEIVED("received"),
    PREPARING("preparing"),
    READY("ready"),
    DELIVERED("delivered");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
