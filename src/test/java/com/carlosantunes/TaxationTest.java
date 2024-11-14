package com.carlosantunes;

import com.carlosantunes.restaurant.Table;
import com.carlosantunes.restaurant.enums.BoissonType;
import com.carlosantunes.restaurant.enums.PlatType;
import com.carlosantunes.restaurant.enums.TableType;
import com.carlosantunes.restaurant.pont.TaxationEntreprise;
import com.carlosantunes.restaurant.pont.TaxationPrive;
import com.carlosantunes.restaurant.produit.boisson.Boisson;
import com.carlosantunes.restaurant.produit.plat.Plat;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class TaxationTest {

    @Test
    public void testCalculerTaxePrive() {
        // Table privée avec taxation privée0
        Table tablePrivee = new Table("Client 1", new Date(), TableType.PLAISIR, new TaxationPrive());
        tablePrivee.ajouterProduit(new Plat("Steak", 12.00, PlatType.RICHE));
        tablePrivee.ajouterProduit(new Boisson("Bière", 2.50, BoissonType.ALCOOLISEE));

        double montant = 14.50; // Montant des produits consommés
        double taxeAttendue = montant * 1.15; // La taxe doit être 15% du montant

        Assert.assertEquals(taxeAttendue, tablePrivee.calculerTaxe(), 0.01);
    }

    @Test
    public void testCalculerTaxeEntreprise() {
        // Table d'entreprise avec taxation entreprise
        Table tableEntreprise = new Table("Client 2", new Date(), TableType.VEGAN, new TaxationEntreprise());
        tableEntreprise.ajouterProduit(new Plat("Salade", 8.00, PlatType.VEGAN));
        tableEntreprise.ajouterProduit(new Boisson("Eau", 1.00, BoissonType.GAZEUSE));

        double montant = 9.00; // Montant des produits consommés
        double taxeAttendue = montant * 1.05; // La taxe doit être 5% du montant

        Assert.assertEquals(taxeAttendue, tableEntreprise.calculerTaxe(), 0.01);
    }
}
