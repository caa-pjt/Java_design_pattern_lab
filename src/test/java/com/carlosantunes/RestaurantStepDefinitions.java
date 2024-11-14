package com.carlosantunes;


import com.carlosantunes.restaurant.Recette;
import com.carlosantunes.restaurant.Table;
import com.carlosantunes.restaurant.TableFactory;
import com.carlosantunes.restaurant.decorateur.ExtraTaste;
import com.carlosantunes.restaurant.enums.BoissonType;
import com.carlosantunes.restaurant.enums.MenuType;
import com.carlosantunes.restaurant.enums.PlatType;
import com.carlosantunes.restaurant.enums.TableType;
import com.carlosantunes.restaurant.etat.Cloturer;
import com.carlosantunes.restaurant.etat.Servie;
import com.carlosantunes.restaurant.fabrique.CreateurProduit;
import com.carlosantunes.restaurant.produit.Menu;
import com.carlosantunes.restaurant.produit.Produit;
import com.carlosantunes.restaurant.produit.boisson.Boisson;
import com.carlosantunes.restaurant.produit.plat.Plat;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.util.Date;
import java.util.HashMap;
public class RestaurantStepDefinitions {

    private Restaurant restaurant;
    private Menu menu;
    private Produit produit;
    private final HashMap<String, Table> tables = new HashMap<>();
    private CreateurProduit createurProduit;

    @Given("un restaurant vide")
    public void un_restaurant_vide() {
        restaurant = new Restaurant(); // Initie un restaurant vide
    }

    @When("j'ajoute un plat {string} avec un prix de {double} chf a la table du client {string}")
    public void jAjouteUnPlatAvecUnPrixDeChf(String nom, double prix, String client) {
        Table table = tables.get(client);
        Produit plat = createurProduit.creerPlat(nom, prix);
        table.ajouterProduit(plat);
    }

    @And("j'ajoute un boisson {string} au montant de {double} CHF à la table du client {string}")
    public void jAjouteUnBoissonAuMontantDeCHFALaTableDuClient(String nom, double prix, String client) {
        Table table = tables.get(client);
        Produit boissons = createurProduit.creerBoisson(nom, prix);
        table.ajouterProduit(boissons);
    }

    @Then("le restaurant contient {int} produit")
    public void le_restaurant_contient_produit(int nbProduits) {
        Assert.assertEquals(nbProduits, restaurant.getProduits().size());
    }

