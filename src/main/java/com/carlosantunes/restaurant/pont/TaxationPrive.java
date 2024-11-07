package com.carlosantunes.restaurant.pont;

import com.carlosantunes.restaurant.api.TaxationPriveApi;

public class TaxationPrive implements Taxation {

    @Override
    public double calculerTaxe(double montant) {
        return TaxationPriveApi.calculerTaxe(montant);
    }
}
