package com.carlosantunes;

import com.carlosantunes.restaurant.menuBuilder.Builder;
import com.carlosantunes.restaurant.menuBuilder.ConcretMenuPlaisir;
import com.carlosantunes.restaurant.menuBuilder.DirecteurCopieux;
import com.carlosantunes.restaurant.produit.Menu;
import com.carlosantunes.restaurant.produit.boisson.BoissonAlcoolisee;
import com.carlosantunes.restaurant.produit.plat.PlatRiche;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class ConcretMenuPlaisirTest {

    private Builder builderPlaisir;

    @BeforeEach
    void setUp() {
        this.builderPlaisir = new ConcretMenuPlaisir("Menu Plaisir");
    }


    @Test
    public void testConstruireMenuPlaisir() {
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
