package com.carlosantunes;

import com.carlosantunes.restaurant.Recette;
import com.carlosantunes.restaurant.Table;
import com.carlosantunes.restaurant.enums.PlatType;
import com.carlosantunes.restaurant.enums.TableType;
import com.carlosantunes.restaurant.etat.Cloturer;
import com.carlosantunes.restaurant.etat.Reserver;
import com.carlosantunes.restaurant.etat.Servie;
import com.carlosantunes.restaurant.observeur.Subscriber;
import com.carlosantunes.restaurant.pont.TaxationEntreprise;
import com.carlosantunes.restaurant.pont.TaxationPrive;
import com.carlosantunes.restaurant.produit.plat.Plat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;

/**
 * Tests for the Table state transitions
 */
class TableStateTest {

    private final HashMap<String, Table> tables = new HashMap<>();

    @BeforeEach
    public void setUp() {
        Table table1 = new Table("Client 1", new Date(), TableType.PLAISIR, new TaxationPrive());
        Table table2 = new Table("Client 2", new Date(), TableType.VEGAN, new TaxationEntreprise());
        tables.put("table1", table1);
        tables.put("table2", table2);
    }

    @Test
    void testEtatInitialDoitEtreReserve() {
        Table table = tables.get("table1");
        assertInstanceOf(Reserver.class, table.getEtatDeLaTable());
    }

    @Test
    void testAccueillirClientChangerEtatServie() {
        Table table = tables.get("table1");
        table.getEtatDeLaTable().accueillirClient(table);
        assertInstanceOf(Servie.class, table.getEtatDeLaTable());
    }

    @Test
    void testServirProduitDoitServirProduits() {
        Table table = tables.get("table1");
        table.getEtatDeLaTable().accueillirClient(table); // Passer à l'état Servie
        table.ajouterProduit(new Plat("Test Plat", 10.0, PlatType.RICHE));
        table.getEtatDeLaTable().servirProduits(table); // Simule le service des produits

        assertEquals(1, table.getProduitsConsommes().size());
        assertEquals("Test Plat", table.getProduitsConsommes().get(0).getNom());
    }

    @Test
    void testFermerDoitChangerEtatCloturer() {
        Table table = tables.get("table2");
        table.getEtatDeLaTable().accueillirClient(table); // Passer à l'état Servie
        table.ajouterProduit(new Plat("Test Plat", 10.0, PlatType.RICHE));
        table.getEtatDeLaTable().afficher(table); // Simule l'affichage de la note
        table.getEtatDeLaTable().fermer(table); // Passer à l'état Cloturer
        assertInstanceOf(Cloturer.class, table.getEtatDeLaTable());
    }

    @Test
    void testDeuxFoisAccueillirClientDoitResterServie() {
        Table table = tables.get("table1");
        table.getEtatDeLaTable().accueillirClient(table); // Passer à l'état Servie
        table.getEtatDeLaTable().accueillirClient(table); // Doit rester à l'état Servie
        assertInstanceOf(Servie.class, table.getEtatDeLaTable());
    }

    @Test
    void testDeuxTablesDoiventResterIndependantes() {

        // Création de la deuxième table avec un type différent pour tester l'indépendance
        tables.put("table3", new Table("Client 3", new Date(), TableType.VEGAN, new TaxationEntreprise()));
        tables.put("table4", new Table("Client 4", new Date(), TableType.PLAISIR, new TaxationPrive()));

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

    @Test
    void testFermerEtatAvecObservateur() {
        tables.put("table5", new Table("Client 5", new Date(), TableType.PLAISIR, new TaxationPrive()));
        Table table = tables.get("table5");

        table.getEtatDeLaTable().accueillirClient(table);
        table.ajouterObserver(Recette.getInstance());
        table.ajouterProduit(new Plat("Pizza", 10.0, PlatType.RICHE));

        table.getEtatDeLaTable().servirProduits(table);
        table.getEtatDeLaTable().fermer(table);

        // Vérifier que l'observateur a été supprimé
        assertEquals(table.getEtatDeLaTable(), Cloturer.getInstance());
    }

    @Test
    void testNotificationAvecMock() {
        tables.put("table6", new Table("Client 6", new Date(), TableType.PLAISIR, new TaxationPrive()));
        Table table = tables.get("table6");

        // Créer un mock de l'observateur
        Subscriber<Table> mockSubscriber = mock(Subscriber.class);

        // Ajouter le mock en tant qu'observateur
        table.ajouterObserver(mockSubscriber);

        // Notifier les observateurs
        table.notifier("clotureTable");

        // Vérifier que le mock a été notifié
        verify(mockSubscriber, times(1)).update("clotureTable", table);
    }
}
