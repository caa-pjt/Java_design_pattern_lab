package com.carlosantunes.restaurant;

import com.carlosantunes.restaurant.produit.Produit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Table {
    private String client;
    private Date date;
    private String Type;

    private List<Produit> produitsConsommes;

    public Table(String client, Date date, String type) {
        this.client = client;
        this.date = date;
        this.Type = type;
        produitsConsommes = new ArrayList<>();
    }

    public void ajouterProduit(Produit produit) {
        produitsConsommes.add(produit);
    }

    public void afficherProduitsConsommes() {
        for (Produit produit : produitsConsommes) {
            produit.afficher();
            System.out.println("--------------------");
        }
    }

    public String getClient() {
        return client;
    }
}
