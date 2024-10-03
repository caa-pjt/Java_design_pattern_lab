package com.carlosantunes;

import com.carlosantunes.restaurant.produit.Boisson;
import com.carlosantunes.restaurant.produit.Menu;
import com.carlosantunes.restaurant.produit.Plat;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RestaurantTest {

    private Restaurant restaurant;

    @Before
    public void setUp() {
        restaurant = new Restaurant();
    }

    @Test
    public void testAjoutProduit() {
        // Création de produits
        Plat plat = new Plat("Salade", 5.50, "entrée");
        Boisson boisson = new Boisson("Coca", 2.50, "froide");
        Menu menu = new Menu("Menu du jour");

        // Ajout des produits au restaurant
        restaurant.ajouterProduit(plat);
        restaurant.ajouterProduit(boisson);
        restaurant.ajouterProduit(menu);

        // Vérification que les produits ont bien été ajoutés
        assertEquals(3, restaurant.getProduits().size());
        assertTrue(restaurant.getProduits().contains(plat));
        assertTrue(restaurant.getProduits().contains(boisson));
        assertTrue(restaurant.getProduits().contains(menu));
    }
}
