package br.usp.fllsouto.pokemonbattle.core;

import java.util.HashMap;

/**
 * Created by fsouto on 27/04/16.
 */
public class Potion extends Item {

    private static HashMap<String, Double> potionList;
    static {
        potionList = new HashMap<String, Double>();
        potionList.put("Potion", 30.0);
    }
    public Potion(String name, Double value) {
        this.name = name;
        this.value = value;
    }
    @Override
    public Double use() {
        return value;
    }

    public static Double getValue(String key) {
        return potionList.get(key);
    }

    public String getName() {
        return this.name;
    }

    public Double getValue() {
        return this.value;
    }
}
