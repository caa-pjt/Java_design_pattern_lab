package com.carlosantunes.restaurant;

import com.carlosantunes.restaurant.enums.TableType;
import com.carlosantunes.restaurant.fabrique.CreateurDiet;
import com.carlosantunes.restaurant.fabrique.CreateurPlaisir;
import com.carlosantunes.restaurant.fabrique.CreateurProduit;
import com.carlosantunes.restaurant.fabrique.CreateurVegan;

public class TableFactory {

    public static CreateurProduit createTable(TableType tableType) {
        return switch (tableType) {
            case DIET -> new CreateurDiet();
            case PLAISIR -> new CreateurPlaisir();
            case VEGAN -> new CreateurVegan();
            default -> throw new IllegalArgumentException("Type de table inconnu");
        };
    }
}
