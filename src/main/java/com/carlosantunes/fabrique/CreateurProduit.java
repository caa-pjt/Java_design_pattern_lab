package com.carlosantunes.fabrique;

import com.carlosantunes.restaurant.produit.boisson.Boisson;
import com.carlosantunes.restaurant.produit.plat.Plat;

public interface CreateurProduit {

    Plat creerPlat(String nom, double prix);

    Boisson creerBoisson(String nom, double prix);
}
