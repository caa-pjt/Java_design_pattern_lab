package com.carlosantunes.fabrique;

import com.carlosantunes.restaurant.produit.boisson.Boisson;
import com.carlosantunes.restaurant.produit.boisson.BoissonAlcoolisee;
import com.carlosantunes.restaurant.produit.plat.Plat;
import com.carlosantunes.restaurant.produit.plat.PlatRiche;

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
