package com.carlosantunes;

import com.carlosantunes.restaurant.decorateur.ExtraDose;
import com.carlosantunes.restaurant.decorateur.ExtraTaste;
import com.carlosantunes.restaurant.enums.PlatType;
import com.carlosantunes.restaurant.produit.Produit;
import com.carlosantunes.restaurant.produit.plat.Plat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class ProduitDecorateurTest {

    @Test
    void testGetPrixExtraTaste() {
        Produit produit = new Plat("Pates", 10.0, PlatType.VEGAN);
        double prixbase = produit.getPrix();
        produit = new ExtraTaste(produit);

        Assertions.assertEquals(prixbase * 1.15, produit.getPrix(), 0.0);
    }

    @Test
    void testGetPrixExtrDose() {
        // Tester avec un prix qui nécessite un arrondi
        Produit plat = new Plat("Salade", 6.75, PlatType.VEGAN);
        double prixBase = BigDecimal.valueOf(plat.getPrix() * 1.10).setScale(2, RoundingMode.HALF_UP).doubleValue();
        plat = new ExtraDose(plat);

        Assertions.assertEquals(prixBase, plat.getPrix(), 0.0);
    }
}
