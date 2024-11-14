# Created by carlos Antunes at 17.10.2024

Feature: Gestion des tables avec le Abstract Factory Pattern

  Scenario: Créer une table de type VEGAN avec des produits
    Given un restaurant vide
    Given La table est réservée pour "Client A" de type "PLAISIR"
    And j'ajoute un produit "Salade" au montant de 8.00 CHF à la table du client "Client A"
    And j'ajoute un produit "Boisson" au montant de 5.00 CHF à la table du client "Client A"
    Then la table du client "Client A" contient 2 produits

  Scenario: Créer une table de type PLAISIR avec des produits et verifier si les boissons sont alcoolisées
    Given un restaurant vide
    When La table de type "PLAISIR" est réservée pour "Lucie"
    And j'ajoute un plat "Steak" avec un prix de 12.00 chf a la table du client "Lucie"
    And j'ajoute un boisson "Vin" au montant de 5.80 CHF à la table du client "Lucie"
    Then la boisson "Vin" est de type "Alcoolisée" pour le client "Lucie"

  Scenario: Créer une table de type DIET avec des produits et verifier si les plats sont light
    Given un restaurant vide
    When La table de type "DIET" est réservée pour "Lucie"
    And j'ajoute un plat "Salade" avec un prix de 8.00 chf a la table du client "Lucie"
    And j'ajoute un boisson "Coca Light" au montant de 2.0 CHF à la table du client "Lucie"
    Then le plat "Salade" est de type "Diet" pour le client "Lucie"