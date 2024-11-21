package com.carlosantunes;

import com.carlosantunes.restaurant.Recette;
import com.carlosantunes.restaurant.Statistique;
import com.carlosantunes.restaurant.Table;
import com.carlosantunes.restaurant.TableFactory;
import com.carlosantunes.restaurant.builder.*;
import com.carlosantunes.restaurant.decorateur.ExtraDose;
import com.carlosantunes.restaurant.decorateur.ExtraTaste;
import com.carlosantunes.restaurant.enums.BoissonType;
import com.carlosantunes.restaurant.enums.MenuType;
import com.carlosantunes.restaurant.enums.PlatType;
import com.carlosantunes.restaurant.enums.TableType;
import com.carlosantunes.restaurant.fabrique.CreateurProduit;
import com.carlosantunes.restaurant.pont.TaxationEntreprise;
import com.carlosantunes.restaurant.pont.TaxationPrive;
import com.carlosantunes.restaurant.produit.Menu;
import com.carlosantunes.restaurant.produit.Produit;
import com.carlosantunes.restaurant.produit.boisson.Boisson;
import com.carlosantunes.restaurant.produit.plat.Plat;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * La classe Restaurant représente un restaurant qui gère une liste de produits (plats, boissons, menus).
 * Elle permet d'ajouter des produits au restaurant et d'afficher l'ensemble des produits proposés.
 */
public class Restaurant {

    // Patron composite
    private final List<Produit> produits;

    // Patron Factory et Abstract Factory
    private final List<Table> tables;

    /**
     * Constructeur de la classe Restaurant.
     * Initialise la liste des Produits et des tables du restaurant.
     */
    public Restaurant() {
        // produits
        this.produits = new ArrayList<>();
        // Tables
        this.tables = new ArrayList<>();
    }


    // ======================================== Tâche 1: Composite pattern ========================================

    /**
     * Ajoute un produit à la liste des produits du restaurant.
     *
     * @param produit Le produit à ajouter (peut être un Plat, une Boisson ou un Menu).
     */
    public void ajouterProduit(Produit produit) {
        produits.add(produit);
    }

    /**
     * Affiche l'ensemble des produits disponibles dans le restaurant (plats, boissons, menus).
     * Chaque produit appelle sa propre méthode d'affichage via le polymorphisme.
     */
    public void afficherProduits() {
        for (Produit produit : produits) {
            produit.afficher();
            System.out.println("----------------------------------------");
        }
    }

    /**
     * Utilisé pour les tests unitaires, retourne les produits du restaurant sous forme de liste.
     *
     * @return Liste des produits du restaurant.
     */
    public List<Produit> getProduits() {
        return produits;
    }


    // ======================================== Tâche 2: Abstract Factory pattern ========================================

    /**
     * Ajoute une table à la liste des tables du restaurant.
     *
     * @param table La table à ajouter
     */
    public void ajouterTable(Table table) {
        tables.add(table);
    }

    /**
     * Affiche l'ensemble des tables du restaurant.
     */
    public void afficherTables() {
        System.out.println("Tables du restaurant:");
        for (Table table : tables) {
            table.afficherProduitsConsommes();
            System.out.println("----------------------------------------");
        }
    }

    // ======================================== Tâche 3: Singleton pattern ========================================

