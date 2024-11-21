package com.carlosantunes.restaurant;

import com.carlosantunes.restaurant.decorateur.ProduitDecorateur;
import com.carlosantunes.restaurant.produit.Menu;
import com.carlosantunes.restaurant.produit.boisson.Boisson;
import com.carlosantunes.restaurant.produit.plat.Plat;

/**
 * Visiteur est une interface qui définit les méthodes pour visiter les différents types de produits.
 */
public interface Visiteur {

    void visiter(Boisson boisson);

    void visiter(Plat plat);

    void visiter(Menu menu);

    void visiter(ProduitDecorateur produitDecorateur);
}
