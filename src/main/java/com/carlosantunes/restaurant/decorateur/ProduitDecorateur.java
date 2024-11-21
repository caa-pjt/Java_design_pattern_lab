package com.carlosantunes.restaurant.decorateur;

import com.carlosantunes.restaurant.produit.Produit;

public abstract class ProduitDecorateur implements Produit {
    protected Produit produit;

    public ProduitDecorateur(Produit produit) {
        this.produit = produit;
    }

    @Override
    public String getNom() {
        return produit.getNom();
    }

    @Override
    public String getType() {
        return produit.getType();
    }

    @Override
    public abstract double getPrix();

    @Override
    public void afficher() {
        produit.afficher();
    }


}
