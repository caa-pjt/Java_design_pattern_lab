package com.carlosantunes;


import com.carlosantunes.restaurant.Table;
import com.carlosantunes.restaurant.enums.BoissonType;
import com.carlosantunes.restaurant.enums.MenuType;
import com.carlosantunes.restaurant.enums.PlatType;
import com.carlosantunes.restaurant.enums.TableType;
import com.carlosantunes.restaurant.produit.Menu;
import com.carlosantunes.restaurant.produit.boisson.Boisson;
import com.carlosantunes.restaurant.produit.plat.Plat;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.util.Date;

public class RestaurantStepDefinitions {

    private Restaurant restaurant;
    private Menu menu;
    private Table table;


    @Given("un restaurant vide")
    public void un_restaurant_vide() {
        restaurant = new Restaurant(); // Initie un restaurant vide
    }

    @When("j'ajoute un plat {string} avec un prix de {double} chf")
    public void jAjouteUnPlatAvecUnPrixDeChf(String nom, double prix) {
        Plat plat = new Plat(nom, prix, PlatType.VEGAN);
        restaurant.ajouterProduit(plat);
    }

    @Then("le restaurant contient {int} produit")
    public void le_restaurant_contient_produit(int nbProduits) {
        Assert.assertEquals(nbProduits, restaurant.getProduits().size());
    }

    @Given("un menu {string} de type {string}")
    public void unMenuDeType(String nom, String type) {
        menu = new Menu(nom, MenuType.valueOf(type));
    }

    @When("j'ajoute un plat {string} au menu avec un prix de {double} chf")
    public void j_ajoute_un_plat_au_menu_avec_un_prix_de(String nom, double prix) {
        Plat plat = new Plat(nom, prix, PlatType.DIET);
        menu.ajouterProduit(plat);
    }

    @When("j'ajoute une boisson {string} avec un prix de {double} chf")
    public void j_ajoute_une_boisson_avec_un_prix_de(String nom, double prix) {
        Boisson boisson = new Boisson(nom, prix, BoissonType.GAZEUSE);
        menu.ajouterProduit(boisson);
    }

    @Then("le prix total du menu est {double} chf")
    public void le_prix_total_du_menu_est(double prixTotal) {
        Assert.assertEquals(prixTotal, menu.getPrix(), 0.01);
    }

    @When("je cree une table pour le client {string} de type {string}")
    public void jeCreeUneTablePourLeClientDeType(String client, String type) {
        table = new Table(client, new Date(), TableType.valueOf(type));
    }


    @And("j'ajoute un plat {string} avec un prix de {double} chf a la table")
    public void jAjouteUnPlatAvecUnPrixDeChfALaTable(String nom, double prix) {
        Plat plat = new Plat(nom, prix, PlatType.VEGAN);
        table.ajouterProduit(plat);
    }

    @And("j'ajoute une boisson {string} avec un prix de {double} chf a la table")
    public void jAjouteUneBoissonAvecUnPrixDeChfALaTable(String nom, double prix) {
        Boisson boisson = new Boisson(nom, prix, BoissonType.GAZEUSE);
        table.ajouterProduit(boisson);
    }

    @Then("la table contient {int} produits")
    public void laTableContientProduits(int nbProduits) {
        int nombreDeProduits = table.getProduitsConsommes().size();
        Assert.assertEquals(nbProduits, nombreDeProduits);
    }
}
