package com.carlosantunes.restaurant.etat;

import com.carlosantunes.restaurant.Table;

public abstract class TableState {
    protected Table table;

    protected TableState(Table table) {
        this.table = table;
    }

    public abstract TableState accueillirClient();
    public abstract TableState servirProduits();
    public abstract TableState fermer();
    public abstract void afficher();

}
