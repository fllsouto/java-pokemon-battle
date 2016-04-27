package br.usp.fllsouto.pokemonbattle.core;

/**
 * Created by fsouto on 27/04/16.
 */
abstract class Item {
    protected String name;
    protected Double value;

    abstract public Double use();
}
