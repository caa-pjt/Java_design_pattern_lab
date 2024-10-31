package com.carlosantunes.restaurant.menuBuilder;

import com.carlosantunes.restaurant.produit.Menu;

public class DirecteurNormal {

    private final Builder builder;

    public DirecteurNormal(Builder builder) {
        this.builder = builder;
        System.out.println("----------------------------------");
        System.out.println("Menu : " + builder.getMenu().getNom());
        System.out.println("----------------------------------");
    }

    public Menu construireMenu() {
        builder.construireEntree();
        builder.construirePlat();
        builder.construireDessert();
        builder.construireBoisson();

        return builder.getMenu();
    }
}
