# Created by carlos Antunes at 02.10.2024
Feature: Gestion du restaurant
  Scenario: Ajouter un produit au restaurant
    Given un restaurant vide
    When j'ajoute un plat "Salade" avec un prix de 5.50 chf
    Then le restaurant contient 1 produit

  Scenario: Calculer le prix total d'un menu
    Given un menu "Menu Déjeuner"
    When j'ajoute un plat "Steak" au menu avec un prix de 12.0 chf
    And j'ajoute une boisson "Coca" avec un prix de 3.0 chf
    Then le prix total du menu est 15.0 chf