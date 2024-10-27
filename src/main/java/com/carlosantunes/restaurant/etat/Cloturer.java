package com.carlosantunes.restaurant.etat;

import com.carlosantunes.restaurant.Table;

public class Cloturer extends TableState {
    private static Cloturer instance;

    private Cloturer(Table table) {
        super(table);
    }

    public static Cloturer getInstance(Table table) {
        if (instance == null) {
            instance = new Cloturer(table);
        }
        return instance;
    }

    @Override
    public TableState accueillirClient() {
        System.out.println("Erreur : la table est clôturée.");
        return this;
    }

    @Override
    public TableState servirProduits() {
        System.out.println("Erreur : la table est clôturée.");
        return this;
    }

    @Override
    public TableState fermer() {
        System.out.println("La table est déjà clôturée.");
        return this;
    }

    @Override
    public void afficher() {
        System.out.println("La table est clôturée.");
    }
}
