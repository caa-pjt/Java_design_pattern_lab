package com.carlosantunes.restaurant.iterateurs;

import com.carlosantunes.restaurant.Table;

import java.util.List;
import java.util.NoSuchElementException;

public class RecetteIterateurMontantSuperieurMontant implements Iterateur<Table> {

    private int index;
    private final double montant;
    private final List<Table> tableList;

    public RecetteIterateurMontantSuperieurMontant(List<Table> tables, double montant) {
        this.index = 0;
        this.montant = montant;
        this.tableList = tables;
    }

    @Override
    public boolean hasNext() {
        while (index < tableList.size()) {
            if (isValid(tableList.get(index))) {
                return true;
            }
            index++;
        }
        return false;
    }

    @Override
    public Table next() {
        while (index < tableList.size()) {
            Table table = tableList.get(index++);
            if (isValid(table)) {
                return table;
            }
        }
        throw new NoSuchElementException("Il n'y a plus de tables clôturées avec un montant supérieur à 50 CHF.");
    }

    /**
     * Vérifie si le montant de la table est supérieur à 50 CHF.
     *
     * @param table La table courante à vérifier.
     * @return true si le montant est supérieur au montant donné, false sinon.
     */
    private boolean isValid(Table table) {
        return table.getMontant() > this.montant;
    }
}

