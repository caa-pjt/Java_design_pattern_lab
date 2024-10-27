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
        System.out.println("Erreur : Impossible de servir des produits. La table est réservée et le client n'est pas encore accueilli.");
        System.out.println("----------------------------------------");
        return this;
    }

    @Override
    public TableState fermer() {
        System.out.println("Erreur : Impossible de fermer la table. La table est réservée et le client n'est pas encore accueilli.");
        System.out.println("----------------------------------------");
        return this;
    }

    @Override
    public void afficher() {
        System.out.println("État actuel: Réservée pour " + table.getClient() + " (" + table.getTableType() + ")");
        System.out.println("----------------------------------------");
    }
}
