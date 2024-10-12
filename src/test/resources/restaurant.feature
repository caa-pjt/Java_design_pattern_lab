# Created by carlos Antunes at 02.10.2024

## Tâche 1
Feature: Gestion du restaurant avec le Composite Pattern
  Scenario: Ajouter un produit au restaurant
    Given un restaurant vide
    When j'ajoute un plat "Salade" avec un prix de 5.50 chf
    Then le restaurant contient 1 produit

  Scenario: Calculer le prix total d'un menu
    Given un menu "Menu Déjeuner" de type "PLAISIR"
    When j'ajoute un plat "Steak" au menu avec un prix de 12.0 chf
    And j'ajoute une boisson "Coca" avec un prix de 3.0 chf
    Then le prix total du menu est 15.0 chf


## Tâche 2 Gestion des tables avec le Abstract Factory Pattern
  Scenario: Créer une table de type VEGAN avec des produits
    Given un restaurant vide
    When je cree une table pour le client "Lucie" de type "VEGAN"
    And j'ajoute un plat "Salade" avec un prix de 8.0 chf a la table
    And j'ajoute une boisson "Eau gazeuse" avec un prix de 2.0 chf a la table
    Then la table contient 2 produits