package com.carlosantunes.restaurant.enums;

public enum BoissonType {

    GAZEUSE("Gazeuse"),
    ALCOOLISEE("Alcoolisée"),
    LIGHT("Light");

    private final String type;

    BoissonType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
