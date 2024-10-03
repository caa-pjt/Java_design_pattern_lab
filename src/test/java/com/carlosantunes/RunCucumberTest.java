package com.carlosantunes;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
 * Classe de configuration pour exécuter les scénarios Cucumber.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources", // Chemin vers les fichiers .feature
        glue = "com.carlosantunes" // Package où se trouvent les définitions des étapes
)
public class RunCucumberTest {
}