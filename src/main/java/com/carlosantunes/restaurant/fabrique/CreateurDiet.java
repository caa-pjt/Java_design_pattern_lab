package com.carlosantunes.restaurant.fabrique;

import com.carlosantunes.restaurant.produit.boisson.Boisson;
import com.carlosantunes.restaurant.produit.boisson.BoissonLight;
import com.carlosantunes.restaurant.produit.plat.Plat;
import com.carlosantunes.restaurant.produit.plat.PlatDiet;

/**
 * Classe CreateurDiet qui permet de créer des plats et des boissons diététiques.
 */
public class CreateurDiet implements CreateurProduit {

    @Override
    public Plat creerPlat(String nom, double prix) {
        return new PlatDiet(nom, prix);
    }

    @Override
    public Boisson creerBoisson(String nom, double prix) {
        return new BoissonLight(nom, prix);
    }
}
