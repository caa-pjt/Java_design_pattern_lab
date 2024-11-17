package com.carlosantunes.restaurant.iterateurs;

import java.util.Iterator;

public interface Iterateur<T> extends Iterator<T> {

    /**
     * @return true si l'itérateur a un élément suivant, false sinon.
     */
    boolean hasNext();

    /**
     * @return L'élément suivant de l'itérateur.
     */
    T next();

}