    @Then("le menu contient {int} produit")
    public void leMenuContientProduit(int nbProduits) {
        Assert.assertEquals(nbProduits, menu.getProduits().size());
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

    @Then("la table du client {string} contient {int} produits")
    public void laTableContientProduits(String client, int nbProduits) {
        Table table = tables.get(client);
        int nombreDeProduits = table.getProduitsConsommes().size();
        Assert.assertEquals(nbProduits, nombreDeProduits);
    }

    @Then("la boisson {string} est de type {string} pour le client {string}")
    public void laBoissonEstDeType(String nomBoisson, String typeBoisson, String client) {

        Table table = tables.get(client);

        Boisson boisson = (Boisson) table.getProduitsConsommes().stream()
                .filter(produit -> produit.getNom().equals(nomBoisson))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Boisson non trouvée : " + nomBoisson));


        System.out.println("La boisson " + nomBoisson + " est de type " + typeBoisson + " et est de type " + boisson.getClass());

        // Comparer avec la valeur de getType() au lieu du nom de l'enum
        Assert.assertEquals(typeBoisson, boisson.getType());

    }

    @Then("le plat {string} est de type {string} pour le client {string}")
    public void lePlatEstDeType(String nomPlat, String typePlat, String client) {
        Table table = tables.get(client);
        Plat plat = (Plat) table.getProduitsConsommes().stream()
                .filter(produit -> produit.getNom().equals(nomPlat))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Plat non trouvé : " + nomPlat));

        System.out.println("Le plat " + nomPlat + " est de type " + typePlat + " et est de type " + plat.getClass());

        // Comparer avec la valeur de getType() au lieu du nom de l'enum
        Assert.assertEquals(typePlat, plat.getType());
    }

    @Given("le restaurant a une table avec le client {string} et des produits")
    public void le_restaurant_a_une_table_avec_le_client_et_des_produits(String client) {
        restaurant = new Restaurant();

        // Première table pour client1
        Table table1 = new Table(client, new Date(), TableType.PLAISIR);
        tables.put(client, table1); // Ajout de la table au HashMap avec le nom du client1 comme clé
        table1.ajouterProduit(new Plat("Pâtes", 12.50, PlatType.RICHE));
        table1.ajouterProduit(new Boisson("Vin", 8.00, BoissonType.ALCOOLISEE));
    }

    @When("la table est clôturée pour le client {string}")
    public void laTableEstCloturee(String client) {
        Table table = tables.get(client);
        Recette.getInstance().setTableRecette(table);
    }

    @Then("la recette doit contenir une table pour le client {string} avec un montant total de {double} CHF.")
    public void laRecetteDoitContenirUneTablePourLeClientAvecUnMontantTotalDe(String name, double montant) {

        // Montant de la dernière table clôturée
        Table tableCloturee = Recette.getInstance().getListeTablesCloturees().stream().filter(t -> t.getClient().equals(name)).findFirst().orElseThrow();
        Assert.assertEquals(montant, tableCloturee.getMontant(), 0.01);
    }

    @Given("le restaurant a deux tables avec les clients {string} et {string}")
    public void le_restaurant_a_deux_tables_avec_les_clients_et(String client1, String client2) {
        restaurant = new Restaurant();

        Recette.getInstance().viderRecette(); // Vide la recette avant de commencer

        // Première table pour client1
        Table table1 = new Table(client1, new Date(), TableType.PLAISIR);
        tables.put(client1, table1); // Ajout de la table au HashMap avec le nom du client1 comme clé
        table1.ajouterProduit(new Plat("Pâtes", 12.50, PlatType.RICHE));
        table1.ajouterProduit(new Boisson("Vin", 8.00, BoissonType.ALCOOLISEE));

        // Deuxième table pour client2
        Table table2 = new Table(client2, new Date(), TableType.PLAISIR);
        tables.put(client2, table2); // Ajout de la table au HashMap avec le nom du client2 comme clé
        table2.ajouterProduit(new Plat("Salade", 7.00, PlatType.VEGAN));
        table2.ajouterProduit(new Boisson("Eau", 3.50, BoissonType.GAZEUSE));
    }

    @When("les tables sont clôturées pour les clients {string} et {string}")
    public void lesTablesSontCloturees(String client1, String client2) {
        Table table = tables.get(client1);
        Table table2 = tables.get(client2);
        Recette.getInstance().setTableRecette(table);
        Recette.getInstance().setTableRecette(table2);
    }

    @Then("le restaurant doit avoir {int} tables clôturées")
    public void leRestaurantDoitAvoirTablesCloturees(int nbrTablesCloturees) {
        Assert.assertEquals(nbrTablesCloturees, Recette.getInstance().getListeTablesCloturees().size());
    }


    @Then("les recettes totales doivent être {double} CHF.")
    public void lesRecettesTotalesDoiventEtreCHF(double totalAttendu) {
        // Comparer les doubles avec une précision de 0.01
        Assert.assertEquals(totalAttendu, Recette.getInstance().getMontantTotalRecettes(), 0.01);

    }

    // =========== restaurantTableState.feature ===========
    @Given("La table est réservée pour {string} de type {string}")
    public void laTableReserveePourDeType(String client, String type) {
        Table nouvelleTable = new Table(client, new Date(), TableType.valueOf(type));
        tables.put(client, nouvelleTable); // Ajout de la table au HashMap avec le nom du client comme clé
        System.out.println("Table réservée pour " + client + " de type " + type);
    }

    @When("je accueille le client {string}")
    public void je_accueille_le_client(String client) {
        Table table = tables.get(client);
        table.getEtatDeLaTable().accueillirClient(table);
    }

    @Then("la table doit être servi pour {string}")
    public void laTableDoitEtreServiPour(String client) {
        Table table = tables.get(client);
        Assert.assertTrue(table.getEtatDeLaTable() instanceof Servie);
    }

    @When("je ferme la table pour {string}")
    public void je_ferme_la_table(String client) {
        Table table = tables.get(client);
        table.getEtatDeLaTable().fermer(table);
    }

    @Then("la table doit être clôturée pour {string}")
    public void la_table_doit_etre_cloturee(String client) {
        Table table = tables.get(client);
        Assert.assertTrue(table.getEtatDeLaTable() instanceof Cloturer);
    }

    @And("j'ajoute un produit {string} au montant de {double} CHF à la table du client {string}")
    public void jAjouteUnProduitAuMontantDeCHFALaTableDuClient(String nomProduit, double montant, String client) {
        Table table = tables.get(client);
        Produit produitAjouter = new Plat(nomProduit, montant, PlatType.RICHE);
        table.ajouterProduit(produitAjouter);
    }

    @And("les produits consommés à la table su client {string} doivent inclure {string}")
    public void lesProduitsConsommesALaTableSuClientDoiventInclure(String client, String nomProduit) {
        Table table = tables.get(client);
        boolean found = table.getProduitsConsommes().stream()
                .anyMatch(produit -> produit.getNom().equals(nomProduit));
        Assert.assertTrue(found);
    }

    @And("je peux servir des produits pour {string}")
    public void jePeuxServirDesProduits(String client) {
        Table table = tables.get(client);
        Assert.assertTrue(table.getEtatDeLaTable() instanceof Servie);
    }

    @When("La table de type {string} est réservée pour {string}")
    public void laTableDeTypePLAISIREstReserveePour(String typeTable, String client) {
        createurProduit = TableFactory.createTable(TableType.valueOf(typeTable));
        Table table = new Table(client, new Date(), TableType.valueOf(typeTable));
        tables.put(client, table);
    }

    @When("j'ajoute un plat {string} avec un prix de {double} chf")
    public void jAjouteUnPlatAvecUnPrixDeChf(String nom, double prix) {
        Produit plat = new Plat(nom, prix, PlatType.RICHE);
        restaurant.ajouterProduit(plat);
    }

    // =========== restaurantProduitDecorator.feature ===========

    @Given("un produit de base avec un prix de {double} CHF")
    public void unProduitDeBaseAvecUnPrixDeCHF(double prix) {
        produit = new Plat("Pâtes", prix, PlatType.RICHE);
    }

    @When("appliquer ExtraTaste au produit")
    public void jAppliqueLExtraTasteAuProduit() {
        produit = new ExtraTaste(produit);
    }

    @Then("le prix total doit être {double} CHF")
    public void lePrixTotalDoitEtreCHF(double prixAttendu) {
        Assert.assertEquals(prixAttendu, produit.getPrix(), 0.01);
    }
}
