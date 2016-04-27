package br.usp.fllsouto.pokemonbattle.core;

import java.util.HashMap;

public class Trainer {
    private String name;
    private HashMap<String, Pokemon> pokebolas;
    private Inventory inventory = new Inventory();

    public Trainer(String name, HashMap<String, Pokemon> pokemonList) {
        this.name = name;
        this.pokebolas = pokemonList;
        this.inventory.addItem("Potion");
    }
}