package com.carlosantunes.restaurant;

import com.carlosantunes.restaurant.produit.Menu;
import com.carlosantunes.restaurant.produit.boisson.Boisson;
import com.carlosantunes.restaurant.produit.plat.Plat;

public class ExporterFichierCSV implements Visiteur {

    private final String nomFichier;

    public ExporterFichierCSV(String nomFichier) {
        this.nomFichier = nomFichier;
    }

    @Override
    public void visiterBoisson(Boisson boisson) {
        boisson.exportBoisson(nomFichier);
    }

    @Override
    public void visiterPlat(Plat plat) {
        plat.exportPlat(nomFichier);
    }

    @Override
    public void visiterMenu(Menu menu) {
        menu.visiterMenu(nomFichier);
    }
}
