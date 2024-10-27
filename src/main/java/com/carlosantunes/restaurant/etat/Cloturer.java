package com.carlosantunes.restaurant.etat;

import com.carlosantunes.restaurant.Table;

public class Cloturer extends TableState {

    public Cloturer(Table table) {
        super(table);
    }

    @Override
    public TableState accueillirClient() {
        System.out.println("La table est fermée.");
        return this;
    }

    @Override
    public TableState servirProduits() {
        System.out.println("La table est fermée.");
        return this;
    }

    @Override
    public TableState fermer() {
        System.out.println("La table est déjà fermée.");
        return this;
    }

    @Override
    public void afficher() {
        System.out.println("La table est fermée.");
    }
}
