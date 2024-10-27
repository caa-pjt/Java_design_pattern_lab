package com.carlosantunes.restaurant.etat;

import com.carlosantunes.restaurant.Table;

public class Cloturer extends TableState {

    public Cloturer(Table table) {
        super(table);
    }

    @Override
    public TableState accueillirClient() {
        System.out.println("Erreur : Impossible d'accueillir un client. La table est clôturée.");
        System.out.println("----------------------------------------");
        return this;
    }

    @Override
    public TableState servirProduits() {
        System.out.println("Erreur : Impossible de servir des produits. La table est clôturée.");
        System.out.println("----------------------------------------");
        return this;
    }

    @Override
    public TableState fermer() {
        System.out.println("Erreur : Impossible de fermer la table. La table est déjà clôturée.");
        return this;
    }

    @Override
    public void afficher() {
        System.out.println("La table du client : ("+ table.getClient() + ") est clôturée voir la facture ci-dessous :");
        table.afficherProduitsConsommes();
        System.out.println("Montant total : " + table.getMontant() + " CHF");
        System.out.println("----------------------------------------");
    }
}
