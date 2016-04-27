package br.usp.fllsouto.pokemonbattle.test;

import br.usp.fllsouto.pokemonbattle.core.Pokemon;
import org.junit.Assert.*;
import org.junit.*;

import java.util.HashMap;

/**
 * Created by fsouto on 27/04/16.
 */
public class PokemonTest {

    private Double DELTA = 0.0001;
    @Test
    public void testConstrutor() {
        String name = "Pikachu";
        Double hp = 100.0;
        HashMap<String, Double> attacks = new HashMap();
        attacks.put("Choque do Trovão", new Double(45.0));
        attacks.put("Ataque Rápido", new Double(15.0));
        attacks.put("Cauda de Ferro", new Double(25.0));
        attacks.put("Relampago", new Double(30));

        Pokemon pikachu = new Pokemon(name, hp, attacks);

        Assert.assertEquals(pikachu.getName(), name);
        Assert.assertEquals(pikachu.getHp(), hp, DELTA);

        Assert.assertEquals(pikachu.getAttack("Choque do Trovão").getName(), "Choque do Trovão");
        Assert.assertEquals(pikachu.getAttack("Choque do Trovão").getDamage(), 45.0, DELTA);

        Assert.assertEquals(pikachu.getAttack("Ataque Rápido").getName(), "Ataque Rápido");
        Assert.assertEquals(pikachu.getAttack("Ataque Rápido").getDamage(), 15.0, DELTA);

        Assert.assertEquals(pikachu.getAttack("Cauda de Ferro").getName(), "Cauda de Ferro");
        Assert.assertEquals(pikachu.getAttack("Cauda de Ferro").getDamage(), 25.0, DELTA);

        Assert.assertEquals(pikachu.getAttack("Relampago").getName(), "Relampago");
        Assert.assertEquals(pikachu.getAttack("Relampago").getDamage(), 30.0, DELTA);

    }
}
