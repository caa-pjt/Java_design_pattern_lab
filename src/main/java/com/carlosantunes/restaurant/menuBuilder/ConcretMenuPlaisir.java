package com.carlosantunes.restaurant.menuBuilder;

import com.carlosantunes.restaurant.TableFactory;
import com.carlosantunes.restaurant.enums.MenuType;
import com.carlosantunes.restaurant.enums.TableType;
import com.carlosantunes.restaurant.fabrique.CreateurProduit;
import com.carlosantunes.restaurant.produit.Menu;
import com.carlosantunes.restaurant.produit.Produit;

public class ConcretMenuPlaisir implements Builder {

    private final Menu menu;
    private final CreateurProduit createurType;

    public ConcretMenuPlaisir(String nom) {
        this.menu = new Menu(nom, MenuType.PLAISIR);
        this.createurType = TableFactory.createTable(TableType.PLAISIR);
    }

    @Override
    public void construireEntree() {
        Produit entree = createurType.creerPlat("Charcuterie", 8.0);
        menu.ajouterProduit(entree);
    }

    @Override
    public void construirePlat() {
        Produit plat = createurType.creerPlat("Steak", 15.0);
        menu.ajouterProduit(plat);
    }

    @Override
    public void construireDessert() {
        Produit dessert = createurType.creerPlat("Gâteau", 5.0);
        menu.ajouterProduit(dessert);
    }

    @Override
    public void construireBoisson() {
        Produit boisson = createurType.creerBoisson("Vin Rouge", 6.0);
        menu.ajouterProduit(boisson);
    }

    @Override
    public Menu getMenu() {
        return menu;
    }
}
