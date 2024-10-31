# created by : carlos Antunes at 31.10.2024
# Labo 3 tâche 2 - Decorator Pattern

Feature: Test du décorateur ExtraTaste

  Scenario: Calcul du prix avec ExtraTaste
    Given un produit de base avec un prix de 10.00 CHF
    When appliquer ExtraTaste au produit
    Then le prix total doit être 11.50 CHF

