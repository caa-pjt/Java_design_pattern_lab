package com.carlosantunes.restaurant.etat;

import com.carlosantunes.restaurant.Table;

public abstract class TableState {

    public abstract TableState accueillirClient(Table table);
    public abstract TableState servirProduits(Table table);
    public abstract TableState fermer(Table table);
    public abstract void afficher(Table table);

}
