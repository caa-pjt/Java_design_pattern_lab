# Created by : carlos Antunes
# Labo 2 tâche 1 - Singleton Pattern

Feature: Gestion des tables clôturées et des recettes

  Scenario: Clôturer plusieurs tables et afficher les statistiques
    Given le restaurant a deux tables avec les clients "Luc" et "Janette"
    When les tables sont clôturées pour les clients "Luc" et "Janette"
    Then les recettes totales doivent être 31.00 CHF.

    Scenario: clôturer plusieurs tables et afficher les statistiques
    Given le restaurant a deux tables avec les clients "Luc" et "Janette"
    When les tables sont clôturées pour les clients "Luc" et "Janette"
    Then les recettes totales doivent être 31.00 CHF.
    Then le restaurant doit avoir 2 tables clôturées

  Scenario: Clôturer une table et vérifier les recettes
    Given le restaurant a une table avec le client "Moris" et des produits
    When la table est clôturée pour le client "Moris"
    Then la recette doit contenir une table pour le client "Moris" avec un montant total de 20.50 CHF.