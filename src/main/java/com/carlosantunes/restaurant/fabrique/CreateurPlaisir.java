package com.carlosantunes.restaurant.fabrique;

import com.carlosantunes.restaurant.produit.boisson.Boisson;
import com.carlosantunes.restaurant.produit.boisson.BoissonAlcoolisee;
import com.carlosantunes.restaurant.produit.plat.Plat;
import com.carlosantunes.restaurant.produit.plat.PlatRiche;

/**
 * Classe CreateurPlaisir qui permet de créer des plats et des boissons riches.
 */
public class CreateurPlaisir implements CreateurProduit {

    @Override
    public Plat creerPlat(String nom, double prix) {
        return new PlatRiche(nom, prix, "Riche");
    }

    @Override
    public Boisson creerBoisson(String nom, double prix) {
        return new BoissonAlcoolisee(nom, prix, "Alcoolisée");
    }

}
