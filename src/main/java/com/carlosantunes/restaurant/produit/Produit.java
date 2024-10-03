package com.carlosantunes.restaurant.produit;

/**
 * Classe Composant
 * Interface Produit qui représente un produit abstrait dans le restaurant.
 * Elle est implémentée par les classes concrètes Plat, Boisson et Menu.
 *
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
    Double getPrix();

    /**
     * @return Le type du produit par exemple, entrée, plat principal, dessert, boisson).
     */
    String getType();

    /**
     * Affiche les informations du produit.
     */
    void afficher();
}
