package com.carlosantunes.restaurant.pont;

import com.carlosantunes.restaurant.api.TaxationEntrepriseApi;

public class TaxationEntreprise implements Taxation {

    @Override
    public double calculerTaxe(double montant) {
        return TaxationEntrepriseApi.calculerTaxe(montant);
    }
}
