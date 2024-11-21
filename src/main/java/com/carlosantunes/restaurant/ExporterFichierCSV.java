package com.carlosantunes.restaurant;

import com.carlosantunes.restaurant.produit.Menu;
import com.carlosantunes.restaurant.produit.boisson.Boisson;
import com.carlosantunes.restaurant.produit.plat.Plat;

import java.io.FileWriter;
import java.io.Writer;

public class ExporterFichierCSV implements Visiteur, AutoCloseable {


    private Writer writer;

    public ExporterFichierCSV(String cheminFichier) {
        try {
            writer = new FileWriter(cheminFichier, true);
        } catch (Exception e) {
            System.err.println("Erreur lors de l'ouverture du fichier : " + e.getMessage());
        }
    }


    @Override
    public void visiterBoisson(Boisson boisson) {
        exporter(boisson.getNom(), boisson.getPrix(), boisson.getType());
    }

    @Override
    public void visiterPlat(Plat plat) {
        exporter(plat.getNom(), plat.getPrix(), plat.getType());
    }

    @Override
    public void visiterMenu(Menu menu) {
        exporter(menu.getNom(), menu.getPrix(), menu.getType());
    }

    /**
     * Exporte une ligne dans le fichier CSV.
     *
     * @param nom  Le nom du produit.
     * @param prix Le prix du produit.
     * @param type Le type du produit.
     */
    private void exporter(String nom, double prix, String type) {
        try {
            this.writer.append(nom)
                    .append(",")
                    .append(Double.toString(prix))
                    .append(",")
                    .append(type)
                    .append("\n");
            this.writer.flush();
            System.out.println("Ligne exportée : " + nom);
        } catch (Exception e) {
            System.err.println("Erreur lors de l'exportation de la ligne : " + e.getMessage());
        }
    }

    @Override
    public void close() {
        try {
            this.writer.close();
        } catch (Exception e) {
            System.err.println("Erreur lors de la fermeture du fichier : " + e.getMessage());
        }
    }
}
