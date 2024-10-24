package com.carlosantunes;

import com.carlosantunes.restaurant.Recette;
import com.carlosantunes.restaurant.Table;
import com.carlosantunes.restaurant.enums.PlatType;
import com.carlosantunes.restaurant.enums.TableType;
import com.carlosantunes.restaurant.produit.plat.Plat;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;


public class RecetteTest {

    private Restaurant restaurant;
    private Table table;


    @Before
    public void setUp() {
        restaurant = new Restaurant();
        table = new Table("Lucien", new Date(), TableType.PLAISIR);
    }

    @Test
    public void testInitialisationTable() {
        assertNotNull(table);
        assertEquals("Lucien", table.getClient());
        assertEquals(TableType.PLAISIR, table.getTableType());
    }


    @Test
    public void testAjouterProduit() {

        Plat plat = new Plat("Pâtes", 12.50, PlatType.RICHE);
        table.ajouterProduit(plat);
        assertEquals(1, table.getProduitsConsommes().size());
        assertEquals(plat, table.getProduitsConsommes().get(0));
    }

    @Test
    public void testCloturerTable() {
        // Ajouter des produits et clôturer la table
        Plat plat = new Plat("Pâtes", 12.50, PlatType.RICHE);
        table.ajouterProduit(plat);
        restaurant.cloturerTable(table);
        Recette.TableCloturee table = Recette.getInstance().listeTablesCloturees().stream().findFirst().orElseThrow();
        assertEquals(12.50, table.getMontant(), 0.01);
    }

    @Test
    public void testSingletonInstance() {
        // Obtenir deux instances
        Recette instance1 = Recette.getInstance();
        Recette instance2 = Recette.getInstance();

        // Vérifier qu'il s'agit de la même instance
        assertSame(instance1, instance2);
    }

    @Test
    public void testNoNewInstance() {
        // Tester que l'instance obtenue n'est pas null
        Recette instance = Recette.getInstance();
        assertNotNull(instance);

    }

}
