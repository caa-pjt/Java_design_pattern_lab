package com.carlosantunes;

import com.carlosantunes.restaurant.Recette;
import com.carlosantunes.restaurant.Table;
import com.carlosantunes.restaurant.enums.TableType;
import com.carlosantunes.restaurant.iterateurs.RecetteIteratorParMois;
import com.carlosantunes.restaurant.pont.TaxationEntreprise;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RecetteIteratorParMoisTest {

    private Recette recette;

    @BeforeEach
    void setUp() {
        List<Table> tables = Arrays.asList(
                new Table("Charlie", Date.from(LocalDate.of(2024, 1, 16)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant()), TableType.PLAISIR,
                        new TaxationEntreprise()), // Mois 1
                new Table("Bob", Date.from(LocalDate.of(2024, 2, 16)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant()), TableType.PLAISIR, new TaxationEntreprise()),  // Mois 2
                new Table("Marie", Date.from(LocalDate.of(2024, 1, 16)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant()), TableType.PLAISIR,
                        new TaxationEntreprise()) // Mois 1
        );
        recette = mock(Recette.class);
        when(recette.getListeTablesCloturees()).thenReturn(tables);
    }

    @Test
    void testHasNext_ReturnsTrueWhenValidElement() {
        RecetteIteratorParMois iterator = new RecetteIteratorParMois(recette, 1);

        assertTrue(iterator.hasNext());  // Le premier élément correspond au mois 1
    }

    @Test
    void testNext_ReturnsTableForGivenMonth() {
        RecetteIteratorParMois iterator = new RecetteIteratorParMois(recette, 1);

        Table table = iterator.next();
        Assertions.assertEquals(1, table.getLocalDate().getMonthValue()); // Mois 1

        table = iterator.next();
        Assertions.assertEquals(1, table.getLocalDate().getMonthValue()); // Mois 1
    }

    @Test
    void testNext_ThrowsExceptionWhenNoMoreTablesForMonth() {
        RecetteIteratorParMois iterator = new RecetteIteratorParMois(recette, 1);

        iterator.next(); // Mois 1
        iterator.next(); // Mois 1

        assertThrows(NoSuchElementException.class, iterator::next);
    }
}
