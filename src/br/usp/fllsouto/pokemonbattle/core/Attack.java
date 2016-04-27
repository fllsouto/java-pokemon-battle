package br.usp.fllsouto.pokemonbattle.core;

/**
 * Created by fsouto on 27/04/16.
 */
public class Attack {
    private String name;
    private Double damage;

    public Attack(String name, double damage) {
        this.name = name;
        this.damage = damage;
    }

    public String getName() {
        return this.name;
    }

    public Double getDamage() {
        return this.damage;
    }
}
