package com.carlosantunes.restaurant.observeur;

/**
 * Interface pour les sujets qui souhaitent être observés par des abonnés.
 * Les sujets peuvent ajouter, supprimer et notifier des abonnés.
 */
public interface Sujet {
    void ajouterObserveur(Subscriber observeur);

    void supprimerObserveur(Subscriber observeur);

    void notifier();
}
