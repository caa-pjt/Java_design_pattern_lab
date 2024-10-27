package com.carlosantunes.restaurant.etat;

import com.carlosantunes.restaurant.Table;

public class Servie extends TableState {

    public Servie(Table table) {
        super(table);
    }

    @Override
    public TableState accueillirClient() {
        System.out.println("Erreur : Impossible d'accueillir un client. La table est déjà occupée.");
        System.out.println("----------------------------------------");
        return this;
    }

    @Override
    public TableState servirProduits() {
        System.out.println("Les produits sont servis :");
        table.afficherProduitsConsommes();
        System.out.println("----------------------------------------");
        return this;
    }

    @Override
    public TableState fermer() {
        System.out.println("Le client a fini de consommer il demande l'addition : " + table.getMontant() + " CHF. La table passe à l'état clôturée.");
        System.out.println("----------------------------------------");
        return new Cloturer(table);
    }

    @Override
    public void afficher() {
        System.out.println("Le client est en train de consommer à la table " + table.getClient() + " (" + table.getTableType() + ")");
        System.out.println("----------------------------------------");

    }
}
