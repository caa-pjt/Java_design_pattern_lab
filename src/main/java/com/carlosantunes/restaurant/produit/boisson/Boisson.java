package com.carlosantunes.restaurant.produit.boisson;

import com.carlosantunes.restaurant.produit.Produit;

/**
 * Classe Boisson qui représente une boisson servie dans le restaurant.
 * Implémente l'interface Produit et doit fournir des détails sur la boisson.
 */
public class Boisson implements Produit {

    private final String nom;
    private final Double prix;
    private final String type;


    /**
     * Constructeur de la classe Boisson.
     *
     * @param nom  Le nom de la boisson.
     * @param prix Le prix de la boisson.
     * @param type Le type de boisson (froide ou chaude).
     */
    public Boisson(String nom, Double prix, String type) {
        this.nom = nom;
        this.prix = prix;
        this.type = type;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public Double getPrix() {
        return prix;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void afficher() {
        System.out.println("Boisson " +
                "nom : '" + getNom() + '\'' +
                ", type : " + getType() +
                ", prix : " + getPrix() + " CHF");
    }
}
