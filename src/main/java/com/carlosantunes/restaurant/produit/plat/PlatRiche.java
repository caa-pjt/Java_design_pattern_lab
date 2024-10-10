package com.carlosantunes.restaurant.produit.plat;

import com.carlosantunes.restaurant.enums.PlatType;

public class PlatRiche extends Plat {

    public PlatRiche(String nom, Double prix) {
        super(nom, prix, PlatType.RICHE);
    }
}
