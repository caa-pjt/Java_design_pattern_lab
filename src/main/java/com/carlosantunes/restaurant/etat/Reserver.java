package com.carlosantunes.restaurant.etat;

import com.carlosantunes.restaurant.Table;

public class Reserver extends TableState {
    private static final Reserver instance = new Reserver();

    private Reserver() {}

    public static Reserver getInstance() {
        return instance;
    }

    @Override
    public TableState accueillirClient(Table table) {
        System.out.println("Le client est accueilli à la table " + table.getTableType());
        return Servie.getInstance();
    }

    @Override
    public TableState servirProduits(Table table) {
        try {
            afficheErreur("Erreur : Impossible de servir des produits. La table est réservée et le client n'est pas encore accueilli.");
        } catch (IllegalStateException e) {
            System.out.println("Message : " + e.getMessage());
        }
        return this;
    }

    @Override
    public TableState fermer(Table table) {
        try {
            afficheErreur("Erreur : Impossible de fermer la table. La table est réservée et le client n'est pas encore accueilli.");
        } catch (IllegalStateException e) {
            System.out.println("Message : " + e.getMessage());
        }
        return this;
    }

    @Override
    public void afficher(Table table) {
        System.out.println("État actuel: Réservée pour " + table.getClient() + " (" + table.getTableType() + ")");
        System.out.println("----------------------------------------");
    }
}
