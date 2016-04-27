package br.usp.fllsouto.pokemonbattle.core;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by fsouto on 27/04/16.
 */
public class Inventory {
    private ArrayList<Potion> potionBag = new ArrayList();

    public boolean addItem(String item) {
        Potion newItem = new Potion(item, Potion.getValue(item));
        if (newItem != null) {
            potionBag.add(newItem);
            return true;
        } else {
            System.out.println("Undefined Item");
            return false;
        }
    }

    public Item getItem(String item) {
        if (this.potionBag.isEmpty()) {
            System.out.println("Empty bag");
            return null;
        } else {
            return potionBag.remove(0);
        }
    }
}
