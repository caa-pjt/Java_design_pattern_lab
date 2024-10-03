package com.carlosantunes.fabrique;

import com.carlosantunes.restaurant.produit.boisson.Boisson;
import com.carlosantunes.restaurant.produit.boisson.BoissonGazeuse;
import com.carlosantunes.restaurant.produit.plat.Plat;
import com.carlosantunes.restaurant.produit.plat.PlatVegan;

public class CreateurVegan implements CreateurProduit {

    @Override
    public Plat creerPlat(String nom, double prix) {
        return new PlatVegan(nom, prix, "Vegan");
    }

    @Override
    public Boisson creerBoisson(String nom, double prix) {
        return new BoissonGazeuse(nom, prix, "Gazeuse");
    }
}
