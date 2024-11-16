package com.carlosantunes.restaurant.builder;

import com.carlosantunes.restaurant.produit.Menu;

public class DirecteurNormal implements Directeur {

    private final Builder builder;

    public DirecteurNormal(Builder builder) {
        this.builder = builder;
    }

    public Menu construireMenu() {
        builder.construireEntree();
        builder.construirePlat();
        builder.construireDessert();
        builder.construireBoisson();

        return builder.getMenu();
    }
}
