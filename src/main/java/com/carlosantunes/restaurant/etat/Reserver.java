package com.carlosantunes.restaurant.etat;

import com.carlosantunes.restaurant.Table;

public class Reserver extends TableState {

    public Reserver(Table table) {
        super(table);
    }

    @Override
    public TableState accueillirClient() {
        System.out.println("La table est déjà réservée.");
        return this;
    }

    @Override
    public TableState servirProduits() {
        System.out.println("Impossible de servir des produits à une table réservée.");
        return new Servie(table);
    }

    @Override
    public TableState fermer() {
        System.out.println("La table est réservée et ne peut pas être fermée.");
        return this;
    }

    @Override
    public void afficher() {
        System.out.println("La table est réservée.");
    }
}
