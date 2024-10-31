package com.carlosantunes.restaurant.decorateur;

import com.carlosantunes.restaurant.produit.Produit;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ExtraDose extends ProduitDecorateur {

    private static final double SUPPLEMENT_EXTRA_DOSE = 0.10; // 10% pour ExtraDose

    public ExtraDose(Produit produit) {
        super(produit);
    }

    @Override
    public double getPrix() {
        // Prix avec majoration de 10%
        double prixMajore = produit.getPrix() * (1 + SUPPLEMENT_EXTRA_DOSE);
        // Arrondi à 2 chiffres après la virgule
        return BigDecimal.valueOf(prixMajore).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    @Override
    public void afficher() {
        produit.afficher();
        System.out.println("Extra Dose : majoration du prix de 10% : " + getPrix());
    }
}
