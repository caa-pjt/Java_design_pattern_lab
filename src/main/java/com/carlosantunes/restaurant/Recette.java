package com.carlosantunes.restaurant;

import com.carlosantunes.restaurant.observeur.Subscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe Recette est un singleton. Elle permet de sauvegarder toutes les tables clôturées avec :
 * La date de la clôture,
 * Le client,
 * Le type de table,
 * Le montant de l'addition.
 */
public final class Recette implements Subscriber<Table> {

    private static final String DEVISE = "CHF";

    private static Recette instance = null;

    // Liste pour stocker les tables clôturées
    private final List<Table> historiqueTablesCloturees;

    private Recette() {
        this.historiqueTablesCloturees = new ArrayList<>();
    }


    /**
     * Méthode pour obtenir l'instance de la recette.
     *
     * @return L'instance de la recette.
     */
    public static synchronized Recette getInstance() {
        if (instance == null) {
            instance = new Recette();
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
            historiqueTablesCloturees.add(table);
            System.out.println("Table clôturée pour le client " + table.getClient() + " avec un montant de " + table.getMontant() + DEVISE);
        }
    }

    /**
     * @return Le montant total des recettes.
     */
    public double getMontantTotalRecettes() {
        double total = 0;
        synchronized (this) {
            for (Table table : historiqueTablesCloturees) {
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
            for (Table table : historiqueTablesCloturees) {
                System.out.println(
                        "Client: " + table.getClient() +
                                ", Date: " + table.getDate() +
                                ", Type: " + table.getTableType() +
                                ", Montant: " + table.getMontant() + DEVISE
                );
            }
            System.out.println("Total des recettes : " + getMontantTotalRecettes() + DEVISE);
        }

    }

    
    @Override
    public void update(String message, Table args) {
        if (message.equals("clotureTable")) {
            setTableRecette(args);
        }
    }


    // ============== Méthodes utilisées pour les test unitaires ===========================

    /**
     * @return La liste des tables clôturées
     */
    public List<Table> getListeTablesCloturees() {
        synchronized (this) {
            return new ArrayList<>(historiqueTablesCloturees); // Retourne une copie pour éviter toute modification externe
        }
    }

    /**
     * Vide la recette.
     */
    public void viderRecette() {
        synchronized (this) {
            historiqueTablesCloturees.clear();
        }
    }
}
