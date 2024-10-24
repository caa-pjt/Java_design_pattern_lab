package com.carlosantunes.restaurant;

import com.carlosantunes.restaurant.enums.TableType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * La classe Recette est un singleton. Elle permet de sauvegarder toutes les tables clôturées avec :
 * La date,
 * Le type de client,
 * Le montant de l'addition.
 */
public final class Recette {

    private static Recette instance = null;

    // Liste pour stocker les tables clôturées
    private final List<TableCloturee> tablesCloturees;

    private Recette() {
        this.tablesCloturees = new ArrayList<>();
    }

    public synchronized static Recette getInstance() {
        if (Recette.instance == null) {
            Recette.instance = new Recette();
        }
        return Recette.instance;
    }

    /**
     * Ajoute une table clôturée à la recette.
     *
     * @param client Le nom du client
     * @param date La date de la table
     * @param type Le type de table
     * @param montant Le montant total de l'addition
     */
    public void ajouterTableCloturee(String client, Date date, TableType type, double montant) {
        TableCloturee table = new TableCloturee(client, date, type, montant);
        tablesCloturees.add(table);
    }

    /**
     * Affiche les statistiques des tables clôturées.
     */
    public void afficherStatistiques() {
        double total = 0;
        System.out.println("Statistiques des tables clôturées :");
        for (TableCloturee table : tablesCloturees) {
            System.out.println(
                    "Client: " + table.getClient() +
                    ", Date: " + table.getDate() +
                    ", Type: " + table.getType() +
                    ", Montant: " + table.getMontant() + " CHF."
            );
            total += table.getMontant();
        }
        System.out.println("Total des recettes : " + total + " CHF.");
    }

    /**
     * Retourne la liste des tables clôturées.
     *
     * @return La liste des tables clôturées
     */
    public List<TableCloturee> listeTablesCloturees() {
        return this.tablesCloturees;
    }

    /**
     * Vide la recette.
     * Cette méthode est utilisée pour les tests.
     */
    public void viderRecette() {
        this.tablesCloturees.clear();
    }

    /**
     * Classe interne pour stocker les tables clôturées.
     * Chaque table clôturée est composée du nom du client, de la date, du type de table et du montant total de l'addition.
     * Cette classe est publique, car elle est utilisée que par la classe Recette et pour les tests.
     */
    public static class TableCloturee {
        private final String client;
        private final Date date;
        private final TableType type;
        private final double montant;

        public TableCloturee(String client, Date date, TableType type, double montant) {
            this.client = client;
            this.date = date;
            this.type = type;
            this.montant = montant;
        }

        public String getClient() {
            return client;
        }

        public Date getDate() {
            return date;
        }

        public TableType getType() {
            return type;
        }

        public double getMontant() {
            return montant;
        }
    }
}
