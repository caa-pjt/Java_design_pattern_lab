package com.carlosantunes.restaurant.produit.boisson;

import com.carlosantunes.restaurant.enums.BoissonType;

public class BoissonGazeuse extends Boisson {

    /**
    * Constructeur de la classe BoissonGazeuse.
    *
    * @param nom  Le nom de la boisson gazeuse.
    * @param prix Le prix de la boisson gazeuse.
    */
    public BoissonGazeuse(String nom, Double prix) {
        super(nom, prix, BoissonType.GAZEUSE);
    }
}
