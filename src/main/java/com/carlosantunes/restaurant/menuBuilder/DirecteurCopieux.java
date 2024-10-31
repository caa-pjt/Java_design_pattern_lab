package com.carlosantunes.restaurant.menuBuilder;

import com.carlosantunes.restaurant.produit.Menu;

public class DirecteurCopieux {

    private final Builder builder;

    public DirecteurCopieux(Builder builder) {
        this.builder = builder;
        System.out.println("----------------------------------");
        System.out.println("Menu : " + builder.getMenu().getNom());
        System.out.println("----------------------------------");
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
