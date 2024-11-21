package com.carlosantunes.restaurant.produit;

import com.carlosantunes.restaurant.Visiteur;

/**
 * Classe Composant
 * Interface Produit qui représente un produit abstrait dans le restaurant.
 * Elle est implémentée par les classes concrètes Plat, Boisson et Menu.
 * <p>
 * Chaque produit doit être capable de fournir son nom, son prix, son type, et une méthode d'affichage.
 */
public interface Produit {

    /**
     * @return Le nom du produit.
     */
    String getNom();

    /**
     * @return Le prix du produit.
     */
    double getPrix();

    /**
     * @return Le type du produit par exemple, entrée, plat principal, dessert, boisson).
     */
    String getType();

    /**
     * Affiche les informations du produit.
     */
    void afficher();

    /**
     * Ajoute un produit au menu.
     * Cette méthode est implémentée par la classe Menu.
     * Elle est vide par défaut pour les classes Plat et Boisson.
     *
     * @param produit Le produit à ajouter au menu (peut être un Plat, une Boisson).
     */
    default void ajouterProduit(Produit produit) {
        throw new UnsupportedOperationException("Impossible d'ajouter un produit à un produit qui n'est pas un menu.");
    }

    /**
     * Exporte le produit en utilisant un visiteur.
     *
     * @param visiteur Le visiteur qui exporte le produit.
     */
    void exporter(Visiteur visiteur);
}
