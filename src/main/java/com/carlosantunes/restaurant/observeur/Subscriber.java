package com.carlosantunes.restaurant.observeur;

import com.carlosantunes.restaurant.Table;

/**
 * Interface pour les abonnés qui souhaitent être notifiés des changements de la table.
 */
public interface Subscriber {
    void update(Table table);
}
