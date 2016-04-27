package br.usp.fllsouto.pokemonbattle.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by fsouto on 27/04/16.
 */
public class Pokemon {
    private String name;
    private Double hp;
    private HashMap<String, Attack> pokemonAttacks = new HashMap();

    public Pokemon(String name, double hp, HashMap<String, Double> attacks) {
        this.name = name;
        this.hp = hp;
        Iterator it = attacks.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            pokemonAttacks.put((String) pair.getKey(), new Attack((String) pair.getKey(), (Double) pair.getValue()));
        }
    }

    public String getName() {
        return this.name;
    }

    public Double getHp() {
        return this.hp;
    }

    public Attack getAttack(String name) {
        Attack attack = this.pokemonAttacks.get(name);
        if (attack == null) {
            System.out.println("ERROR, Attack not Found!");
            System.out.println("Available attacks : " + this.pokemonAttacks.keySet().toString());
        }
        return attack;
    }
}