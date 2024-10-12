package com.carlosantunes.restaurant.enums;

public enum TableType {

    PLAISIR("Plaisir"),
    DIET("Diet"),
    VEGAN("Vegan");

    private final String type;

    TableType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