    /**
     * Récupère les informations de la table pour les ajouter à la recette.
     * Si aucun produit n'a été consommé, une exception est lancée.
     *
     * @param table La table à clôturer
     */
    public void cloturerTable(Table table) {
        try {
            if (table.getProduitsConsommes().isEmpty()) {
                throw new IllegalArgumentException("Impossible de clôturer la table de (" + table.getClient() + ") aucun produit consommé !");
            }
            Recette.getInstance().setTableRecette(table);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Affiche les statistiques des tables clôturées.
     */
    public void afficherRecette() {
        Recette.getInstance().afficherStatistiques();
    }


    /**
     * Méthode principale du programme.
     * Crée un restaurant et effectue les tâches demandées.
     */
    public static void main(String[] args) {
        System.out.println("Bienvenue au Restaurant!");
        System.out.println("----------------------------------------");

        // Création du restaurant
        Restaurant restaurant = new Restaurant();


        // Lab 1 - Tache 1: Composite pattern
        // tache1(restaurant);

        // Lab 1 - Tache 2: Abstract Factory pattern
        // tache2(restaurant);

        // Lab 2 - Tache 1 : Singleton pattern
        // tache3(restaurant);

        // Lab 2 - Tache 2 : State pattern
        // tache4(restaurant);

        // Lab 3 - Tache 1 : Decorator pattern
        // tache5(restaurant);

        // Lab 3 - Tache 2 : Builder pattern
        // tache6(restaurant);

        // Lab 4 - Tache 1 : Bridge pattern
        //tache7(restaurant);

        // Lab 4 - Tache 2 : Observer pattern
        // tache8(restaurant);

        // Lab 5 - Tache 1 : Iterator pattern
        // tache9(restaurant);

        // Lab 5 - Tache 2 : Visitor pattern
        tache10(restaurant);
    }

    /**
     * Lab 5 - Tâche 2 : Visitor pattern
     * 1. Création de menus Diet, Plaisir et Vegan selon le Builder pattern Normal et Copieux
     * 2. Ajout des menus au restaurant
     * 3. Création de tables privées et d'entreprise
     * 4. Ajout des menus aux tables
     * 5. Changement d'état des tables : Réservée, Servie, Clôturée
     * 6. Utilisation du pattern Visitor pour afficher les statistiques des produits consommés
     * 7. Exportation des statistiques des produits consommés dans un fichier CSV
     */
    private static void tache10(Restaurant restaurant) {

        System.out.println("----------------------------------------");
        System.out.println("Tâche 10: Visitor pattern :");
        System.out.println("----------------------------------------");

        Statistique stats = Statistique.getInstance();

        ajouterMenusAuRestaurant(restaurant);

        ajouterTablesAuRestaurant(restaurant);

        gererTables(restaurant);

        stats.addProduit(new Plat("Steak saignant", 12.00, PlatType.RICHE));
        stats.addProduit(new Boisson("Bière Blonde", 2.50, BoissonType.ALCOOLISEE));

        // Ajoutez des produits aux statistiques
        restaurant.tables.forEach(table -> table.getProduitsConsommes().forEach(stats::addProduit));

        System.out.println("Nombre de produits consommés : " + stats.getNombreProduitsConsommes());
        System.out.println("Montant total des produits consommés : " + stats.getMontantTotal());

        // Exportation des statistiques des produits consommés dans un fichier CSV
        stats.exporterProduits("statistiques.csv");

    }

    /*
     * Lab 5 - Tâche 1 : Iterator pattern
     *
     * 1. Création de menus Diet, Plaisir et Vegan selon le Builder pattern Normal et Copieux
     * 2. Ajout des menus au restaurant
     * 3. Création de tables privées et d'entreprise
     * 4. Ajout des menus aux tables
     * 5. Changement d'état des tables : Réservée, Servie, Clôturée
     * 6. Utilisation de l'itérateur pour afficher les tables clôturées de plus de 50 CHF
     * 7. Utilisation de l'itérateur pour afficher les tables clôturées pour un mois donné
     * 8. Utilisation de l'itérateur pour afficher les tables clôturées avec un montant supérieur à 20 CHF
     */
    private static void tache9(Restaurant restaurant) {

        System.out.println("----------------------------------------");
        System.out.println("Tâche 9: Iterator pattern :");
        System.out.println("----------------------------------------");


        /// Créer et ajouter des menus
        ajouterMenusAuRestaurant(restaurant);

        // Créer et ajouter des tables
        ajouterTablesAuRestaurant(restaurant);

        // Servir et fermer les tables
        gererTables(restaurant);

        // Afficher la recette totale du restaurant
        restaurant.afficherRecette();

        // Utiliser l'itérateur pour afficher les tables clôturées
        Iterator<Table> iteratorOver50 = Recette.getInstance().recetteIteratorMontantSuperieurA50();
        Iterator<Table> iteratorByMonth = Recette.getInstance().recetteIterateurParMois(10);
        Iterator<Table> iteratorMontantSuperiorMontant = Recette.getInstance().recetteIterateurMontantSuperieurMontant(20.0);

        System.out.println("===== TABLES CLÔTURÉES DE PLUS DE 50 CHF =====");
        while (iteratorOver50.hasNext()) {
            Table table = iteratorOver50.next();
            System.out.println("Table de plus de 50 CHF : " + table.getClient() + " - " + table.getMontant() + " CHF");
        }

        System.out.println("===== TABLES CLÔTURÉES AU MOIS xxx =====");
        while (iteratorByMonth.hasNext()) {
            Table table = iteratorByMonth.next();
            System.out.println("Table clôturée le : " + table.getLocalDate() + " - " + table.getClient() + " - " + table.getMontant() + " CHF");
        }

        System.out.println("===== TABLES CLÔTURÉES AVEC UN MONANT SUPÉRIEUR À 20 CHF =====");
        while (iteratorMontantSuperiorMontant.hasNext()) {
            Table table = iteratorMontantSuperiorMontant.next();
            System.out.println("Table de plus de 20 CHF : " + table.getClient() + " - " + table.getMontant() + " CHF");
        }
    }

    /*
        Lab 4 - Tâche 2 : Observer pattern

            1. Création de tables privées et d'entreprise
            2. Ajout de produits aux tables
            3. Changement d'état des tables :
                1. Réservée
                2. Servie
                3. Clôturée
            3. Si la table est clôturée, affichage de la facture
            4. Affichage de la recette totale du restaurant
    */
    private static void tache8(Restaurant restaurant) {

        System.out.println("----------------------------------------");
        System.out.println("Tâche 8: Observer pattern :");
        System.out.println("----------------------------------------");

        // Utilisation de la fabrique pour choisir le type de table
        CreateurProduit createurType = TableFactory.createTable(TableType.PLAISIR);

        // Création de 2 produits à partir du type de table
        Plat plat = createurType.creerPlat("Steak tartar", 20.80);
        Boisson boisson = createurType.creerBoisson("Vin rouge", 32.25);

        // Création de la table avec l'état "Réservée"
        Table table = new Table("Carlos", new Date(), TableType.PLAISIR, new TaxationPrive());
        restaurant.ajouterTable(table); // Ajout de la table au restaurant


        table.getEtatDeLaTable().accueillirClient(table); // Passe à l'état "Servie"

        // Ajout des produits à la table
        table.ajouterProduit(plat);
        table.ajouterProduit(boisson);

        table.getEtatDeLaTable().servirProduits(table); // Affichage des produits servis
        table.getEtatDeLaTable().fermer(table); // Passe à l'état "Cloturer"

        // Creation d'une deuxième table
        CreateurProduit createurType2 = TableFactory.createTable(TableType.VEGAN);
        Table table2 = new Table("Alice", new Date(), TableType.VEGAN, new TaxationPrive());
        restaurant.ajouterTable(table2); // Ajout de la table au restaurant
        Plat plat2 = createurType2.creerPlat("Salade verte", 5.50);
        Boisson boisson2 = createurType2.creerBoisson("Eau", 1.00);
        table2.ajouterProduit(plat2);
        table2.ajouterProduit(boisson2);

        table2.getEtatDeLaTable().accueillirClient(table2); // Passe à l'état "Servie"
        table2.getEtatDeLaTable().servirProduits(table2); // Affichage des produits servis
        table2.getEtatDeLaTable().fermer(table2); // Passe à l'état "Cloturer"

        System.out.println("===== RECETTE DU RESTAURANT =====");
        // Affichage la recette totale du restaurant
        restaurant.afficherRecette();
        System.out.println("=================================");

    }

    /*
        Lab 4 - Tâche 1 : Bridge pattern

            1. Création de tables privées et d'entreprise
            2. Ajout de produits aux tables
            3. Calcul des taxes sur les tables
    */
    public static void tache7(Restaurant restaurant) {

        System.out.println("----------------------------------------");
        System.out.println("Tâche 7: Bridge pattern :");
        System.out.println("----------------------------------------");


        // Table privée avec taxation privée
        Table tablePrive = new Table("Client 1", new Date(), TableType.PLAISIR, new TaxationPrive());
        tablePrive.ajouterProduit(new Plat("Steak", 12.00, PlatType.RICHE));
        tablePrive.ajouterProduit(new Boisson("Bière", 2.50, BoissonType.ALCOOLISEE));

        // Table entreprise avec taxation entreprise
        Table tableEntreprise = new Table("Client 2", new Date(), TableType.PLAISIR, new TaxationEntreprise());
        tableEntreprise.ajouterProduit(new Plat("Steak", 12.00, PlatType.RICHE));
        tableEntreprise.ajouterProduit(new Boisson("Bière", 2.50, BoissonType.ALCOOLISEE));

        // Affichage des tables montants HT
        System.out.println("Table privée HT : " + tablePrive.getMontant());
        System.out.println("Table entreprise HT : " + tableEntreprise.getMontant());

        // Calcul des taxes
        double taxePrivee = tablePrive.calculerTaxe();
        double taxeEntreprise = tableEntreprise.calculerTaxe();

        // Affichage des tables montant TTC
        System.out.println("Taxe sur la table privée: " + taxePrivee);
        System.out.println("Taxe sur la table entreprise: " + taxeEntreprise);

    }

    /*
        Lab 3 - Tâche 2 : Builder pattern

            1. Création de menus Diet, Plaisir et Vegan selon le Builder pattern Normal et Copieux
            2. Affichage des menus
    */
    private static void tache6(Restaurant restaurant) {
        System.out.println("----------------------------------------");
        System.out.println("Tâche 6: Builder pattern :");
        System.out.println("----------------------------------------");

        // Menu Normal Diet
        Builder builderDiet = new ConcretMenuDiet("Menu Diet");
        DirecteurNormal directeurNormal = new DirecteurNormal(builderDiet);
        Menu menuDiet = directeurNormal.construireMenu();
        menuDiet.afficher();

        // Menu Copieux Plaisir
        Builder builderPlaisir = new ConcretMenuPlaisir("Menu Plaisir et Copieux");
        DirecteurCopieux directeurCopieux = new DirecteurCopieux(builderPlaisir);
        Menu menuPlaisir = directeurCopieux.construireMenu();
        menuPlaisir.afficher();

        // Menu Normal Vegan
        Builder builderVegan = new ConcretMenuVegan("Menu Vegan");
        DirecteurNormal directeurNormalVegan = new DirecteurNormal(builderVegan);
        Menu menuVegan = directeurNormalVegan.construireMenu();
        menuVegan.afficher();

    }

    /*
        Lab 3 - Tâche 1 : Decorator pattern

            1. Création de plats et boissons
            2. Ajout de décorations aux plats et boissons
            3. Création d'un menu
            4. Affichage des produits
    */
    private static void tache5(Restaurant restaurant) {

        System.out.println("----------------------------------------");
        System.out.println("Tâche 5: Decorator pattern :");
        System.out.println("----------------------------------------");

        Produit plat = new Plat("Steak", 12.00, PlatType.RICHE);
        plat = new ExtraDose(plat);
        //plat.afficher();

        Produit boisson = new Boisson("Bière", 2.50, BoissonType.ALCOOLISEE);
        boisson = new ExtraTaste(boisson);
        //boisson.afficher();

        Produit menu = new Menu("Menu du jour", MenuType.PLAISIR);
        menu.ajouterProduit(plat);
        menu.ajouterProduit(boisson);
        menu.afficher();

        menu = new ExtraDose(menu);
        // menu.afficher();
        menu = new ExtraTaste(menu);
        System.out.println("----------------------------------- MENU FINAL -----------------------------------");
        menu.afficher();

        System.out.println("----------- Prix du Menu avec les décorateurs ExtraDose et ExtraTaste ------------");
        System.out.println(menu.getNom() + " : " + menu.getPrix() + " CHF");
    }

    /*
        Lab 2 - Tâche 2 : State pattern

            1. Création de tables pour le restaurant
            2. Ajout de produits aux tables
            3. Affichage de l'état des tables
    */
    private static void tache4(Restaurant restaurant) {

        System.out.println("----------------------------------------");
        System.out.println("Tâche 4: State pattern :");
        System.out.println("----------------------------------------");


        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le nom du client :");
        String client = scanner.nextLine();

        // Demande du type de table au client et vérification de la validité du type
        TableType typeTable = null;
        while (typeTable == null) {
            try {
                System.out.println("Entrez le type de table (PLAISIR, DIET, VEGAN):");
                typeTable = TableType.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Type de table invalide. Veuillez entrer PLAISIR, DIET ou VEGAN.");
            }
        }

        // Utilisation de la fabrique pour choisir le type de table
        CreateurProduit createurType = TableFactory.createTable(typeTable);

        // Création de la table avec l'état "Réservée"
        Table table = new Table(client, new Date(), typeTable, new TaxationPrive());

        // Création de 2 produits à partir du type de table
        Plat plat = createurType.creerPlat("Palat de test", 10.80);
        Boisson boisson = createurType.creerBoisson("Boisson de test", 8.55);

        // Ajout des produits à la table
        table.ajouterProduit(plat);
        table.ajouterProduit(boisson);

        restaurant.ajouterTable(table); // Ajout de la table au restaurant


        table.getEtatDeLaTable().afficher(table); // Affichage de l'état de la table
        table.getEtatDeLaTable().accueillirClient(table); // Passe à l'état "Servie"

        table.getEtatDeLaTable().afficher(table); // Le client est en train de consommer
        table.getEtatDeLaTable().servirProduits(table); // Affichage des produits servis

        table.ajouterProduit(new Plat("Salade", 5.50, PlatType.VEGAN));
        table.ajouterProduit(new Boisson("Eau", 1.00, BoissonType.GAZEUSE));

        table.getEtatDeLaTable().afficher(table); // Le client est en train de consommer
        table.getEtatDeLaTable().fermer(table); // Passe à l'état "Cloturer"
        table.getEtatDeLaTable().afficher(table); // Affichage de la facture

        System.out.println("===== RECETTE DU RESTAURANT =====");
        // Affichage la recette totale du restaurant
        restaurant.afficherRecette();
        System.out.println("=================================");

    }

    /*
        Lab 2 - Tâche 1 : Singleton pattern

            1. Création de tables pour le restaurant
            2. Ajout de produits aux tables
            3. Clôture des tables
            4. Affichage des statistiques
     */
    private static void tache3(Restaurant restaurant) {


        System.out.println("----------------------------------------");
        System.out.println("Tâche 3: Singleton pattern :");
        System.out.println("----------------------------------------");


        Table table1 = new Table("Alice", new Date(), TableType.VEGAN, new TaxationPrive());
        table1.ajouterProduit(new Plat("Salade", 5.50, PlatType.VEGAN));
        table1.ajouterProduit(new Boisson("Eau", 1.00, BoissonType.GAZEUSE));


        restaurant.ajouterTable(table1);
        restaurant.cloturerTable(table1);


        Table table2 = new Table("Bob", new Date(), TableType.PLAISIR, new TaxationEntreprise());
        table2.ajouterProduit(new Plat("Steak", 12.00, PlatType.RICHE));
        table2.ajouterProduit(new Boisson("Bière", 2.50, BoissonType.ALCOOLISEE));
        restaurant.ajouterTable(table2);
        restaurant.cloturerTable(table2);


        restaurant.afficherRecette();

    }

    /*
        Lab 1 - Tâche 1: Composite pattern
     */
    private static void tache1(Restaurant restaurant) {


        System.out.println("----------------------------------------");
        System.out.println("Tâche 1: Composite pattern :");
        System.out.println("----------------------------------------");


        // Création de plats
        Plat entree = new Plat("Salade", 5.50, PlatType.VEGAN);
        Plat principal = new Plat("Steak", 12.00, PlatType.RICHE);
        Plat dessert = new Plat("Tarte aux pommes", 4.00, PlatType.VEGAN);

        // Création de boissons
        Boisson soda = new Boisson("Coca", 2.50, BoissonType.GAZEUSE);
        Boisson cafe = new Boisson("Café", 1.50, BoissonType.LIGHT);

        // Création d'un menu
        Menu menuMidi = new Menu("Menu du jour", MenuType.PLAISIR);
        menuMidi.ajouterProduit(entree);
        menuMidi.ajouterProduit(principal);
        menuMidi.ajouterProduit(dessert);
        menuMidi.ajouterProduit(soda);
        menuMidi.ajouterProduit(cafe);

        // Ajout du menu au restaurant
        restaurant.ajouterProduit(menuMidi);

        // Affichage des produits du restaurant
        restaurant.afficherProduits();
    }


    /*
       Lab 1 - Tâche 2: Factory et Abstract Factory pattern
     */
    private static void tache2(Restaurant restaurant) {

        System.out.println("----------------------------------------");
        System.out.println("Tâche 2: Abstract Factory pattern :");
        System.out.println("----------------------------------------");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le nom du client:");
        String client = scanner.nextLine();

        // Demande du type de table au client et vérification de la validité du type
        TableType typeTable = null;
        while (typeTable == null) {
            try {
                System.out.println("Entrez le type de table (PLAISIR, DIET, VEGAN):");
                typeTable = TableType.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Type de table invalide. Veuillez entrer PLAISIR, DIET ou VEGAN.");
            }
        }

        // Utilisation de la fabrique pour choisir le type de table
        CreateurProduit createurType = TableFactory.createTable(typeTable);

        // Création de la table
        Table table = new Table(client, new Date(), typeTable, new TaxationPrive());

        // Demande des détails du plat
        String[] detailsPlat = commanderProduit("plat");
        String nomPlat = detailsPlat[0];
        double prixPlat = Double.parseDouble(detailsPlat[1]);

        // Demande des détails de la boisson
        String[] detailsBoisson = commanderProduit("boisson");
        String nomBoisson = detailsBoisson[0];
        double prixBoisson = Double.parseDouble(detailsBoisson[1]);

        // Création des produits à partir des entrées utilisateur et du type de table
        Plat plat = createurType.creerPlat(nomPlat, prixPlat);
        Boisson boisson = createurType.creerBoisson(nomBoisson, prixBoisson);

        // Ajout des produits à la table
        table.ajouterProduit(plat);
        table.ajouterProduit(boisson);

        // Ajout de la table au restaurant et affichage
        restaurant.ajouterTable(table);
        restaurant.afficherTables();
    }


    /**
     * Demande à l'utilisateur le nom et le prix d'un produit.
     *
     * @param typeProduit Le type de produit (ex: "plat", "boisson")
     * @return Un tableau de String contenant le nom et le prix du produit.
     */
    private static String[] commanderProduit(String typeProduit) {
        Scanner scanner = new Scanner(System.in);

        // Demande du nom du produit
        System.out.println("Entrez le nom du " + typeProduit + " commandé:");
        String nom = "";
        while (nom.isEmpty()) {
            nom = scanner.nextLine();
        }

        // Demande du prix du produit
        System.out.println("Entrez le prix du " + typeProduit + " commandé:");
        String prixString = "";
        while (prixString.isEmpty() || !prixString.matches("^[0-9]+$")) {
            prixString = scanner.nextLine();
        }

        // Remplacer les virgules par des points pour les nombres décimaux
        prixString = prixString.replace(',', '.');

        return new String[]{nom, prixString};
    }

    private static void ajouterMenusAuRestaurant(Restaurant restaurant) {
        List<Produit> menus = List.of(
                new DirecteurNormal(new ConcretMenuDiet("Menu Diet")).construireMenu(),
                new DirecteurCopieux(new ConcretMenuPlaisir("Menu Plaisir")).construireMenu(),
                new DirecteurCopieux(new ConcretMenuPlaisir("Menu Copieux")).construireMenu()
        );

        for (Produit menu : menus) {
            restaurant.ajouterProduit(menu);
        }
    }

    private static void ajouterTablesAuRestaurant(Restaurant restaurant) {
        restaurant.tables.clear();
        List<Table> tables = List.of(
                new Table("Sophie", new Date(), TableType.VEGAN, new TaxationPrive()),
                new Table("Bob", new Date(), TableType.PLAISIR, new TaxationEntreprise()),
                new Table("Charlie", Date.from(LocalDate.of(2024, 10, 16)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant()), TableType.PLAISIR, new TaxationEntreprise())
        );

        for (Table table : tables) {
            restaurant.ajouterTable(table);
        }
    }

    private static void gererTables(Restaurant restaurant) {
        for (Table table : restaurant.tables) {
            table.ajouterProduit(restaurant.produits.get(restaurant.tables.indexOf(table)));
            table.getEtatDeLaTable().accueillirClient(table);
            table.getEtatDeLaTable().servirProduits(table);
            table.getEtatDeLaTable().fermer(table);
        }
    }

}
