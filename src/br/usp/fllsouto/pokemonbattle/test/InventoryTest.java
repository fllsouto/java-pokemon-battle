package br.usp.fllsouto.pokemonbattle.test;

import br.usp.fllsouto.pokemonbattle.core.Inventory;
import br.usp.fllsouto.pokemonbattle.core.Potion;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by fsouto on 27/04/16.
 */
public class InventoryTest {

    @Test
    public void testAddItemAndGet() throws Exception {
        Inventory inventory = new Inventory();
        inventory.addItem("Potion");

        Potion item = (Potion)inventory.getItem("Potion");
        Assert.assertTrue(item.getName().equals("Potion"));
        Assert.assertTrue(item.getValue().equals(30.0));
    }

    @Test
    public void testGetItemFromEmptyBag() throws Exception {
        Inventory inventory = new Inventory();

        Potion item = (Potion)inventory.getItem("Potion");
        Assert.assertNull(item);
    }

}