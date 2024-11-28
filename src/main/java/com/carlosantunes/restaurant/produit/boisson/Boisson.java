package com.carlosantunes.restaurant.produit.boisson;

import com.carlosantunes.restaurant.Visiteur;
import com.carlosantunes.restaurant.enums.BoissonType;
import com.carlosantunes.restaurant.produit.Produit;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Classe Boisson qui représente une boisson servie dans le restaurant.
 * Implémente l'interface Produit et doit fournir des détails sur la boisson.
 */
public class Boisson implements Produit {

    private final String nom;
    private final Double prix;
    private final BoissonType type;


    /**
     * Constructeur de la classe Boisson.
     *
     * @param nom  Le nom de la boisson.
     * @param prix Le prix de la boisson.
     * @param type Le type de boisson (froide ou chaude).
     */
    public Boisson(String nom, double prix, BoissonType type) {
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
        System.out.println("Boisson " +
                "nom : '" + getNom() + '\'' +
                ", type : " + getType() +
                ", prix : " + getPrix() + " CHF");
    }

    @Override
    public void accepter(Visiteur visiteur) {
        visiteur.visiterBoisson(this);
    }

    public void exportBoisson(String cheminFichier) {
        try (FileWriter writer = new FileWriter(cheminFichier, true)) {
            writer.append(getNom())
                    .append(",")
                    .append(Double.toString(getPrix()))
                    .append(",")
                    .append(getType())
                    .append("\n");
            writer.flush();
            writer.close();
            System.out.println("Boisson exportée : " + getNom());
        } catch (IOException e) {
            System.err.println("Erreur lors de l'exportation de la boisson : " + e.getMessage());
        }
    }
}
