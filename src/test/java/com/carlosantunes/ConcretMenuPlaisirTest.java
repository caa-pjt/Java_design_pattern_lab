package com.carlosantunes;

import com.carlosantunes.restaurant.builder.Builder;
import com.carlosantunes.restaurant.builder.ConcretMenuPlaisir;
import com.carlosantunes.restaurant.builder.DirecteurCopieux;
import com.carlosantunes.restaurant.produit.Menu;
import com.carlosantunes.restaurant.produit.boisson.BoissonAlcoolisee;
import com.carlosantunes.restaurant.produit.plat.PlatRiche;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConcretMenuPlaisirTest {

    private Builder builderPlaisir;

    @BeforeEach
    void setUp() {
        this.builderPlaisir = new ConcretMenuPlaisir("Menu Plaisir");
    }


    @Test
    void testConstruireMenuPlaisir() {
        // Construire le menu Plaisir
        DirecteurCopieux directeurCopieux = new DirecteurCopieux(builderPlaisir);
        Menu menuPlaisir = directeurCopieux.construireMenu();

        // Vérifier que le menu a bien été construit
        Assertions.assertNotNull(menuPlaisir);
        Assertions.assertEquals("Menu Plaisir", menuPlaisir.getNom());
        Assertions.assertFalse(menuPlaisir.getProduits().isEmpty());

        // Vérifier les types de produits
        Assertions.assertTrue(menuPlaisir.getProduits().stream().anyMatch(p -> p instanceof PlatRiche));
        Assertions.assertTrue(menuPlaisir.getProduits().stream().anyMatch(p -> p instanceof BoissonAlcoolisee));
    }
}
