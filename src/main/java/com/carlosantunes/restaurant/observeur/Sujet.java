package com.carlosantunes.restaurant.observeur;

/**
 * Interface pour les sujets qui souhaitent être observés par des abonnés.
 * Les sujets peuvent ajouter, supprimer et notifier des abonnés.
 */
public interface Sujet<T> {
    /**
     * Ajoute un abonné à la liste des abonnés.
     *
     * @param observer L'abonné à ajouter.
     */
    void ajouterObserver(Subscriber<T> observer);

    /**
     * Supprime un abonné de la liste des abonnés.
     *
     * @param observer L'abonné à supprimer.
     */
    void supprimerObserver(Subscriber<T> observer);

    /**
     * Notifie tous les abonnés avec un message.
     *
     * @param message Le message à notifier.
     */
    void notifier(String message);

}
