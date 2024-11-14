package com.carlosantunes.restaurant.api;

public class TaxationEntrepriseApi {

    private static final double TAXE = 1.05; // 5% de taxe

    public static double calculerTaxe(double montant) {
        return montant * TAXE;
    }
}
