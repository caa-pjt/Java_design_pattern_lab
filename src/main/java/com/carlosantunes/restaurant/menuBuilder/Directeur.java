package com.carlosantunes.restaurant.menuBuilder;

import com.carlosantunes.restaurant.produit.Menu;

public interface Directeur {

    /*
        Le directeur normal et copieux doivent hériter de la même classe abstraite ou interface pour que le client
        puisse les utiliser de manière interchangeable.
     */
    Menu construireMenu();
}
