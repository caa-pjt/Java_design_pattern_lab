package com.carlosantunes.restaurant.etat;

import com.carlosantunes.restaurant.Table;

public class Servie extends TableState {
    private static Servie instance;

    private Servie(Table table) {
        super(table);
    }

    public static Servie getInstance(Table table) {
        if (instance == null) {
            instance = new Servie(table);
        }
        return instance;
    }

    @Override
    public TableState accueillirClient() {
        System.out.println("La table à déjà accueilli les clients.");
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
        System.out.println("La table est clôturée.");
        return Cloturer.getInstance(table);
    }

    @Override
    public void afficher() {
        System.out.println("La table a été servie et attend d'être clôturée.");
    }
}
