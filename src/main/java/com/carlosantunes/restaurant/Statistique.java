package com.carlosantunes.restaurant;

import com.carlosantunes.restaurant.produit.Produit;

import java.util.ArrayList;
import java.util.List;

public final class Statistique {

    private static Statistique instance = null;
    private final List<Produit> produitsConsommes = new ArrayList<>();

    private Statistique() {
    }

    public static synchronized Statistique getInstance() {
        if (instance == null) {
            instance = new Statistique();
        }
        return instance;
    }

    public synchronized void addProduit(Produit produit) {
        produitsConsommes.add(produit);
    }

    public synchronized int getNombreProduitsConsommes() {
        return produitsConsommes.size();
    }

    public synchronized double getMontantTotal() {
        return produitsConsommes.stream().mapToDouble(Produit::getPrix).sum();
    }

    /**
     * Exporte les produits consommés dans un fichier CSV.
     *
     * @param filePath Le chemin du fichier CSV.
     */
    public synchronized void exporterProduits(String filePath) {
        try (ExporterFichierCSV exporter = new ExporterFichierCSV(filePath)) {
            System.out.println("Exportation des produits consommés..." + produitsConsommes.size() + " produits à exporter.");
            produitsConsommes.forEach(produit -> produit.exporter(exporter));
        }
    }
}
