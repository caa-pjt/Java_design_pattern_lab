package com.carlosantunes;

import com.carlosantunes.restaurant.Statistique;
import com.carlosantunes.restaurant.enums.BoissonType;
import com.carlosantunes.restaurant.produit.boisson.Boisson;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ExporterBoissonCommandeTest {

    @Test
    void testExecute() throws Exception {
        // Arrange
        Statistique statistique = Statistique.getInstance();
        Boisson boisson = new Boisson("Coca-Cola", 3.5, BoissonType.LIGHT);
        String filePath = "test_statistique_export.txt";

        // Action (execute)
        statistique.addProduit(boisson);
        statistique.exporterProduits(filePath);

        // Assert
        Path path = Path.of(filePath);
        assertTrue(Files.exists(path), "Le fichier n'a pas été créé.");
        String fileContent = Files.readString(path);
        assertTrue(fileContent.contains("Coca-Cola"), "Le fichier ne contient pas le nom de la boisson.");
        assertTrue(fileContent.contains("3.5"), "Le fichier ne contient pas le prix de la boisson.");

        // Cleanup
        Files.deleteIfExists(path);
    }
}
