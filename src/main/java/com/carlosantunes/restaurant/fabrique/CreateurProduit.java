package com.carlosantunes.restaurant.fabrique;

import com.carlosantunes.restaurant.produit.boisson.Boisson;
import com.carlosantunes.restaurant.produit.plat.Plat;

/**
 * Interface CreateurProduit qui permet de créer des plats et des boissons.
 */
public interface CreateurProduit {

    Plat creerPlat(String nom, double prix);
    Boisson creerBoisson(String nom, double prix);
}
