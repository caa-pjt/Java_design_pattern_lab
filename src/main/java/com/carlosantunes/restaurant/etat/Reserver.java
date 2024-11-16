package com.carlosantunes.restaurant.etat;

import com.carlosantunes.restaurant.Recette;
import com.carlosantunes.restaurant.Table;

public class Reserver extends TableState {
    private static final Reserver instance = new Reserver();

    public static Reserver getInstance() {
        return instance;
    }

    @Override
    public void accueillirClient(Table table) {
        System.out.println("Le client est accueilli à la table " + table.getTableType());
        // La table est observée.
        table.ajouterObserver(Recette.getInstance());
        // Changement de l'état de la table à "Servie"
        table.setEtatDeLaTable(Servie.getInstance());
    }

    @Override
    public void servirProduits(Table table) {
        try {
            afficheErreur("Erreur : Impossible de servir des produits. La table est réservée et le client n'est pas encore accueilli.");
        } catch (IllegalStateException e) {
            System.out.println("Message : " + e.getMessage());
        }
    }

    @Override
    public void fermer(Table table) {
        try {
            afficheErreur("Erreur : Impossible de fermer la table. La table est réservée et le client n'est pas encore accueilli.");
        } catch (IllegalStateException e) {
            System.out.println("Message : " + e.getMessage());
        }
    }

    @Override
    public void afficher(Table table) {
        System.out.println("État actuel: Réservée pour " + table.getClient() + " (" + table.getTableType() + ")");
        System.out.println("----------------------------------------");
    }
}
