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

            if (alivePokemonTrainerA == alivePokemonTrainerB) {

                if (alivePokemonTrainerA != 0)
                    return;

                description = "DRAW!\nAll pokemons from " + BattleSystemController.this.trainerA.getName() + " and " + BattleSystemController.this.trainerB.getName() + " are defeated!";
            } else if (alivePokemonTrainerA > alivePokemonTrainerB) {

                if (alivePokemonTrainerB != 0)
                    return;

                description = "TRAINER " + BattleSystemController.this.trainerA.getName() + " WIN!\nAll pokemons from " + BattleSystemController.this.trainerB.getName() + " was defeated!";
            } else {

                if (alivePokemonTrainerA != 0)
                    return;

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
        private Trainer from;
        private String description;
        private Pokemon changedPokemon;

        public ChangeMainPokemon(long eventTime, Trainer from) {
            super(eventTime);
            this.from = from;
        }

        public void action() {
            changedPokemon = this.from.getMainPokemon();
            this.description = "Trainer " + this.from.getName() + " are changing your main pokemon " + changedPokemon.getName();
            if(!this.from.changePokemon()) {
                this.description += "\nTrainer " + this.from.getName() + " doesn't have anymore pokemons!";
                addEvent(new EndBattle(System.currentTimeMillis() + 10));
            } else {
                this.description += " by " + this.from.getMainPokemon().getName();
            }
        }

        public String description() {
            return this.description;
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
        private String description = "";
        public PokemonAttacks(long eventTime, Trainer from, Trainer to) {
            super(eventTime);
            this.from = from;
            this.to = to;
        }

        public void action() {
            this.description = "\n------------------------------------------------------------------";
            this.description += "\nTrainer  " + this.from.getName() + " attacks " + this.to.getName();

            attack = this.from.getMainPokemon().getRandomAttack();

            this.description += "\n" + this.from.getMainPokemon().getName() + " attacked " + this.to.getMainPokemon().getName() + " with " + attack.getName();
            this.description += "\n\nBefore:" + this.to.getMainPokemon().getLifeInfo();

            this.to.getMainPokemon().takeDamage(attack);

            this.description += "\n\nAfter:" + this.to.getMainPokemon().getLifeInfo();

            if (this.to.getMainPokemon().isDead()) {
                this.description += "\n\n" + this.to.getMainPokemon().getName() + " is dead!";
                addEvent(new ChangeMainPokemon(System.currentTimeMillis(), this.to));
            }

            this.description += "\n------------------------------------------------------------------";

        }

        public String description() {

            return this.description;
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
            return "New Battle Turn...";
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
