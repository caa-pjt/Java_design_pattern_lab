package com.carlosantunes.restaurant.observeur;

/**
 * Interface pour les abonnés qui souhaitent être notifiés des changements de la table.
 */
public interface Subscriber<T> {

    /**
     * Met à jour l'abonné avec un message et des arguments.
     *
     * @param message le message à afficher
     * @param args    les arguments à afficher (objet)
     */
    void update(String message, T args);
}
