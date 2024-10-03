package com.carlosantunes.restaurant;

import com.carlosantunes.restaurant.produit.Produit;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
        System.out.println("Table de " + client + " (" + Type + ")" + " - " + getDate(date));
        for (Produit produit : produitsConsommes) {
            produit.afficher();
        }
    }

    public String getClient() {
        return client;
    }

    public String getDate(Date date) {
        String pattern = "dd MMMM yyyy HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("fr", "FR"));
        return simpleDateFormat.format(date);
    }
}
