package com.carlosantunes.restaurant;

import com.carlosantunes.restaurant.decorateur.ProduitDecorateur;
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
    public void visiter(Boisson boisson) {
        exporter(boisson.getNom(), boisson.getPrix(), boisson.getType());
    }

    @Override
    public void visiter(Plat plat) {
        exporter(plat.getNom(), plat.getPrix(), plat.getType());
    }

    @Override
    public void visiter(Menu menu) {
        exporter(menu.getNom(), menu.getPrix(), menu.getType());
    }

    @Override
    public void visiter(ProduitDecorateur produitDecorateur) {
        exporter(produitDecorateur.getNom(), produitDecorateur.getPrix(), produitDecorateur.getType());
    }

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
