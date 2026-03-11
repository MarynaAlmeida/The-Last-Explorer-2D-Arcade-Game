package game.entities.enemies;
import game.enums.AnimalType;
import game.main.GameMap;
import game.main.Position;

/**
 * Represents animal moving enemy
 * 
 * @author Maryna Almeida
 * @version 1.0
 * */
public class Animal extends MovingEnemy {
    private AnimalType type;

    /**
     * Act as the constructor for the animal class.
     * 
     * @param pos position of the Animal.
     * @param map game map that the animal exists on.
     * @param type the type of animal (hare, fox, boar).
     * */
    public Animal(Position pos,  GameMap map, AnimalType type) {
        super(pos, map);
        this.type = type;
    }

    /**
     * Updates the animals intended movement for a tick.
     * 
     * @param explorerPos explorer's position on the map.
     * */
    public void updateMovement(Position explorerPos) {
        super.updateMovement(explorerPos);
    }

    /**
     * Acts as a getter for the animal object type.
     * 
     * @return the type of animal object.
     */
    public AnimalType getAnimalType() {
        return type;
    }
}