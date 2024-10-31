package com.carlosantunes.restaurant.etat;

import com.carlosantunes.restaurant.Table;

public class Reserver extends TableState {

    public Reserver(Table table) {
        super(table);
    }

    @Override
    public TableState accueillirClient() {
        System.out.println("Le client est accueilli à la table " + table.getTableType());
        return new Servie(table);
    }

    @Override
    public TableState servirProduits() {
        try {
            afficheErreur("Erreur : Impossible de servir des produits. La table est réservée et le client n'est pas encore accueilli.");
        } catch (IllegalStateException e) {
            System.out.println("Message : " + e.getMessage());
        }
        return this;
    }

    @Override
    public TableState fermer() {
        try {
            afficheErreur("Erreur : Impossible de fermer la table. La table est réservée et le client n'est pas encore accueilli.");
        } catch (IllegalStateException e) {
            System.out.println("Message : " + e.getMessage());
        }
        return this;
    }

    @Override
    public void afficher() {
        System.out.println("État actuel: Réservée pour " + table.getClient() + " (" + table.getTableType() + ")");
        System.out.println("----------------------------------------");
    }
}
