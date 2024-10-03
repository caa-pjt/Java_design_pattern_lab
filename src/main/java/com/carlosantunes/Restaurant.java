package com.carlosantunes;

import com.carlosantunes.restaurant.fabrique.CreateurProduit;
import com.carlosantunes.restaurant.Table;
import com.carlosantunes.restaurant.produit.boisson.Boisson;
import com.carlosantunes.restaurant.produit.Menu;
import com.carlosantunes.restaurant.produit.plat.Plat;
import com.carlosantunes.restaurant.produit.Produit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * La classe Restaurant représente un restaurant qui gère une liste de produits (plats, boissons, menus).
 * Elle permet d'ajouter des produits au restaurant et d'afficher l'ensemble des produits proposés.
 */
public class Restaurant {

    // Patron composite
    private final List<Produit> produits;

    // Patron Abstract Factory
    private final List<Table> tables;

    /**
     * Constructeur de la classe Restaurant.
     * Initialise la liste des Produits et des tables du restaurant.
     */
    public Restaurant() {
        // produits
        this.produits = new ArrayList<>();
        // Tables
        this.tables = new ArrayList<>();
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
     *
     * @param table
     */
    public void ajouterTable(Table table) {
        tables.add(table);
    }


    /**
     * Méthode pour instancier la fabrique dynamiquement
     */
    public CreateurProduit getCreateur(String typeTable) {
        String packageName = "com.carlosantunes.restaurant.fabrique.";
        try {
            Class<?> clazz = Class.forName(packageName + "Createur" + typeTable);
            return (CreateurProduit) clazz.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null; // Si la classe n'existe pas ou une erreur survient
        }
    }

    /**
     * Crée une commande pour un client donné et un type de table donné.
     * @param client
     * @param typeTable
     */
    public void creerCommande(String client, String typeTable) {
        CreateurProduit createur = getCreateur(typeTable);
        if (createur == null) {
            System.out.println("Type de table inconnu");
            return;
        }

        // Création de la table
        Table table = new Table(client, new Date(), typeTable);
        Plat plat = createur.creerPlat("Plat du jour", 10.00);
        Boisson boisson = createur.creerBoisson("Eau", 2.00);
        table.ajouterProduit(plat);
        table.ajouterProduit(boisson);

        ajouterTable(table);
    }

    /**
     * Affiche l'ensemble des tables du restaurant.
     */
    public void afficherTables() {
        System.out.println("Tables du restaurant:");
        for (Table table : tables) {
            // System.out.println("Table de " + table.getClient());
            table.afficherProduitsConsommes();
            System.out.println("--------------------");
        }
    }


    /**
     * Affiche l'ensemble des tables du restaurant.
     */
    public static void main(String[] args) {
        System.out.println("Bienvenue au Restaurant!");

        // Création du restaurant
        Restaurant restaurant = new Restaurant();


        /*
            Tache 1: Composite pattern
            ------------------------------------------------------------------------------------------------------------------

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
*/
        /*
            Tache 2: Abstract Factory pattern
            ------------------------------------------------------------------------------------------------------------------
        */

        // création de commandes pour les factories
        /*
        restaurant.creerCommande("Alice", "Plaisir");
        restaurant.creerCommande("Bob", "Diet");
         */
        restaurant.creerCommande("Charlie", "Vegan");

        restaurant.afficherTables();
    }
}
