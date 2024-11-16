package com.carlosantunes.restaurant.builder;

import com.carlosantunes.restaurant.TableFactory;
import com.carlosantunes.restaurant.enums.MenuType;
import com.carlosantunes.restaurant.enums.TableType;
import com.carlosantunes.restaurant.fabrique.CreateurProduit;
import com.carlosantunes.restaurant.produit.Menu;
import com.carlosantunes.restaurant.produit.Produit;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.List;

public class ConcretMenuVegan implements Builder {

    private final Menu menu;
    private final CreateurProduit createurType;
    private final SecureRandom random;

    public ConcretMenuVegan(String nom) {
        this.menu = new Menu(nom, MenuType.VEGAN);
        this.createurType = TableFactory.createTable(TableType.VEGAN);
        this.random = new SecureRandom();
    }

    /**
     * Obtenir un produit aléatoire parmi une liste de produits
     *
     * @param produits Liste de produits
     * @return Produit aléatoire
     */
    private String obtenirProduitAleatoire(List<String> produits) {
        if (produits.isEmpty()) {
            throw new IllegalArgumentException("Aucun produit disponible pour le type spécifié");
        }
        return produits.get(random.nextInt(produits.size()));
    }

    /**
     * Génère un prix aléatoire entre min et max, arrondi au multiple de 0.05 le plus proche et formaté à 2 décimales
     *
     * @param min Prix minimum
     * @param max Prix maximum
     * @return Prix aléatoire
     */
    private double obtenirPrixAleatoire(double min, double max) {
        // Générer un prix aléatoire entre 3.0 et 10.0
        double prixAleatoire = min + (max - min) * this.random.nextDouble();

        // Arrondir au multiple de 0.05 le plus proche et formater à 2 décimales
        prixAleatoire = Math.round(prixAleatoire * 20) / 20.0;
        prixAleatoire = BigDecimal.valueOf(prixAleatoire).setScale(2, RoundingMode.HALF_UP).doubleValue();

        return prixAleatoire;
    }

    @Override
    public void construireEntree() {
        String nomEntree = obtenirProduitAleatoire(ProduitsParType.obtenirEntreesParType(MenuType.VEGAN));
        double prix = obtenirPrixAleatoire(3.0, 10.0);
        Produit entree = createurType.creerPlat(nomEntree, prix);
        menu.ajouterProduit(entree);
    }

    @Override
    public void construirePlat() {
        String nomPlat = obtenirProduitAleatoire(ProduitsParType.obtenirPlatsParType(MenuType.VEGAN));
        double prix = obtenirPrixAleatoire(10.0, 20.0);
        Produit plat = createurType.creerPlat(nomPlat, prix);
        menu.ajouterProduit(plat);
    }

    @Override
    public void construireDessert() {
        String nomDessert = obtenirProduitAleatoire(ProduitsParType.obtenirDessertsParType(MenuType.VEGAN));
        double prix = obtenirPrixAleatoire(3.0, 10.0);
        Produit dessert = createurType.creerPlat(nomDessert, prix);
        menu.ajouterProduit(dessert);
    }

    @Override
    public void construireBoisson() {
        String nomBoisson = obtenirProduitAleatoire(ProduitsParType.obtenirBoissonsParType(MenuType.VEGAN));
        double prix = obtenirPrixAleatoire(2.0, 5.0);
        Produit boisson = createurType.creerBoisson(nomBoisson, prix);
        menu.ajouterProduit(boisson);
    }

    @Override
    public Menu getMenu() {
        return menu;
    }


}
