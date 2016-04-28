package br.usp.fllsouto.pokemonbattle.core;

import java.util.*;

public class Trainer {
    private String name;
    private HashMap<String, Pokemon> pokebolas;
    private Inventory inventory = new Inventory();
    private Pokemon mainPokemon;

    public Trainer(String name, HashMap<String, Pokemon> pokemonList, ArrayList<String> inventoryList) {
        this.name = name;
        this.pokebolas = pokemonList;
        changePokemon(getRandomPokebola());

        for (String item : inventoryList)
            this.inventory.addItem(item);
    }

    public Pokemon getPokemon(Object name) {
        return this.pokebolas.get(name);
    }

    public Boolean changePokemon(Object name) {
        Pokemon p = getPokemon(name);
        if (p == null) {
            return false;
        } else {
            this.mainPokemon = p;
            return true;
        }
    }

    public Boolean changePokemon() {
        if (this.getAlivePokemonQuantity() > 0) {
            Pokemon p = this.pokebolas.get(getRandomPokebola());
            while (p.isDead()) {
                System.out.println("Pokemon " + this.getName() + " is dead, changing Pokemon!");
                p = this.pokebolas.get(getRandomPokebola());
            }
            this.mainPokemon = p;
            return true;
        }
        return false;
    }

    public String getRandomPokebola() {
        List<String> pokemonList = new ArrayList(this.pokebolas.keySet());
        int indx = new Random().nextInt(pokemonList.size());
        return pokemonList.get(indx);
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