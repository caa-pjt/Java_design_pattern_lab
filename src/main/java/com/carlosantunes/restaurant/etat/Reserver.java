package com.carlosantunes.restaurant.etat;

import com.carlosantunes.restaurant.Table;

public class Reserver extends TableState {
    private static Reserver instance;

    private Reserver(Table table) {
        super(table);
    }

    public static Reserver getInstance(Table table) {
        if (instance == null) {
            instance = new Reserver(table);
        }
        return instance;
    }

    @Override
    public TableState accueillirClient() {
        System.out.println("La table accueille le client et passe à l'état Servie.");
        return Servie.getInstance(table);
    }

    @Override
    public TableState servirProduits() {
        System.out.println("Impossible de servir des produits à une table réservée.");
        return this;
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
