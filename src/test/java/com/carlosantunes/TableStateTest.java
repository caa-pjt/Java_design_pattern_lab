package com.carlosantunes;

import static org.junit.jupiter.api.Assertions.*;

import com.carlosantunes.restaurant.enums.PlatType;
import com.carlosantunes.restaurant.etat.Cloturer;
import com.carlosantunes.restaurant.etat.Reserver;
import com.carlosantunes.restaurant.etat.Servie;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.carlosantunes.restaurant.Table;
import com.carlosantunes.restaurant.enums.TableType;
import com.carlosantunes.restaurant.produit.plat.Plat;

import java.util.Date;
import java.util.HashMap;

/**
 * Tests for the Table state transitions
 */
public class TableStateTest {

    private final HashMap<String, Table> tables = new HashMap<>();

    @BeforeEach
    public void setUp() {
        Table table1 = new Table("Client 1", new Date(), TableType.PLAISIR);
        Table table2 = new Table("Client 2", new Date(), TableType.VEGAN);
        tables.put("table1", table1);
        tables.put("table2", table2);
    }

    @Test
    public void testEtatInitialDoitEtreReserve() {
        Table table = tables.get("table1");
        assertInstanceOf(Reserver.class, table.getEtatDeLaTable());
    }

    @Test
    public void testAccueillirClientChangerEtatServie() {
        Table table = tables.get("table1");
        table.getEtatDeLaTable().accueillirClient(table);
        assertInstanceOf(Servie.class, table.getEtatDeLaTable());
    }

    @Test
    public void testServirProduitDoitServirProduits() {
        Table table = tables.get("table1");
        table.getEtatDeLaTable().accueillirClient(table); // Passer à l'état Servie
        table.ajouterProduit(new Plat("Test Plat", 10.0, PlatType.RICHE));
        table.getEtatDeLaTable().servirProduits(table); // Simule le service des produits

        assertEquals(1, table.getProduitsConsommes().size());
        assertEquals("Test Plat", table.getProduitsConsommes().get(0).getNom());
    }

    @Test
    public void testFermerDoitChangerEtatCloturer() {
        Table table = tables.get("table2");
        table.getEtatDeLaTable().accueillirClient(table); // Passer à l'état Servie
        table.ajouterProduit(new Plat("Test Plat", 10.0, PlatType.RICHE));
        table.getEtatDeLaTable().afficher(table); // Simule l'affichage de la note
        table.getEtatDeLaTable().fermer(table); // Passer à l'état Cloturer
        assertInstanceOf(Cloturer.class, table.getEtatDeLaTable());
    }

    @Test
    public void testDeuxFoisAccueillirClientDoitResterServie() {
        Table table = tables.get("table1");
        table.getEtatDeLaTable().accueillirClient(table); // Passer à l'état Servie
        table.getEtatDeLaTable().accueillirClient(table); // Doit rester à l'état Servie
        assertInstanceOf(Servie.class, table.getEtatDeLaTable());
    }

    @Test
    public void testDeuxTablesDoiventResterIndependantes() {

        // Création de la deuxième table avec un type différent pour tester l'indépendance
        tables.put("table3", new Table("Client 3", new Date(), TableType.VEGAN));
        tables.put("table4", new Table("Client 4", new Date(), TableType.PLAISIR));

        Table table1 = tables.get("table3");
        table1.getEtatDeLaTable().accueillirClient(table1); // Passer à l'état Servie
        table1.ajouterProduit(new Plat("Pizza vegan", 25.80, PlatType.VEGAN));
        table1.getEtatDeLaTable().servirProduits(table1); // Simule le service des produits
        table1.getEtatDeLaTable().fermer(table1);

        Table table2 = tables.get("table4");
        table2.getEtatDeLaTable().accueillirClient(table2); // Passer à l'état Servie
        table2.getEtatDeLaTable().servirProduits(table2); // Simule le service des produits

        // Test si les tables sont indépendantes
        assertEquals(TableType.VEGAN, table1.getTableType());
        assertEquals(TableType.PLAISIR, table2.getTableType());

        // Test si les états sont indépendants
        assertInstanceOf(Cloturer.class, table1.getEtatDeLaTable());
        assertInstanceOf(Servie.class, table2.getEtatDeLaTable());

        // Test si les produits consommés sont indépendants
        assertEquals(1, table1.getProduitsConsommes().size());
        assertEquals(0, table2.getProduitsConsommes().size());
    }
}
