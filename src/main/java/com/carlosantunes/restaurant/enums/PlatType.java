package com.carlosantunes.restaurant.enums;

public enum PlatType {

    DIET("Diet"),
    RICHE("Riche"),
    VEGAN("Vegan");

    private final String type;

    PlatType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
