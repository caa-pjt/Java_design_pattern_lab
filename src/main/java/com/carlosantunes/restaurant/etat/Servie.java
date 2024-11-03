package com.carlosantunes.restaurant.etat;

import com.carlosantunes.restaurant.Recette;
import com.carlosantunes.restaurant.Table;

public class Servie extends TableState {

    public Servie(Table table) {
        super(table);
    }

    @Override
    public TableState accueillirClient() {
        try {
            afficheErreur("Erreur : Impossible d'accueillir un client. La table est déjà occupée.");
        } catch (IllegalStateException e) {
            System.out.println("Message : " + e.getMessage());
        }
        return this;
    }

    @Override
    public TableState servirProduits() {
        try {
            verifierProduitsConsommes("Erreur : Aucun produit n'a été consommé par la table.");

            System.out.println("Les produits sont servis :");
            table.afficherProduitsConsommes();
            System.out.println("----------------------------------------");
        } catch (IllegalStateException e) {
            System.out.println("Message : " + e.getMessage());
        }
        return this;
    }

    @Override
    public TableState fermer() {
        try {
            verifierProduitsConsommes("Erreur : Impossible de fermer la table. Aucun produit n'a été consommé.");

            System.out.println("La table est fermée.");
            System.out.println("----------------------------------------");

            // Clôture de la table et ajout de la recette
            Recette.getInstance().setTableRecette(table);

            System.out.println("Le client ("+table.getClient() +") a fini de consommer et demande l'addition.");

        } catch (IllegalStateException e) {
            System.out.println("Message : " + e.getMessage());
        }
        return new Cloturer(table);
    }

    @Override
    public void afficher() {
        System.out.println("Le client ("+ table.getClient() +") est en train de consommer à la table (" + table.getTableType() + ")");
        System.out.println("----------------------------------------");

    }
}
