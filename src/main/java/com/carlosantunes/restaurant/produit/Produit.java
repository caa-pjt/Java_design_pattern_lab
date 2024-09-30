package com.carlosantunes.restaurant.produit;

/**
 * Classe Composant
 * Abstract Produit qui représente un produit abstrait dans le restaurant.
 * Elle est implémentée par les classes concrètes Plat, Boisson et Menu.
 *
 * Chaque produit doit être capable de fournir son nom, son prix, son type, et une méthode d'affichage.
 */
public abstract class Produit {

    protected String nom;
    protected double prix;
    protected String type;


    protected Produit(String nom, double prix, String type) {
        this.nom = nom;
        this.prix = prix;
        this.type = type;
    }

    public Produit(String nom) {
        this.nom = nom;
    }

    protected String getNom() {
        return nom;
    }

    protected double getPrix() {
        return prix;
    }

    protected String getType() {
        return type;
    }

    public abstract void afficher();
}
