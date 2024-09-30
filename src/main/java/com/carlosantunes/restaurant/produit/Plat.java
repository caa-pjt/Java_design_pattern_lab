package com.carlosantunes.restaurant.produit;

/**
 * Classe Plat qui représente un plat servi dans le restaurant.
 * Implémente l'interface Produit et doit fournir des détails sur le plat.
 */
public class Plat extends Produit {

    public Plat(String nom, double prix, String type) {
        super(nom, prix, type);
    }

    @Override
    public void afficher() {
        System.out.println("Plat " +
                "nom : '" + getNom() + '\'' +
                ", type : " + getType() +
                ", prix : " + getPrix() + " CHF");
    }
}
