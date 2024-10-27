package com.carlosantunes.restaurant.etat;

import com.carlosantunes.restaurant.Table;

public class Cloturer extends TableState {
    private static final Cloturer instance = new Cloturer();

    private Cloturer() {}

    public static Cloturer getInstance() {
        return instance;
    }

    @Override
    public TableState accueillirClient(Table table) {
        System.out.println("Erreur : Impossible d'accueillir un client. La table est clôturée.");
        System.out.println("----------------------------------------");
        return this;
    }

    @Override
    public TableState servirProduits(Table table) {
        System.out.println("Erreur : Impossible de servir des produits. La table est clôturée.");
        System.out.println("----------------------------------------");
        return this;
    }

    @Override
    public TableState fermer(Table table) {
        System.out.println("Erreur : Impossible de fermer la table. La table est déjà clôturée.");
        return this;
    }

    @Override
    public void afficher(Table table) {
        System.out.println("La table du client : ("+ table.getClient() + ") est clôturée voir la facture ci-dessous :");
        table.afficherProduitsConsommes();
        System.out.println("Montant total : " + table.getMontant() + " CHF");
        System.out.println("----------------------------------------");
    }
}
