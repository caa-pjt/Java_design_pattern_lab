package com.carlosantunes.restaurant.iterateurs;

import com.carlosantunes.restaurant.Recette;
import com.carlosantunes.restaurant.Table;

import java.util.List;
import java.util.NoSuchElementException;

public class RecetteIteratorParMois implements RecetteIterateur<Table> {
    private final int month;
    private int index;
    List<Table> tableList;

    public RecetteIteratorParMois(Recette recette, int month) {
        this.tableList = recette.getListeTablesCloturees();
        this.month = month;
        this.index = 0;
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
        if (hasNext()) {
            return tableList.get(index++);
        }
        throw new NoSuchElementException("Il n'y a plus de tables clôturées pour le mois " + month + ".");
    }

    /**
     * Vérifie si le montant de la table est supérieur à 50 CHF.
     *
     * @param table La table courante à vérifier.
     * @return true si le montant est supérieur à 50 CHF, false sinon.
     */
    private boolean isValid(Table table) {
        return table.getLocalDate().getMonthValue() == month;
    }
}