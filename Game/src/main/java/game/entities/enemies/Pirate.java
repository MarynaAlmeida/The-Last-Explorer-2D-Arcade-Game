package main.java.game.entities.enemies;
import main.java.game.enums.AnimalType;
import main.java.game.enums.PirateType;
import main.java.game.main.*;
/**
 * Represents pirate moving enemy
 * */

public class Pirate extends MovingEnemy{
    private PirateType pirateType;
    /**
     * Pirate's position on the game map
     * @param pos position of the Animal
     * */

    public Pirate(Position pos,  GameMap map, PirateType pirateType) {
        super(pos, map);
        this.pirateType = pirateType;
    }

    /**
     * Pirate's movement strategy from moving enemy
     * @param explorerPos explorer's position on the map, so Pirate
     * knows and tries to catch it
      */
    public void updateMovement(Position explorerPos) {
        super.updateMovement(explorerPos);
    }

    public PirateType getPirateType() {
        return pirateType;
    }
}