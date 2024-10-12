package com.carlosantunes.restaurant.produit.plat;

import com.carlosantunes.restaurant.enums.PlatType;

public class PlatVegan extends Plat {

    public PlatVegan(String nom, Double prix) {
        super(nom, prix, PlatType.VEGAN);
    }
}
