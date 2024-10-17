# Created by carlos Antunes at 17.10.2024

Feature: Gestion des tables avec le Abstract Factory Pattern

  Scenario: Créer une table de type VEGAN avec des produits
    Given un restaurant vide
    When je cree une table pour le client "Lucie" de type "VEGAN"
    And j'ajoute un plat "Salade" avec un prix de 8.0 chf a la table
    And j'ajoute une boisson "Eau gazeuse" avec un prix de 2.0 chf a la table
    Then la table contient 2 produits

  Scenario: Créer une table de type PLAISIR avec des produits et verifier si les boissons sont alcoolisées
    Given un restaurant vide
    When je cree une table pour le client "Lucie" de type "PLAISIR"
    And j'ajoute un plat "Steak" avec un prix de 12.0 chf a la table
    And j'ajoute une boisson "Vin" avec un prix de 5.8 chf a la table
    Then la boisson "Vin" est de type "Alcoolisée"

  Scenario: Créer une table de type DIET avec des produits et verifier si les plats sont light
    Given un restaurant vide
    When je cree une table pour le client "Lucie" de type "DIET"
    And j'ajoute un plat "Salade" avec un prix de 8.0 chf a la table
    And j'ajoute une boisson "Coca Light" avec un prix de 2.0 chf a la table
    Then le plat "Salade" est de type "Diet"