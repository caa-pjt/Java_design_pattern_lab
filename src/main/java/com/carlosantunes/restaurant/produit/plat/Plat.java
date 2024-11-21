package com.carlosantunes.restaurant.produit.plat;

import com.carlosantunes.restaurant.Visiteur;
import com.carlosantunes.restaurant.enums.PlatType;
import com.carlosantunes.restaurant.produit.Produit;

/**
 * Classe Plat qui représente un plat servi dans le restaurant.
 * Implémente l'interface Produit et doit fournir des détails sur le plat.
 */
public class Plat implements Produit {

    private final String nom;
    private final Double prix;
    private final PlatType type;

    /**
     * Constructeur de la classe Plat.
     *
     * @param nom  Le nom du plat.
     * @param prix Le prix du plat.
     * @param type Le type du plat (entrée, plat principal, dessert).
     */
    public Plat(String nom, double prix, PlatType type) {
        this.nom = nom;
        this.prix = prix;
        this.type = type;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public double getPrix() {
        return prix;
    }

    @Override
    public String getType() {
        return type.getType();
    }

    @Override
    public void afficher() {
        System.out.println("Plat " +
                "nom : '" + getNom() + '\'' +
                ", type : " + getType() +
                ", prix : " + getPrix() + " CHF");
    }

    @Override
    public void exporter(Visiteur visiteur) {
        visiteur.visiterPlat(this);
    }
}
