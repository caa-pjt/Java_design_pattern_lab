package com.carlosantunes;


import com.carlosantunes.restaurant.produit.Boisson;
import com.carlosantunes.restaurant.produit.Menu;
import com.carlosantunes.restaurant.produit.Plat;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class RestaurantStepDefinitions {

    private Restaurant restaurant;
    private Menu menu;

    @Given("un restaurant vide")
    public void un_restaurant_vide() {
        restaurant = new Restaurant(); // Initie un restaurant vide
    }

    @When("j'ajoute un plat {string} avec un prix de {double} chf")
    public void jAjouteUnPlatAvecUnPrixDeChf(String nom, double prix) {
        Plat plat = new Plat(nom, prix, "Entrée");
        restaurant.ajouterProduit(plat);
    }

    @Then("le restaurant contient {int} produit")
    public void le_restaurant_contient_produit(int nbProduits) {
        Assert.assertEquals(nbProduits, restaurant.getProduits().size());
    }

    @Given("un menu {string}")
    public void un_menu(String nom) {
        menu = new Menu(nom);
    }

    @When("j'ajoute un plat {string} au menu avec un prix de {double} chf")
    public void j_ajoute_un_plat_au_menu_avec_un_prix_de(String nom, double prix) {
        Plat plat = new Plat(nom, prix, "Principal");
        menu.ajouterProduit(plat);
    }

    @When("j'ajoute une boisson {string} avec un prix de {double} chf")
    public void j_ajoute_une_boisson_avec_un_prix_de(String nom, double prix) {
        Boisson boisson = new Boisson(nom, prix, "Gazeuse");
        menu.ajouterProduit(boisson);
    }

    @Then("le prix total du menu est {double} chf")
    public void le_prix_total_du_menu_est(double prixTotal) {
        Assert.assertEquals(prixTotal, menu.getPrix(), 0.01);
    }
}
