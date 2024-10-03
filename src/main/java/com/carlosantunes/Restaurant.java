package com.carlosantunes;

import com.carlosantunes.restaurant.produit.Boisson;
import com.carlosantunes.restaurant.produit.Menu;
import com.carlosantunes.restaurant.produit.Plat;
import com.carlosantunes.restaurant.produit.Produit;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe Restaurant représente un restaurant qui gère une liste de produits (plats, boissons, menus).
 * Elle permet d'ajouter des produits au restaurant et d'afficher l'ensemble des produits proposés.
 */
public class Restaurant {

    private final List<Produit> produits;

    /**
     * Constructeur de la classe Restaurant.
     * Initialise la liste des produits du restaurant.
     */
    public Restaurant() {
        this.produits = new ArrayList<>();
    }

    /**
     * Ajoute un produit à la liste des produits du restaurant.
     *
     * @param produit Le produit à ajouter (peut être un Plat, une Boisson ou un Menu).
     */
    public void ajouterProduit(Produit produit) {
        produits.add(produit);
    }

    /**
     * Affiche l'ensemble des produits disponibles dans le restaurant (plats, boissons, menus).
     * Chaque produit appelle sa propre méthode d'affichage via le polymorphisme.
     */
    public void afficherProduits() {
        for (Produit produit : produits) {
            produit.afficher();
            System.out.println("--------------------");
        }
    }

    /**
     * Utilisé pour les tests unitaires, retourne les produits du restaurant sous forme de liste.
     *
     * @return Liste des produits du restaurant.
     */
    public List<Produit> getProduits() {
        return produits;
    }

    /**
     * Méthode principale qui simule le fonctionnement du restaurant.
     * Crée des plats, boissons et menus, et les ajoute à la liste des produits du restaurant.
     *
     * @param args Arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        System.out.println("Bienvenue au Restaurant!");

        // Création du restaurant
        Restaurant restaurant = new Restaurant();

        // Création de plats
        Plat entree = new Plat("Salade", 5.50, "entrée");
        Plat principal = new Plat("Steak", 12.00, "plat principal");
        Plat dessert = new Plat("Tarte aux pommes", 4.00, "dessert");

        // Création de boissons
        Boisson soda = new Boisson("Coca", 2.50, "froide");
        Boisson cafe = new Boisson("Café", 1.50, "chaude");

        // Création d'un menu
        Menu menuMidi = new Menu("Menu du jour");
        menuMidi.ajouterProduit(entree);
        menuMidi.ajouterProduit(principal);
        menuMidi.ajouterProduit(dessert);
        menuMidi.ajouterProduit(soda);
        menuMidi.ajouterProduit(cafe);

        // Ajout des produits au restaurant
        restaurant.ajouterProduit(entree);
        restaurant.ajouterProduit(principal);
        restaurant.ajouterProduit(dessert);
        // Ajout des boissons au restaurant
        restaurant.ajouterProduit(soda);
        restaurant.ajouterProduit(cafe);

        // Ajout du menu au restaurant
        restaurant.ajouterProduit(menuMidi);

        // Affichage des produits du restaurant
        restaurant.afficherProduits();
    }
}
