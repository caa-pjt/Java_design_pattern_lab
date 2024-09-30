# Java_design_pattern_lab


## Description
Formation sur les design patterns en Java.

## Prérequis
- Java 21
- Maven 3.9.9
- Git 2.46.1
- IntelliJ IDEA
- [PlantUML](https://plantuml.com/)

## Installation
1. Cloner le projet :

```bash
git clone https://github.com/caa-pjt/Java_design_pattern_lab.git
```

2. Importer le projet dans IntelliJ IDEA :
3. Installer les dépendances Maven :

```bash
mvn clean install
```

## Utilisation
1. Commande pour compiler et exécuter le programme :

```bash
mvn clean compile assembly:single
java -jar target/Java_design_pattern_lab-1.0-SNAPSHOT-jar-with-dependencies.jar
```

Autres commandes Maven pour exécuter le programme :

## Maven
Utiliser Maven pour gérer les dépendances et les tâches de construction.

| Nom      | Utilité                                                                                              |
| -------- | ---------------------------------------------------------------------------------------------------- |
| clean    | Nettoyer le répertoire /target                                                                       |
| validate | Valider le pom.xml                                                                                   |
| compile  | Compiler les fichiers .java en .class dans le dossier /target                                        |
| test     | Lancer les tests unitaires dans /src/test/java                                                       |
| package  | Créer un package (un fichier « .jar », « .war », « .ear »)                                           |
| verify   | Lancer des tests pour vérifier la validité du package                                                |
| install  | Déployer le package vers le repository local afin qu'il puisse être accessible dans d'autres projets |
| deploy   | Déployer le package vers un repository distant                                                       |

## Auteur
- [Carlos Antunes](https://carlosantunes.ch/)
