package com.carlosantunes.restaurant.iterateurs;

import com.carlosantunes.restaurant.Table;

import java.util.Iterator;

public interface RecetteIterateur {

    Iterator<Table> recetteIterateurParMois(int month);

    Iterator<Table> recetteIteratorMontantSuperieurA50();

    Iterateur<Table> recetteIterateurMontantSuperieurMontant(double montant);
}
