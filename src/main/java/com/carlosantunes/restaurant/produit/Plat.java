package com.carlosantunes.restaurant.produit;

/**
 * Classe Plat qui représente un plat servi dans le restaurant.
 * Implémente l'interface Produit et doit fournir des détails sur le plat.
 */
public class Plat implements Produit {

    private final String nom;
    private final Double prix;
    private final String type;

    /**
     * Constructeur de la classe Plat.
     *
     * @param nom  Le nom du plat.
     * @param prix Le prix du plat.
     * @param type Le type du plat (entrée, plat principal, dessert).
     */
    public Plat(String nom, Double prix, String type) {
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
        System.out.println("Plat " +
                "nom : '" + getNom() + '\'' +
                ", type : " + getType() +
                ", prix : " + getPrix() + " CHF");
    }
}
