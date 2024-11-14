package com.carlosantunes.restaurant;

import com.carlosantunes.restaurant.enums.TableType;
import com.carlosantunes.restaurant.etat.Reserver;
import com.carlosantunes.restaurant.etat.TableState;
import com.carlosantunes.restaurant.observeur.Subscriber;
import com.carlosantunes.restaurant.observeur.Sujet;
import com.carlosantunes.restaurant.pont.Taxation;
import com.carlosantunes.restaurant.produit.Produit;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Table implements Sujet {
    private final String client;
    private final Date date;
    private final TableType tableType;

    private final List<Produit> produitsConsommes;

    // Permet de définir la taxation de la table | pattern Bridge pour définir la taxation
    private final Taxation taxation;

    // Liste des observateurs de la table
    private final List<Subscriber> observeurs = new ArrayList<>();

    /**
     * État courant de la table | pattern State pour gérer l'état de la table
     *
     * @see com.carlosantunes.restaurant.etat.TableState
     */
    private TableState etatDeLaTable;


    /**
     * Constructeur bis de la classe Table avec la taxation
     *
     * @param client    le client de la table
     * @param date      la date de la table
     * @param TableType le type de la table
     * @param taxation  la taxation de la table
     */
    public Table(String client, Date date, TableType TableType, Taxation taxation) {
        this.client = client;
        this.date = date;
        this.tableType = TableType;
        produitsConsommes = new ArrayList<>();

        // Initialisation de l'état courant de la table à "Réservée"
        this.etatDeLaTable = Reserver.getInstance();

        // Initialisation de la taxation de la table à "Privée ou Publique"
        this.taxation = taxation;
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
    public TableType getTableType() {
        return tableType;
    }

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
     *
     * @return l'état courant de la table
     */
    public TableState getEtatDeLaTable() {
        return etatDeLaTable;
    }

    /**
     * Change l'état de la table
     *
     * @param etatDeLaTable l'état à définir
     */
    public void setEtatDeLaTable(TableState etatDeLaTable) {
        this.etatDeLaTable = etatDeLaTable;
    }


    // ======================================== Lab 4 tâche 1 : Bridge pattern =======================================

    /**
     * @return le montant total des produits consommés par table avec la taxation
     */
    public double calculerTaxe() {
        return taxation.calculerTaxe(getMontant());
    }

    // ======================================== Lab 4 tâche 2 : Observer pattern =======================================

    @Override
    public void ajouterObserveur(Subscriber observeur) {
        observeurs.add(observeur);
    }

    @Override
    public void supprimerObserveur(Subscriber observeur) {
        observeurs.remove(observeur);
    }

    @Override
    public void notifier() {
        for (Subscriber observeur : observeurs) {
            observeur.update(this);
        }
    }
}
