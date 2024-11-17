package com.carlosantunes;

import com.carlosantunes.restaurant.Recette;
import com.carlosantunes.restaurant.Table;
import com.carlosantunes.restaurant.enums.BoissonType;
import com.carlosantunes.restaurant.enums.PlatType;
import com.carlosantunes.restaurant.enums.TableType;
import com.carlosantunes.restaurant.iterateurs.RecetteIteratorMontantSuperieurA50;
import com.carlosantunes.restaurant.pont.TaxationEntreprise;
import com.carlosantunes.restaurant.pont.TaxationPrive;
import com.carlosantunes.restaurant.produit.Produit;
import com.carlosantunes.restaurant.produit.boisson.Boisson;
import com.carlosantunes.restaurant.produit.plat.Plat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RecetteIteratorMontantSuperieurA50Test {

    private Recette recette;
    private List<Produit> produits;

    @BeforeEach
    void setUp() {
        List<Table> tables = Arrays.asList(
                new Table("Sophie", new Date(), TableType.VEGAN, new TaxationPrive()),
                new Table("Bob", new Date(), TableType.PLAISIR, new TaxationEntreprise())
        );
        produits = List.of(
                new Boisson("Vin", 60, BoissonType.ALCOOLISEE),
                new Plat("Pâtes", 10, PlatType.RICHE)
        );
        tables.forEach(table -> table.ajouterProduit(produits.get(0)));
        tables.get(1).ajouterProduit(produits.get(1));

        // Mock Recette
        recette = mock(Recette.class);
        when(recette.getListeTablesCloturees()).thenReturn(tables);
    }

    @Test
    void testHasNext_ReturnsTrueWhenValidElement() {
        RecetteIteratorMontantSuperieurA50 iterator = new RecetteIteratorMontantSuperieurA50(recette);

        assertTrue(iterator.hasNext());  // Le premier élément a un montant > 50
    }

    @Test
    void testNext_ReturnsTableWithAmountGreaterThan50() {
        RecetteIteratorMontantSuperieurA50 iterator = new RecetteIteratorMontantSuperieurA50(recette);

        Table table = iterator.next();
        assertEquals(60, table.getMontant());

        table = iterator.next();
        assertEquals(70, table.getMontant());
    }

    @Test
    void testNext_ThrowsExceptionWhenNoMoreValidElements() {
        RecetteIteratorMontantSuperieurA50 iterator = new RecetteIteratorMontantSuperieurA50(recette);

        iterator.next(); // 60 CHF
        iterator.next(); // 70 CHF

        assertThrows(NoSuchElementException.class, iterator::next);
    }
}
