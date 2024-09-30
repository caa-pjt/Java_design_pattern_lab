package com.carlosantunes.restaurant.produit;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe Composite
 * représente un menu composé de plusieurs produits.
 * Implémente l'interface Produit et agit comme un composite qui peut contenir des plats, des boissons ou d'autres menus.
 */
public class Menu extends Produit {

    private final List<Produit> produits = new ArrayList<>();

    /**
     * Constructeur de la classe Menu.
     *
     * @param nom Le nom du menu.
     */
    public Menu(String nom) {
        super(nom);
    }

    /**
     * Ajoute un produit au menu.
     *
     * @param produit Le produit à ajouter au menu (peut être un Plat, une Boisson ou même un autre Menu).
     */
    public void ajouterProduit(Produit produit) {
        produits.add(produit);
    }

    /**
     * Calcule le prix total du menu en additionnant les prix de tous les produits qu'il contient.
     *
     * @return Le prix total du menu.
     */
    @Override
    public double getPrix() {
        double prixTotal = 0.0;
        for (Produit produit : produits) {
            prixTotal += produit.getPrix();
        }
        return prixTotal;
    }

    @Override
    public String getType() {
        return "Menu";
    }


    /**
     * Affiche les détails du menu ainsi que les produits qu'il contient, et le prix total du menu.
     */
    @Override
    public void afficher() {
        System.out.println(getNom() + " (" + getType() + ")");
        System.out.println("Produits inclus :");
        for (Produit produit : produits) {
            produit.afficher(); // Afficher chaque produit du menu
        }
        System.out.println("Le prix du " + getNom() + " est de : " + getPrix() + " CHF");
    }
}
