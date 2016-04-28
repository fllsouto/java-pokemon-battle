package br.usp.fllsouto.pokemonbattle.core.battlesystem;

import br.usp.fllsouto.pokemonbattle.core.*;

/**
 * Created by fsouto on 28/04/16.
 */
public class BattleSystemController extends EventController {
    private Trainer trainerA;
    private Trainer trainerB;
    private Boolean endGame = false;

    private class QuitBattle extends Event {
        private static final int priority = 5;
        Trainer from;
        private String description = "";

        public QuitBattle(long eventTime, Trainer from) {
            super(eventTime);
            this.from = from;
        }

        public void action() {
            if (BattleSystemController.this.trainerA.equals(this.from)) {
                description = "TRAINER " + BattleSystemController.this.trainerB.getName() + " WIN!\nTrainer " + BattleSystemController.this.trainerA.getName() + " give up the battle!";
            } else {
                description = "TRAINER " + BattleSystemController.this.trainerB.getName() + " WIN!\nTrainer " + BattleSystemController.this.trainerA.getName() + " give up the battle!";
            }
            endGame = true;
        }
        public String description() {
            return description;
        }

    }
    private class EndBattle extends Event {
        private static final int priority = 5;
        private String description = "";

        public EndBattle(long eventTime) {
            super(eventTime);
        }

        public void action() {
            int alivePokemonTrainerA = BattleSystemController.this.trainerA.getAlivePokemonQuantity();
            int alivePokemonTrainerB = BattleSystemController.this.trainerB.getAlivePokemonQuantity();

            if (alivePokemonTrainerA == alivePokemonTrainerB && alivePokemonTrainerA == 0) {
                description = "DRAW!\nAll pokemons from " + BattleSystemController.this.trainerA.getName() + " and " + BattleSystemController.this.trainerB.getName() + " are defeated!";
            } else if (alivePokemonTrainerA > alivePokemonTrainerB ) {
                description = "TRAINER " + BattleSystemController.this.trainerA.getName() + " WIN!\nAll pokemons from " + BattleSystemController.this.trainerB.getName() + " was defeated!";
            } else {
                description = "TRAINER " + BattleSystemController.this.trainerB.getName() + " WIN!\nAll pokemons from " + BattleSystemController.this.trainerA.getName() + " was defeated!";
            }
            endGame = true;
        }
        public String description() {
            return description;
        }



    }
    private class ChangeMainPokemon extends Event {
        private static final int priority = 4;
        Trainer from;
        String pokemonName;

        public ChangeMainPokemon(long eventTime, Trainer from) {
            super(eventTime);
            this.from = from;
            this.pokemonName = null;
        }

        public ChangeMainPokemon(long eventTime, Trainer from, String pokemonName) {
            super(eventTime);
            this.from = from;
            this.pokemonName = pokemonName;
        }

        public void action() {

        }

        public String description() {
            if (this.pokemonName == null) {
                return ("Trainer" + this.from.getName() + " are changing your main pokemon " + this.from.getMainPokemon().getName() + " for another one.");
            } else {
                return ("Trainer" + this.from.getName() + " are changing your main pokemon " + this.from.getMainPokemon().getName() + " for " + this.pokemonName);

            }
        }
    }

    private class UseItem extends Event {
        private static final int priority = 3;
        private Trainer from;
        private String itemName;
        private String description = "";

        public UseItem(long eventTime, Trainer from, String itemName) {
            super(eventTime);
            this.from = from;
            this.itemName = itemName;
        }

        public void action() {
            Potion item = (Potion)this.from.getItemFromInventory(itemName);

            if (item == null) {
                description = "Trainer " + this.from.getName() + " doesn't have " + this.itemName + " in his inventory!";
            } else {
                Double healingPower = item.use(this.from.getMainPokemon());
                description = "Trainer " + this.from.getName() + " used " + this.itemName + " and healed " + healingPower;
            }
        }

        public String description() {
            return description;
        }
    }

    private class PokemonAttacks extends Event {
        private static final int priority = 2;
        private Trainer from;
        private Trainer to;
        private Attack attack;

        public PokemonAttacks(long eventTime, Trainer from, Trainer to) {
            super(eventTime);
            this.from = from;
            this.to = to;
        }

        public void action() {
            attack = this.from.getMainPokemon().getRandomAttack();
            this.to.getMainPokemon().takeDamage(attack);
        }

        public String description() {
            return ("Trainer " + this.from.getName() + " with pokemon " + this.from.getMainPokemon().getName() + " attacked " + this.to.getMainPokemon().getName() + " Trainer's " + this.to.getName() + " pokemon with " + attack.getName());
        }
    }

    private class Restart extends Event {
        private static final int priority = 1;

        public Restart(long eventTime) {
            super(eventTime);
        }

        public void action() {
            long tm = System.currentTimeMillis();

            if (endGame)
                return;

            addEvent(new EndBattle(tm));
            addEvent(new PokemonAttacks(tm + 3000, trainerA, trainerB));
            addEvent(new PokemonAttacks(tm + 3000, trainerB, trainerA));
            addEvent(new Restart(tm + 3000));
        }

        public String description() {
            return "Restarting the Battle";
        }

    }

    public static void runBattle(Trainer trainerA, Trainer trainerB) {
        BattleSystemController bsc = new BattleSystemController();
        long tm = System.currentTimeMillis();

        bsc.trainerA = trainerA;
        bsc.trainerB = trainerB;

        bsc.addEvent(bsc.new Restart(tm));
        bsc.run();
    }
}
