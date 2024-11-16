package com.carlosantunes.restaurant.builder;

import com.carlosantunes.restaurant.enums.MenuType;

import java.util.*;

/**
 * Classe ProduitsParType qui permet de stocker les produits par type de menu.
 */
public class ProduitsParType {

    private static final Map<MenuType, List<String>> entreesParType = new EnumMap<>(MenuType.class);
    private static final Map<MenuType, List<String>> platsParType = new EnumMap<>(MenuType.class);
    private static final Map<MenuType, List<String>> dessertsParType = new EnumMap<>(MenuType.class);
    private static final Map<MenuType, List<String>> boissonsParType = new EnumMap<>(MenuType.class);

    private ProduitsParType() {
        throw new IllegalStateException("Utility class");
    }

    static {
        // Initialisation des entrées par type de menu
        entreesParType.put(MenuType.PLAISIR, Arrays.asList(
                "Salade César",
                "Bruschetta",
                "Assiette de Charcuterie"
        ));

        entreesParType.put(MenuType.DIET, Arrays.asList(
                "Salade Verte",
                "Soupe de Légumes",
                "Salade de Quinoa"
        ));

        entreesParType.put(MenuType.VEGAN, Arrays.asList(
                "Houmous",
                "Salade de Lentilles",
                "Taboulé"
        ));

        // Initialisation des plats par type de menu
        platsParType.put(MenuType.PLAISIR, Arrays.asList(
                "Burger Deluxe",
                "Pizza Fromage",
                "Pâtes Carbonara"
        ));

        platsParType.put(MenuType.DIET, Arrays.asList(
                "Poulet Grillé",
                "Poisson Vapeur",
                "Pâtes Complètes"
        ));

        platsParType.put(MenuType.VEGAN, Arrays.asList(
                "Tofu Sauté",
                "Légumes Grillés",
                "Ratatouille"
        ));

        // Initialisation des desserts par type de menu
        dessertsParType.put(MenuType.PLAISIR, Arrays.asList(
                "Tiramisu",
                "Crème Brûlée",
                "Mousse au Chocolat"
        ));

        dessertsParType.put(MenuType.DIET, Arrays.asList(
                "Yaourt Nature",
                "Fruit Frais",
                "Compote de Pommes"
        ));

        dessertsParType.put(MenuType.VEGAN, Arrays.asList(
                "Salade de Fruits",
                "Brownie Vegan",
                "Glace Végétale"
        ));

        // Initialisation des boissons par type de menu
        boissonsParType.put(MenuType.PLAISIR, Arrays.asList(
                "Vin Rouge",
                "Bière Blonde",
                "Cocktail"
        ));

        boissonsParType.put(MenuType.DIET, Arrays.asList(
                "Eau Minérale Light",
                "Thé Vert Détox",
                "Soda Light"
        ));

        boissonsParType.put(MenuType.VEGAN, Arrays.asList(
                "Eau Gazeuse",
                "Limonade Naturelle",
                "Soda Bio"
        ));
    }

    // Méthodes pour obtenir les produits par type de menu
    public static List<String> obtenirEntreesParType(MenuType menuType) {
        return entreesParType.getOrDefault(menuType, Collections.emptyList());
    }

    public static List<String> obtenirPlatsParType(MenuType menuType) {
        return platsParType.getOrDefault(menuType, Collections.emptyList());
    }

    public static List<String> obtenirDessertsParType(MenuType menuType) {
        return dessertsParType.getOrDefault(menuType, Collections.emptyList());
    }

    public static List<String> obtenirBoissonsParType(MenuType menuType) {
        return boissonsParType.getOrDefault(menuType, Collections.emptyList());
    }
}

