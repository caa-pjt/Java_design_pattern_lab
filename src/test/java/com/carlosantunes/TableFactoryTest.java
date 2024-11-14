package com.carlosantunes;

import com.carlosantunes.restaurant.Table;
import com.carlosantunes.restaurant.TableFactory;
import com.carlosantunes.restaurant.enums.BoissonType;
import com.carlosantunes.restaurant.enums.PlatType;
import com.carlosantunes.restaurant.enums.TableType;
import com.carlosantunes.restaurant.fabrique.CreateurProduit;
import com.carlosantunes.restaurant.pont.TaxationPrive;
import com.carlosantunes.restaurant.produit.boisson.Boisson;
import com.carlosantunes.restaurant.produit.plat.Plat;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TableFactoryTest {

    @Before
    public void setUp() {
    }

    @Test
    public void testCreationTableVegan() {

        String vegan = TableType.VEGAN.toString();

        // Utilisation de la fabrique pour choisir le type de table
        CreateurProduit createurType = TableFactory.createTable(TableType.valueOf(vegan));

        // Création de la table
        Table tableVegan = new Table("Client1", new Date(), TableType.valueOf(vegan), new TaxationPrive());


        // Vérification du type de la table
        assertEquals(TableType.VEGAN, tableVegan.getTableType());

        // Ajout de produits spécifiques pour une table Vegan
        Plat platVegan = createurType.creerPlat("Salade", 10.0);
        Boisson boissonLight = createurType.creerBoisson("Eau", 5.0);
        tableVegan.ajouterProduit(platVegan);
        tableVegan.ajouterProduit(boissonLight);

        // Vérification des produits ajoutés
        assertTrue(tableVegan.getProduitsConsommes().contains(platVegan));
        assertTrue(tableVegan.getProduitsConsommes().contains(boissonLight));

        // Vérification du nombre de produits
        assertEquals(2, tableVegan.getProduitsConsommes().size());
    }

    @Test
    public void testCreationTablePlaisir() {

        String plaisir = TableType.PLAISIR.toString();

        // Utilisation de la fabrique pour choisir le type de table
        CreateurProduit createurPlaisir = TableFactory.createTable(TableType.valueOf(plaisir));
        Table tablePlaisir = new Table("Client2", new Date(), TableType.valueOf(plaisir), new TaxationPrive());

        // Vérification du type de la table
        assertEquals(TableType.PLAISIR, tablePlaisir.getTableType());

        // Ajout de produits spécifiques pour une table Plaisir
        Plat platRiche = createurPlaisir.creerPlat(PlatType.RICHE.getType(), 20.0);
        Boisson boissonAlcoolisee = createurPlaisir.creerBoisson(BoissonType.ALCOOLISEE.getType(), 10.0);
        tablePlaisir.ajouterProduit(platRiche);
        tablePlaisir.ajouterProduit(boissonAlcoolisee);

        // Vérification des produits ajoutés
        assertTrue(tablePlaisir.getProduitsConsommes().contains(platRiche));
        assertTrue(tablePlaisir.getProduitsConsommes().contains(boissonAlcoolisee));

        // vérification du type de plat
        assertEquals(PlatType.RICHE.getType(), platRiche.getType());  // Comparer l'énumération ici, pas la chaîne
        // vérification du type de boisson
        assertEquals(BoissonType.ALCOOLISEE.getType(), boissonAlcoolisee.getType());  // Comparer l'énumération ici aussi

        // Vérification du nombre de produits
        assertEquals(2, tablePlaisir.getProduitsConsommes().size());
    }


}
