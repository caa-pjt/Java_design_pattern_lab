package com.carlosantunes.restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe Recette est un singleton. Elle permet de sauvegarder toutes les tables clôturées avec :
 * La date,
 * Le type de client,
 * Le montant de l'addition.
 */
public final class Recette {

    // volatile = valeur qui peut être modifiée de manière asynchrone par plusieurs threads
    private static volatile Recette instance = null;

    // Liste pour stocker les tables clôturées
    private final List<Table> tablesCloturees;

    private Recette() {
        this.tablesCloturees = new ArrayList<>();
    }

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

    public void cloturerTable(Table table) {
        synchronized (this) {
            tablesCloturees.add(table);
            System.out.println("Table clôturée pour le client " + table.getClient() + " avec un montant de " + table.getMontant() + " CHF.");
        }
    }

    public double getRecetteTotal() {
        double total = 0;
        synchronized (this) {
            for (Table table : tablesCloturees) {
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
            for (Table table : tablesCloturees) {
                System.out.println(
                        "Client: " + table.getClient() +
                        ", Date: " + table.getDate() +
                        ", Type: " + table.getTableType() +
                        ", Montant: " + table.getMontant() + " CHF."
                );
            }
            System.out.println("Total des recettes : " + getRecetteTotal() + " CHF.");
        }

    }

    /**
     * Cette méthode est utilisée pour les tests.
     *
     * @return La liste des tables clôturées
     */
    public List<Table> listeTablesCloturees() {
        synchronized (this) {
            return new ArrayList<>(tablesCloturees); // Retourne une copie pour éviter toute modification externe
        }
    }

    /**
     * Vide la recette.
     * Cette méthode est utilisée pour les tests.
     */
    public void viderRecette() {
        synchronized (this) {
            tablesCloturees.clear();
        }
    }
}
