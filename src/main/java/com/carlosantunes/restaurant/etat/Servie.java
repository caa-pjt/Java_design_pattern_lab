package com.carlosantunes.restaurant.etat;

import com.carlosantunes.restaurant.Table;

public class Servie extends TableState {
    private static final Servie instance = new Servie();

    private Servie() {}

    public static Servie getInstance() {
        return instance;
    }

    @Override
    public TableState accueillirClient(Table table) {
        System.out.println("Erreur : Impossible d'accueillir un client. La table est déjà occupée.");
        System.out.println("----------------------------------------");
        return this;
    }

    @Override
    public TableState servirProduits(Table table) {
        System.out.println("Les produits sont servis :");
        table.afficherProduitsConsommes();
        System.out.println("----------------------------------------");
        return this;
    }

    @Override
    public TableState fermer(Table table) {
        System.out.println("Le client a fini de consommer il demande l'addition : " + table.getMontant() + " CHF. La table passe à l'état clôturée.");
        System.out.println("----------------------------------------");
        return Cloturer.getInstance();
    }

    @Override
    public void afficher(Table table) {
        System.out.println("Le client est en train de consommer à la table " + table.getClient() + " (" + table.getTableType() + ")");
        System.out.println("----------------------------------------");

    }
}
