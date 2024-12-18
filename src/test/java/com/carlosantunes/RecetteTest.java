package com.carlosantunes;

import com.carlosantunes.restaurant.Recette;
import com.carlosantunes.restaurant.Table;
import com.carlosantunes.restaurant.enums.PlatType;
import com.carlosantunes.restaurant.enums.TableType;
import com.carlosantunes.restaurant.pont.TaxationPrive;
import com.carlosantunes.restaurant.produit.plat.Plat;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;


public class RecetteTest {

    private Table table;


    @Before
    public void setUp() {
        table = new Table("Lucien", new Date(), TableType.PLAISIR, new TaxationPrive());
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
        Recette.getInstance().viderRecette();
        // Ajouter des produits et clôturer la table
        Plat plat = new Plat("Pâtes", 12.50, PlatType.RICHE);
        table.ajouterProduit(plat);
        Recette.getInstance().setTableRecette(table);
        table = Recette.getInstance().getListeTablesCloturees().stream().findFirst().orElseThrow();
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
