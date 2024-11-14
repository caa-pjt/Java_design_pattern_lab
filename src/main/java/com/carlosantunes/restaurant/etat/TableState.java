package com.carlosantunes.restaurant.etat;

import com.carlosantunes.restaurant.Table;

public abstract class TableState {

    public abstract void accueillirClient(Table table);
    public abstract void servirProduits(Table table);
    public abstract void fermer(Table table);
    public abstract void afficher(Table table);


    /*
     * Méthodes pour les erreurs
     */

    // Vérifie si des produits ont été consommés par la table
    protected void verifierProduitsConsommes(Table table, String message) {
        if (table.getProduitsConsommes().isEmpty()) {
            afficheErreur(message);
        }
    }

    protected void afficheErreur(String message) {
            throw new IllegalStateException(message);
    }

}
