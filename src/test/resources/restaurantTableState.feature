# created by : carlos Antunes at 17.10.2024
# Labo 2 tâche 2 - State Pattern

Feature: Gestion des tables dans le restaurant

  Scenario: Accueillir un client et servir des produits
    Given une table réservée pour "Client A" de type "PLAISIR"
    When je accueille le client
    Then la table doit être servie
    And je peux servir des produits
    And j'ajoute un produit "Salade" au montant de 8.00 CHF
    And les produits consommés doivent inclure "Salade"

  Scenario: Fermer la table après le service
    Given une table réservée pour "Client C" de type "VEGAN"
    When je accueille le client
    And je sers des produits
    And je ferme la table
    Then la table doit être clôturée
