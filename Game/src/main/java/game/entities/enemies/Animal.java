package main.java.game.entities.enemies;
import main.java.game.main.Position;

/**
 * Represents animal moving enemy
 * */
public class Animal extends MovingEnemy {
    /**
     * Animal's position on the game map
     * @param pos position of the Animal
     * */

    public Animal(Position pos) {
        super(pos);
    }
    /**
     * Animal's movement strategy from moving enemy
     * @param explorerPos explorer's position on the map, so Animal
     * knows and tries to catch it
     * */

    public void updateMovement(Position explorerPos) {
        super.updateMovement(explorerPos);
    }
}