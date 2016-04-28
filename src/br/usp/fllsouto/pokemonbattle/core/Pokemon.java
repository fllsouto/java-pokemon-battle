package br.usp.fllsouto.pokemonbattle.core;

import java.util.*;

/**
 * Created by fsouto on 27/04/16.
 */
public class Pokemon {
    private String name;
    private Double maxHP;
    private Double hp;
    private HashMap<String, Attack> pokemonAttacks = new HashMap();

    public Pokemon(String name, double maxHP, HashMap<String, Double> attacks) {
        this.name = name;
        this.maxHP = maxHP;
        this.hp = maxHP;
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

    public Double getMaxHp() {
        return this.maxHP;
    }

    public Attack getAttack(String name) {
        Attack attack = this.pokemonAttacks.get(name);
        if (attack == null) {
            System.out.println("ERROR, Attack not Found!");
            System.out.println("Available attacks : " + this.pokemonAttacks.keySet().toString());
        }
        return attack;
    }

    public Attack getRandomAttack() {
        List<String> attackList = new ArrayList(this.pokemonAttacks.keySet());
        int indx = new Random().nextInt(attackList.size());
        return this.getAttack(attackList.get(indx));
    }

    public Boolean isDead() {
        return (this.hp <= 0);
    }

    public Boolean takeDamage(Attack attack) {
        if (this.isDead()) {
            return false;
        } else {
            this.updateHp(-attack.getDamage());
            return true;
        }
    }

    public void updateHp(Double value) {
        this.hp += value;
        if (this.hp > this.maxHP)
            this.hp = this.maxHP;
    }

    public String getLifeInfo() {
        return String.format("\n%s\n HP: %f/%f", this.getName(), this.getMaxHp(), this.getHp());
    }
}