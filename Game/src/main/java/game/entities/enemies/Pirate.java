package main.java.game.entities.enemies;
import main.java.game.main.*;
/**
 * Represents pirate moving enemy
 * */

public class Pirate extends MovingEnemy{
    /**
     * Pirate's position on the game map
     * @param pos position of the Animal
     * */

    public Pirate(Position pos) {
        super(pos);
    }

    /**
     * Pirate's movement strategy from moving enemy
     * @param explorerPos explorer's position on the map, so Pirate
     * knows and tries to catch it
      */
    public void updateMovement(Position explorerPos) {
        super.updateMovement(explorerPos);
    }
}