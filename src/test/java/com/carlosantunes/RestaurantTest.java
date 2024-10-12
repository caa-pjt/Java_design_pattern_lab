package com.carlosantunes;

import com.carlosantunes.restaurant.Table;
import com.carlosantunes.restaurant.enums.BoissonType;
import com.carlosantunes.restaurant.enums.MenuType;
import com.carlosantunes.restaurant.enums.PlatType;
import com.carlosantunes.restaurant.enums.TableType;
import com.carlosantunes.restaurant.produit.Menu;
import com.carlosantunes.restaurant.produit.boisson.Boisson;
import com.carlosantunes.restaurant.produit.plat.Plat;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

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
        Plat plat = new Plat("Salade", 5.50, PlatType.VEGAN);
        Boisson boisson = new Boisson("Coca", 2.50, BoissonType.LIGHT);
        Menu menu = new Menu("Menu du jour", MenuType.PLAISIR);

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

    @Test
    public void testPrixTotalMenu() {
        // Création de plats
        Plat plat = new Plat("Steak", 12.00, PlatType.RICHE);
        Plat plat2 = new Plat("Salade", 5.50, PlatType.VEGAN);
        Plat plat3 = new Plat("Tarte aux pommes", 4.00, PlatType.VEGAN);

        // Création de boissons
        Boisson boisson = new Boisson("Coca", 2.50, BoissonType.GAZEUSE);
        Boisson boisson2 = new Boisson("Café", 1.50, BoissonType.LIGHT);

        // Création d'un menu
        Menu menu = new Menu("Menu du jour", MenuType.PLAISIR);
        menu.ajouterProduit(plat);
        menu.ajouterProduit(plat2);
        menu.ajouterProduit(plat3);
        menu.ajouterProduit(boisson);
        menu.ajouterProduit(boisson2);

        // Ajout du menu au restaurant
        restaurant.ajouterProduit(menu);

        // Vérification du prix total du menu
        assertEquals(25.50, menu.getPrix(), 0.01);
    }

    @Test
    public void testAjoutProduitMenu() {
        // Création de plats
        Plat plat = new Plat("Steak", 12.00, PlatType.RICHE);
        Plat plat2 = new Plat("Salade", 5.50, PlatType.VEGAN);
        Plat plat3 = new Plat("Tarte aux pommes", 4.00, PlatType.VEGAN);

        // Création de boissons
        Boisson boisson = new Boisson("Coca", 2.50, BoissonType.GAZEUSE);
        Boisson boisson2 = new Boisson("Café", 1.50, BoissonType.LIGHT);

        // Création d'un menu
        Menu menu = new Menu("Menu du jour", MenuType.PLAISIR);
        menu.ajouterProduit(plat);
        menu.ajouterProduit(plat2);
        menu.ajouterProduit(plat3);
        menu.ajouterProduit(boisson);
        menu.ajouterProduit(boisson2);

        // Ajout du menu au restaurant
        restaurant.ajouterProduit(menu);

        System.out.println("Produits du restaurant :" + menu.getProduits());

        // Vérification que les produits du menu ont bien été ajoutés
        assertEquals(5, menu.getProduits().size());
        assertTrue(menu.getProduits().contains(plat));
        assertTrue(menu.getProduits().contains(plat2));
        assertTrue(menu.getProduits().contains(plat3));
        assertTrue(menu.getProduits().contains(boisson));
        assertTrue(menu.getProduits().contains(boisson2));
    }

    @Test
    public void testAjoutProduitTable() {
        // Création de plats
        Plat plat = new Plat("Steak", 12.00, PlatType.RICHE);
        Plat plat2 = new Plat("Salade", 5.50, PlatType.VEGAN);
        Plat plat3 = new Plat("Tarte aux pommes", 4.00, PlatType.VEGAN);

        // Création de boissons
        Boisson boisson = new Boisson("Coca", 2.50, BoissonType.GAZEUSE);
        Boisson boisson2 = new Boisson("Café", 1.50, BoissonType.LIGHT);

        // Création d'une table
        Table table = new Table("Jean", new Date(), TableType.VEGAN);
        table.ajouterProduit(plat);
        table.ajouterProduit(plat2);
        table.ajouterProduit(plat3);
        table.ajouterProduit(boisson);
        table.ajouterProduit(boisson2);

        // Vérification que les produits de la table ont bien été ajoutés
        assertEquals(5, table.getProduitsConsommes().size());
        assertTrue(table.getProduitsConsommes().contains(plat));
        assertTrue(table.getProduitsConsommes().contains(plat2));
        assertTrue(table.getProduitsConsommes().contains(plat3));
        assertTrue(table.getProduitsConsommes().contains(boisson));
        assertTrue(table.getProduitsConsommes().contains(boisson2));
    }
}
