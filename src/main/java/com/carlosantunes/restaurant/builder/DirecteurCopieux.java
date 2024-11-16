package com.carlosantunes.restaurant.builder;

import com.carlosantunes.restaurant.produit.Menu;

public class DirecteurCopieux implements Directeur {

    private final Builder builder;

    public DirecteurCopieux(Builder builder) {
        this.builder = builder;
    }

    public Menu construireMenu() {
        builder.construireEntree();
        builder.construirePlat();
        builder.construireDessert();
        builder.construireDessert();  // Deux desserts pour le menu copieux
        builder.construireBoisson();
        builder.construireBoisson();  // Deux boissons pour le menu copieux

        return builder.getMenu();
    }
}
