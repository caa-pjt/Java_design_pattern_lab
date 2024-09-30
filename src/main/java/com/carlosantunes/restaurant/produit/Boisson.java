package com.carlosantunes.restaurant.produit;

/**
 * Classe Boisson qui représente une boisson servie dans le restaurant.
 * Implémente l'interface Produit et doit fournir des détails sur la boisson.
 */
public class Boisson extends Produit {


    public Boisson(String nom, double prix, String type) {
        super(nom, prix, type);
    }

    @Override
    public void afficher() {
        System.out.println("Boisson " +
                "nom : '" + getNom() + '\'' +
                ", type : " + getType() +
                ", prix : " + getPrix() + " CHF");
    }
}
