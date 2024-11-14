# created by : carlos Antunes at 17.10.2024
# Labo 2 tâche 2 - State Pattern

Feature: Gestion des tables dans le restaurant

  Scenario: Accueillir un client et servir des produits
    Given La table est réservée pour "Client A" de type "PLAISIR"
    When je accueille le client "Client A"
    Then la table doit être servi pour "Client A"
    And je peux servir des produits pour "Client A"
    And j'ajoute un produit "Salade" au montant de 8.00 CHF à la table du client "Client A"
    And les produits consommés à la table su client "Client A" doivent inclure "Salade"

  Scenario: Fermer la table après le service
    Given La table est réservée pour "Client C" de type "VEGAN"
    When je accueille le client "Client C"
    Then la table doit être servi pour "Client C"
    And je peux servir des produits pour "Client C"
    And j'ajoute un produit "Salade" au montant de 8.00 CHF à la table du client "Client C"
    And je ferme la table pour "Client C"
    Then la table doit être clôturée pour "Client C"
