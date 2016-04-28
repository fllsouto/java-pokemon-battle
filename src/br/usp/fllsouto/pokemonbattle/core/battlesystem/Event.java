package br.usp.fllsouto.pokemonbattle.core.battlesystem;

/**
 * Created by fsouto on 27/04/16.
 */
abstract public class Event {
    private long evtTime;

    public Event(long eventTime){
        this.evtTime = eventTime;
    }

    public Boolean ready() {
        return System.currentTimeMillis() >= this.evtTime;
    }

    abstract public void action();
    abstract public String description();
}
