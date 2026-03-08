package game.entities.enemies;
import game.enums.AnimalType;
import game.main.Game;
import game.main.GameMap;
import game.main.Position;

/**
 * Represents animal moving enemy
 * */
public class Animal extends MovingEnemy {
    private AnimalType type;
    /**
     * Animal's position on the game map
     * @param pos position of the Animal
     * */

    public Animal(Position pos,  GameMap map, AnimalType type) {
        super(pos, map);
        this.type = type;
    }
    /**
     * Animal's movement strategy from moving enemy
     * @param explorerPos explorer's position on the map, so Animal
     * knows and tries to catch it
     * */

    public void updateMovement(Position explorerPos) {
        super.updateMovement(explorerPos);
    }

    public AnimalType getAnimalType() {
        return type;
    }
}