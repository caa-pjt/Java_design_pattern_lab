package com.carlosantunes.restaurant.etat;

import com.carlosantunes.restaurant.Recette;
import com.carlosantunes.restaurant.Table;

public class Servie extends TableState {
    private static final Servie instance = new Servie();

    private Servie() {}

    public static Servie getInstance() {
        return instance;
    }

    @Override
    public void accueillirClient(Table table) {
        try {
            afficheErreur("Erreur : Impossible d'accueillir un client. La table est déjà occupée.");
        } catch (IllegalStateException e) {
            System.out.println("Message : " + e.getMessage());
        }
    }

    @Override
    public void servirProduits(Table table) {
        try {
            verifierProduitsConsommes(table, "Erreur : Aucun produit n'a été consommé par la table.");

            System.out.println("Les produits sont servis :");
            table.afficherProduitsConsommes();
            System.out.println("----------------------------------------");
        } catch (IllegalStateException e) {
            System.out.println("Message : " + e.getMessage());
        }
    }

    @Override
    public void fermer(Table table) {
        try {
            verifierProduitsConsommes(table, "Erreur : Impossible de fermer la table. Aucun produit n'a été consommé.");

            System.out.println("Le client ("+table.getClient() +") a fini de consommer et demande et paye l'addition.");
            System.out.println("----------------------------------------");

            // Clôture de la table et ajout de la recette
            Recette.getInstance().setTableRecette(table);

            table.setEtatDeLaTable(Cloturer.getInstance());

        } catch (IllegalStateException e) {
            System.out.println("Message : " + e.getMessage());
        }
    }

    @Override
    public void afficher(Table table) {
        System.out.println("Le client ("+ table.getClient() +") est en train de consommer à la table (" + table.getTableType() + ")");
        System.out.println("----------------------------------------");

    }
}
