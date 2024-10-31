package com.carlosantunes.restaurant.etat;

import com.carlosantunes.restaurant.Table;

public abstract class TableState {
    protected Table table;

    protected TableState(Table table) {
        this.table = table;
    }

    public abstract TableState accueillirClient();
    public abstract TableState servirProduits();
    public abstract TableState fermer();
    public abstract void afficher();


    /*
     * Méthodes pour les erreurs
     */

    // Vérifie si des produits ont été consommés par la table
    protected void verifierProduitsConsommes(String message) {
        if (table.getProduitsConsommes().isEmpty()) {
            afficheErreur(message);
        }
    }

    protected void afficheErreur(String message) {
            throw new IllegalStateException(message);
    }

}
