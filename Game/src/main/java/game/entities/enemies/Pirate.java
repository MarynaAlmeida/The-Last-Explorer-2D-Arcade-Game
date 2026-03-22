package game.entities.enemies;
import game.enums.PirateType;
import game.main.*;

/**
 * Represents pirate moving enemy
 * */

/**
 * Represents a moving pirate enemy.
 * 
 * @author Maryna Almeida
 * @version 1.0
 * */
public class Pirate extends MovingEnemy{
    private PirateType type;
    
    /**
     * Constructs a pirate object on the map at a designated location.
     * 
     * @param pos position on the map where the Animal will be placed.
     * @param map game map that the animal exists on.
     * @param type the type of pirate (pirate1, pirate2).
     * */
    public Pirate(Position pos,  GameMap map, PirateType type) {
        super(pos, map);
        this.type = type;
    }

    /**
     * Pirate's movement strategy from moving enemy.
     * 
     * @param explorerPos explorer's position on the map, so Pirate and Animal
     * knows and tries to catch it
      */
    public void updateMovement(Position explorerPos) {
        super.updateMovement(explorerPos);
    }

    /**
     * Gets the type of pirate.
     * 
     * @return the pirate type.
     */
    public PirateType getPirateType() {
        return type;
    }
}