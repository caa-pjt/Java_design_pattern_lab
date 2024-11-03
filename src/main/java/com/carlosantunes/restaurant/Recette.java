package com.carlosantunes.restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe Recette est un singleton. Elle permet de sauvegarder toutes les tables clôturées avec :
 * La date de la clôture,
 * Le client,
 * Le type de table,
 * Le montant de l'addition.
 */
public final class Recette {

    // volatile = valeur qui peut être modifiée de manière asynchrone par plusieurs threads
    private static volatile Recette instance = null;

    // Liste pour stocker les tables clôturées
    private final List<Table> historiqueTablesClosurees;

    private Recette() {
        this.historiqueTablesClosurees = new ArrayList<>();
    }

    /**
     * Méthode pour obtenir l'instance de la recette.
     *
     * @return L'instance de la recette.
     */
    public static Recette getInstance() {
        if (instance == null) {
            synchronized (Recette.class) {
                if (instance == null) {
                    instance = new Recette();
                }
            }
        }
        return instance;
    }

    /**
     * Clôture une table et ajoute les informations à la liste des tables clôturées.
     *
     * @param table La table clôturée.
     */
    public void setTableRecette(Table table) {
        synchronized (this) {
            historiqueTablesClosurees.add(table);
            System.out.println("Table clôturée pour le client " + table.getClient() + " avec un montant de " + table.getMontant() + " CHF.");
        }
    }

    /**
     * @return Le montant total des recettes.
     */
    public double getMontantTotalRecettes() {
        double total = 0;
        synchronized (this) {
            for (Table table : historiqueTablesClosurees) {
                total += table.getMontant();
            }
        }
        return total;
    }


    /**
     * Affiche les statistiques des tables clôturées.
     */
    public void afficherStatistiques() {
        System.out.println("Statistiques des tables clôturées :");
        synchronized (this) {
            for (Table table : historiqueTablesClosurees) {
                System.out.println(
                        "Client: " + table.getClient() +
                        ", Date: " + table.getDate() +
                        ", Type: " + table.getTableType() +
                        ", Montant: " + table.getMontant() + " CHF."
                );
            }
            System.out.println("Total des recettes : " + getMontantTotalRecettes() + " CHF.");
        }

    }


    // ============== Méthodes utilisées pour les test unitaires ===========================

    /**
     * @return La liste des tables clôturées
     */
    public List<Table> getListeTablesCloturees() {
        synchronized (this) {
            return new ArrayList<>(historiqueTablesClosurees); // Retourne une copie pour éviter toute modification externe
        }
    }

    /**
     * Vide la recette.
     */
    public void viderRecette() {
        synchronized (this) {
            historiqueTablesClosurees.clear();
        }
    }
}
