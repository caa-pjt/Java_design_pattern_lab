package com.carlosantunes.restaurant.produit.plat;

import com.carlosantunes.restaurant.enums.PlatType;

public class PlatDiet extends Plat {

    public PlatDiet(String nom, Double prix) {
        super(nom, prix, PlatType.DIET);
    }
}
