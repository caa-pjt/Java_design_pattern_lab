package com.carlosantunes.restaurant.etat;

import com.carlosantunes.restaurant.Table;

public class Servie extends TableState {

    public Servie(Table table) {
        super(table);
    }

    @Override
    public TableState accueillirClient() {
        System.out.println("La table accueille les clients.");
        return this;
    }

    @Override
    public TableState servirProduits() {

        for (int i = 0; i < 3; i++) {
            System.out.println("Ici, il y a des produits à servir." + i);
        }
        return this;
    }

    @Override
    public TableState fermer() {
        System.out.println("La table est fermée.");
        return new Cloturer(table);
    }

    @Override
    public void afficher() {
        System.out.println("La table est servie.");
    }
}
