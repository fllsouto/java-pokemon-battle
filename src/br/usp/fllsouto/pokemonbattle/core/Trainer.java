package br.usp.fllsouto.pokemonbattle.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Trainer {
    private String name;
    private HashMap<String, Pokemon> pokebolas;
    private Inventory inventory = new Inventory();
    private Pokemon mainPokemon;

    public Trainer(String name, HashMap<String, Pokemon> pokemonList, ArrayList<String> inventoryList) {
        this.name = name;
        this.pokebolas = pokemonList;

        for (String item : inventoryList)
            this.inventory.addItem(item);
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

    public String  getName() {
        return this.name;
    }

    public int getAlivePokemonQuantity() {
        Collection<Pokemon> pokemonCollection = this.pokebolas.values();
        int alivePokemons = 0;
        for (Pokemon pokemon : pokemonCollection) {
            if (!pokemon.isDead()) {
                alivePokemons += 1;
            }
        }

        return alivePokemons;
    }


    public Item getItemFromInventory(String itemName) {
        return this.inventory.getItem(itemName);
    }
}