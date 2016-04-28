package br.usp.fllsouto.pokemonbattle.core;


import br.usp.fllsouto.pokemonbattle.core.battlesystem.*;

import java.util.ArrayList;
import java.util.HashMap;

public class PokemonBattle {
    public static void main(String args[]) {
        System.out.println("Starting Pokemon Battle");
        PokemonBattle.run();
        System.out.println("Ending Pokemon Battle");
    }

    public static void run() {
        System.out.println("Let's Battle!");

        Trainer red = getRed();
        Trainer blue = getBlue();

        BattleSystemController.runBattle(red, blue);
    }

    public static Trainer getRed() {
        ArrayList<String> inventoryList = new ArrayList<String>();

        for (int i = 0; i < 4; i++)
            inventoryList.add("Potion");

        HashMap<String, Pokemon> pokebolas= new HashMap<String, Pokemon>();

        pokebolas.put("Charizard", getCharizard());
        pokebolas.put("Pigeot", getPigeot());
        pokebolas.put("Venusaur", getVenusaur());

        return new Trainer("Red", pokebolas, inventoryList);
    }

    public static Trainer getBlue() {
        ArrayList<String> inventoryList = new ArrayList<String>();

        for (int i = 0; i < 4; i++)
            inventoryList.add("Potion");

        HashMap<String, Pokemon> pokebolas= new HashMap<String, Pokemon>();

        pokebolas.put("Pikachu", getPikachu());
        pokebolas.put("Blastoise", getBlastoise());
        pokebolas.put("Gengar", getGengar());

        return new Trainer("Blue", pokebolas, inventoryList);
    }

    public static Pokemon getPikachu() {
        HashMap<String, Double> attacks = new HashMap<String, Double>();
        attacks.put("Thunder Shock", 45.0);
        attacks.put("Quick Attack", 15.0);
        attacks.put("Iron Tail", 25.0);
        attacks.put("Lightning", 30.0);

        String name = "Pikachu";
        Double hp = 125.0;
        return new Pokemon(name, hp, attacks);
    }

    public static Pokemon getCharizard() {
        HashMap<String, Double> attacks = new HashMap<String, Double>();;
        attacks.put("Inferno", 45.0);
        attacks.put("Iron Tail", 15.0);
        attacks.put("Flamethower", 25.0);
        attacks.put("Wing Attack", 30.0);
        String name = "Charizard";
        Double hp = 200.0;
        return new Pokemon(name, hp, attacks);
    }

    public static Pokemon getPigeot() {
        HashMap<String, Double> attacks = new HashMap<String, Double>();;
        attacks.put("Hurricane", 45.0);
        attacks.put("Quick Attack", 15.0);
        attacks.put("Wing Attack", 25.0);
        attacks.put("Air Slash", 30.0);
        String name = "Pigeot";
        Double hp = 150.0;
        return new Pokemon(name, hp, attacks);
    }

    public static Pokemon getBlastoise() {
        HashMap<String, Double> attacks = new HashMap<String, Double>();;
        attacks.put("Hydro Pump", 45.0);
        attacks.put("Bubble", 15.0);
        attacks.put("Water Pulse", 25.0);
        attacks.put("Skull Bash", 30.0);
        String name = "Blastoise";
        Double hp = 175.0;
        return new Pokemon(name, hp, attacks);
    }

    public static Pokemon getGengar() {
        HashMap<String, Double> attacks = new HashMap<String, Double>();;
        attacks.put("Nightmare", 45.0);
        attacks.put("Lick", 15.0);
        attacks.put("Shadow Ball", 25.0);
        attacks.put("Dream Eater", 30.0);
        String name = "Gengar";
        Double hp = 155.0;
        return new Pokemon(name, hp, attacks);
    }

    public static Pokemon getVenusaur() {
        HashMap<String, Double> attacks = new HashMap<String, Double>();;
        attacks.put("Solar Beam", 45.0);
        attacks.put("Vine Whip", 15.0);
        attacks.put("Double Edge", 25.0);
        attacks.put("Petal Blizzard", 30.0);
        String name = "Venusaur";
        Double hp = 195.0;
        return new Pokemon(name, hp, attacks);
    }

}