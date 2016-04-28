package br.usp.fllsouto.pokemonbattle.core;

import java.util.HashMap;

public class Trainer {
    private String name;
    private HashMap<String, Pokemon> pokebolas;
    private Inventory inventory = new Inventory();
    private Pokemon mainPokemon;

    public Trainer(String name, HashMap<String, Pokemon> pokemonList) {
        this.name = name;
        this.pokebolas = pokemonList;
        this.inventory.addItem("Potion");
    }

    public Pokemon getPokemon(String name) {
        return this.pokebolas.get(name);
    }

    public Boolean changePokemon(String name) {
        Pokemon p = getPokemon(name);
        if (p == null) {
            return false;
        } else {
            this.mainPokemon = p;
            return true;
        }
    }

    public Pokemon getMainPokemon() {
        return this.mainPokemon;
    }
    
}