package com.carlosantunes.restaurant.builder;

import com.carlosantunes.restaurant.produit.Menu;

public interface Builder {


    void construireEntree();

    void construirePlat();

    void construireDessert();

    void construireBoisson();

    Menu getMenu();

}
