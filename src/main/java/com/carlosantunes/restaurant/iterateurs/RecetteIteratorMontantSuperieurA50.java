package com.carlosantunes.restaurant.iterateurs;

import com.carlosantunes.restaurant.Table;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Itérateur pour obtenir les tables clôturées avec un montant supérieur à 50 CHF.
 */
public class RecetteIteratorMontantSuperieurA50 implements Iterateur<Table> {

    private int index;
    private final List<Table> tableList;

    public RecetteIteratorMontantSuperieurA50(List<Table> tables) {
        this.index = 0;
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
     * @return true si le montant est supérieur à 50 CHF, false sinon.
     */
    private boolean isValid(Table table) {
        return table.getMontant() > 50;
    }
}