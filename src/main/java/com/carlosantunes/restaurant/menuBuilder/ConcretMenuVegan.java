package com.carlosantunes.restaurant.menuBuilder;

import com.carlosantunes.restaurant.TableFactory;
import com.carlosantunes.restaurant.enums.MenuType;
import com.carlosantunes.restaurant.enums.TableType;
import com.carlosantunes.restaurant.fabrique.CreateurProduit;
import com.carlosantunes.restaurant.produit.Menu;
import com.carlosantunes.restaurant.produit.Produit;

public class ConcretMenuVegan implements Builder {

    private final Menu menu;
    private final CreateurProduit createurType;

    public ConcretMenuVegan(String nom) {
        this.menu = new Menu(nom, MenuType.VEGAN);
        this.createurType = TableFactory.createTable(TableType.VEGAN);
    }

    @Override
    public void construireEntree() {
        Produit entree = createurType.creerPlat("Salade", 5.0);
        menu.ajouterProduit(entree);
    }

    @Override
    public void construirePlat() {
        Produit plat = createurType.creerPlat("Pizza vegan", 18.0);
        menu.ajouterProduit(plat);
    }

    @Override
    public void construireDessert() {
        Produit dessert = createurType.creerPlat("Fruit", 3.0);
        menu.ajouterProduit(dessert);
    }

    @Override
    public void construireBoisson() {
        Produit boisson = createurType.creerBoisson("Eau minérale Gazeuse", 2.80);
        menu.ajouterProduit(boisson);
    }

    @Override
    public Menu getMenu() {
        return menu;
    }


}
