package com.carlosantunes.restaurant.produit;

import com.carlosantunes.restaurant.Visiteur;
import com.carlosantunes.restaurant.enums.MenuType;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Composite
 * représente un menu composé de plusieurs produits.
 * Implémente l'interface Produit et agit comme un composite qui peut contenir des plats, des boissons ou d'autres menus.
 */
public class Menu implements Produit {

    private final String nom;
    private final MenuType type;
    private final List<Produit> produits = new ArrayList<>();

    /**
     * Constructeur de la classe Menu.
     *
     * @param nom Le nom du menu.
     */
    public Menu(String nom, MenuType type) {

        this.nom = nom;
        this.type = type;
    }

    /**
     * Ajoute un produit au menu.
     *
     * @param produit Le produit à ajouter au menu (peut être un Plat, une Boisson ou même un autre Menu).
     */
    @Override
    public void ajouterProduit(Produit produit) {
        produits.add(produit);
    }

    @Override
    public String getNom() {
        return nom;
    }

    /**
     * Calcule le prix total du menu en additionnant les prix de tous les produits qu'il contient.
     *
     * @return Le prix total du menu.
     */
    @Override
    public double getPrix() {
        double prix = 0.0;
        for (Produit produit : produits) {
            prix += produit.getPrix();
        }
        BigDecimal bd = BigDecimal.valueOf(prix);
        return bd.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    @Override
    public String getType() {
        return type.getDescription();
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

    /**
     * Retourne la liste des produits du menu.
     *
     * @return La liste des produits du menu.
     */
    public List<Produit> getProduits() {
        return produits;
    }

    @Override
    public void accepter(Visiteur visiteur) {
        visiteur.visiterMenu(this);
    }

    public void visiterMenu(String cheminFichier) {
        try (FileWriter writer = new FileWriter(cheminFichier, true)) {
            writer.append(getNom())
                    .append(",")
                    .append(Double.toString(getPrix()))
                    .append(",")
                    .append(getType())
                    .append("\n");
            writer.flush();
            writer.close();
            System.out.println("Menu exportée : " + getNom());
        } catch (IOException e) {
            System.err.println("Erreur lors de l'exportation du menu : " + e.getMessage());
        }
    }

}
