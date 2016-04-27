package br.usp.fllsouto.pokemonbattle.core;

import br.usp.fllsouto.pokemonbattle.core.Trainer;

public class Battle {
    public static void main(String args[]) {
        System.out.println("Starting Pokemon Battle");
        Battle.run();
        System.out.println("Ending Pokemon Battle");
    }

    public static void run() {
        System.out.println("Let's Battle!");
        Trainer ash = new Trainer();
        ash.foo();
    }

    public static boolean isTrue() {
        return true;
    }
}