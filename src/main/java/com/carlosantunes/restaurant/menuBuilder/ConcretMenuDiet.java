package com.carlosantunes.restaurant.menuBuilder;

import com.carlosantunes.restaurant.TableFactory;
import com.carlosantunes.restaurant.enums.MenuType;
import com.carlosantunes.restaurant.enums.TableType;
import com.carlosantunes.restaurant.fabrique.CreateurProduit;
import com.carlosantunes.restaurant.produit.Menu;
import com.carlosantunes.restaurant.produit.Produit;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ConcretMenuDiet implements Builder {

    private final Menu menu;
    private final CreateurProduit createurType;
    private final Random random;

    public ConcretMenuDiet(String nom) {
        this.menu = new Menu(nom, MenuType.DIET);
        this.createurType = TableFactory.createTable(TableType.DIET);
        this.random = new Random();
    }

    /**
     * Obtenir un produit aléatoire parmi une liste de produits
     * @param produits Liste de produits
     * @return Produit aléatoire
     */
    private String obtenirProduitAleatoire(List<String> produits) {
       if(produits.isEmpty()) {
           throw new IllegalArgumentException("Aucun produit disponible pour le type spécifié");
       }
        return produits.get(random.nextInt(produits.size()));
    }

    /**
     * Génère un prix aléatoire entre min et max, arrondi au multiple de 0.05 le plus proche et formaté à 2 décimales
     * @param min Prix minimum
     * @param max Prix maximum
     * @return Prix aléatoire
     */
    private double obtenirPrixAleatoire(Double min, Double max) {
        // Générer un prix aléatoire entre 3.0 et 10.0
        double prixAleatoire = ThreadLocalRandom.current().nextDouble(min, max);

        // Arrondir au multiple de 0.05 le plus proche et formater à 2 décimales
        prixAleatoire = Math.round(prixAleatoire * 20) / 20.0;
        prixAleatoire = BigDecimal.valueOf(prixAleatoire).setScale(2, RoundingMode.HALF_UP).doubleValue();

        return prixAleatoire;
    }

    @Override
    public void construireEntree() {
        String nomEntree = obtenirProduitAleatoire(ProduitsParType.obtenirEntreesParType(MenuType.DIET));
        double prix =obtenirPrixAleatoire(3.0, 10.0);
        Produit entree = createurType.creerPlat(nomEntree, prix);
        menu.ajouterProduit(entree);
    }

    @Override
    public void construirePlat() {
        String nomPlat = obtenirProduitAleatoire(ProduitsParType.obtenirPlatsParType(MenuType.DIET));
        double prix =obtenirPrixAleatoire(10.0, 20.0);
        Produit plat = createurType.creerPlat(nomPlat, prix);
        menu.ajouterProduit(plat);
    }

    @Override
    public void construireDessert() {
        String nomDessert = obtenirProduitAleatoire(ProduitsParType.obtenirDessertsParType(MenuType.DIET));
        double prix =obtenirPrixAleatoire(3.0, 10.0);
        Produit dessert = createurType.creerPlat(nomDessert, prix);
        menu.ajouterProduit(dessert);
    }

    @Override
    public void construireBoisson() {
        String nomBoisson = obtenirProduitAleatoire(ProduitsParType.obtenirBoissonsParType(MenuType.DIET));
        double prix =obtenirPrixAleatoire(2.0, 5.0);
        Produit boisson = createurType.creerBoisson(nomBoisson, prix);
        menu.ajouterProduit(boisson);
    }

    @Override
    public Menu getMenu() {
        return menu;
    }
}
