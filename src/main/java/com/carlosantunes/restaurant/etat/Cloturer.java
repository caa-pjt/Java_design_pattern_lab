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
        try {
            afficheErreur("Erreur : Impossible d'accueillir un client. La table est clôturée.");
        } catch (IllegalStateException e) {
            System.out.println("Message : " + e.getMessage());
        }
        return this;
    }

    @Override
    public TableState servirProduits(Table table) {
        try {
            verifierProduitsConsommes(table, "Erreur : Impossible de servir des produits. La table est clôturée.");
        } catch (IllegalStateException e) {
            System.out.println("Message : " + e.getMessage());
        }
        return this;
    }

    @Override
    public TableState fermer(Table table) {
        try {
            afficheErreur("Erreur : Impossible de fermer la table. La table est déjà clôturée.");
        } catch (IllegalStateException e) {
            System.out.println("Message : " + e.getMessage());
        }
        return this;
    }

    @Override
    public void afficher(Table table) {
        try {
            verifierProduitsConsommes(table, "Erreur : Le client (" + table.getClient() + ") n'a rien consommé, " +
                    "impossible " +
                    "d'afficher " +
                    "les " +
                    "informations de la table.");

            System.out.println("----------------------------------------");
            System.out.println("La table du client : (" + table.getClient() + ") est clôturée voir la facture ci-dessous :");
            table.afficherProduitsConsommes();
            System.out.println("Montant total : " + table.getMontant() + " CHF");
            System.out.println("----------------------------------------");

        } catch (IllegalStateException e) {
            System.out.println("Message : " + e.getMessage());
        }
    }
}
