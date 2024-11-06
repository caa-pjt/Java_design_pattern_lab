package com.carlosantunes.restaurant;

import com.carlosantunes.restaurant.enums.TableType;
import com.carlosantunes.restaurant.etat.Reserver;
import com.carlosantunes.restaurant.etat.TableState;
import com.carlosantunes.restaurant.produit.Produit;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Table {
    private final String client;
    private final Date date;
    private final TableType Type;

    private final List<Produit> produitsConsommes;

    /**
     * État courant de la table | pattern State pour gérer l'état de la table
     * @see com.carlosantunes.restaurant.etat.TableState
     */
    private TableState etatDeLaTable;

    public Table(String client, Date date, TableType type) {
        this.client = client;
        this.date = date;
        this.Type = type;
        produitsConsommes = new ArrayList<>();

        // Initialisation de l'état courant de la table à "Réservée"
        this.etatDeLaTable = Reserver.getInstance();
    }

    /**
     * Ajoute un produit consommé à la table
     *
     * @param produit le produit consommé
     */
    public void ajouterProduit(Produit produit) {
        produitsConsommes.add(produit);
    }

    /**
     * Affiche les produits consommés par la table
     */
    public void afficherProduitsConsommes() {
        System.out.println("Table de " + client + " (" + Type + ")" + " - " + getDate());
        for (Produit produit : produitsConsommes) {
            produit.afficher();
        }
    }

    /**
     * @return le client de la table
     */
    public String getClient() {
        return client;
    }

    /**
     * @return la date de la table
     */
    public TableType getTableType() { return Type; }

    public String getDate() {
        String pattern = "dd MMMM yyyy HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("fr", "FR"));
        return simpleDateFormat.format(date);
    }

    /**
     * @return le montant total des produits consommés par table
     */
    public double getMontant() {
        double montant = 0;
        for (Produit produit : produitsConsommes) {
            montant += produit.getPrix();
        }
        return montant;
    }

    /**
     * @return la liste des produits consommés par table
     */
    public List<Produit> getProduitsConsommes() {
        return produitsConsommes;
    }

    // ======================================== Lab 2 tâche 2 : State pattern ========================================



    /**
     * Vérifie l'état de la table
     * @return l'état courant de la table
     */
    public TableState getEtatDeLaTable() {
        return etatDeLaTable;
    }

    /**
     * Change l'état de la table
     * @param etatDeLaTable l'état à définir
     */
    public void setEtatDeLaTable(TableState etatDeLaTable) {
        this.etatDeLaTable = etatDeLaTable;
    }
}
