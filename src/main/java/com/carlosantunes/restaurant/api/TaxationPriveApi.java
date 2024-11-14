package com.carlosantunes.restaurant.api;

public class TaxationPriveApi {

    private static final double TAXE = 1.15; // 15% de taxe

    public static double calculerTaxe(double montant) {
        return montant * TAXE;
    }
}
