package com.carlosantunes;

import static org.junit.jupiter.api.Assertions.*;

import com.carlosantunes.restaurant.enums.PlatType;
import com.carlosantunes.restaurant.etat.Cloturer;
import com.carlosantunes.restaurant.etat.Reserver;
import com.carlosantunes.restaurant.etat.Servie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.carlosantunes.restaurant.Table;
import com.carlosantunes.restaurant.enums.TableType;
import com.carlosantunes.restaurant.produit.plat.Plat;

import java.util.Date;

/**
 * Tests for the Table state transitions
 */
public class TableStateTest {


    private Table table;

    @BeforeEach
    public void setUp() {
        table = new Table("Client Test", new Date(), TableType.PLAISIR);
    }

    @Test
    public void testEtatInitialDoitEtreReserve() {
        assertInstanceOf(Reserver.class, table.getEtatDeLaTable());
    }

    @Test
    public void testAccueillirClientChangerEtatServie() {
        table.accueillirClient();
        assertInstanceOf(Servie.class, table.getEtatDeLaTable());
    }

    @Test
    public void testServirProduitDoitServirProduits() {
        table.accueillirClient(); // Passer à l'état Servie
        table.ajouterProduit(new Plat("Test Plat", 10.0, PlatType.RICHE));
        table.servirProduits(); // Simule le service des produits

        assertEquals(1, table.getProduitsConsommes().size());
        assertEquals("Test Plat", table.getProduitsConsommes().get(0).getNom());
    }

    @Test
    public void testFermerDoitChangerEtatCloturer() {
        table.accueillirClient(); // Passer à l'état Servie
        table.fermer();
        assertInstanceOf(Cloturer.class, table.getEtatDeLaTable());
    }

    @Test
    public void testDeuxFoisAccueillirClientDoitResterServie() {
        table.accueillirClient(); // Passer à l'état Servie
        table.accueillirClient(); // Doit rester à l'état Servie
        assertInstanceOf(Servie.class, table.getEtatDeLaTable());
    }

    @Test
    public void testDeuxTablesDoiventResterIndependantes() {

        // Création de la deuxième table avec un type différent pour tester l'indépendance
        Table table2 = new Table("Client 2", new Date(), TableType.VEGAN);
        table.accueillirClient(); // Passer à l'état Servie
        table2.accueillirClient(); // Passer à l'état Servie
        table2.ajouterProduit(new Plat("Pizza vegan", 25.80, PlatType.VEGAN));
        table2.servirProduits(); // Simule le service des produits
        table2.fermer();

        // Test si les tables sont indépendantes
        assertEquals(table.getTableType(), TableType.PLAISIR);
        assertEquals(table2.getTableType(), TableType.VEGAN);

        // Test si les états sont indépendants
        assertInstanceOf(Servie.class, table.getEtatDeLaTable());
        assertInstanceOf(Cloturer.class, table2.getEtatDeLaTable());

        // Test si les produits consommés sont indépendants
        assertEquals(0, table.getProduitsConsommes().size());
        assertEquals(1, table2.getProduitsConsommes().size());
    }
}
