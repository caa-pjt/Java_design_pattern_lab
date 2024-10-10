package com.carlosantunes.restaurant.enums;

public enum MenuType {
    PLAISIR("Menu Plaisir"),
    DIET("Menu Diet"),
    VEGAN("Menu Vegan");

    private final String description;

    MenuType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
