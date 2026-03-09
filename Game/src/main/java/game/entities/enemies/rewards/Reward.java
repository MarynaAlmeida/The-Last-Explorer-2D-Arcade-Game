package game.entities.enemies.rewards;

import game.entities.Entity;
import game.main.Position;

/**
 * Represents an abstract collectable reward in the game.
 * 
 * @author Nathaniel Chen
 * @version 1.0
 */
public abstract class Reward extends Entity {
    protected int value;
    protected boolean collected;

    /**
     * Creates an uncollected reward object at a specific position on the board with a specific value.
     * 
     * @param pos the position on the board that the shield will spawn on.
     * @param value the value/score of the reward object that is being created.
     */
    public Reward(Position pos, int value) {
        super(pos);
        this.value = value;
        this.collected = false;
    }

    /**
     * Acts as the getter for the protected value attribute.
     * 
     * @return the value of the current abstract object value.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Determines if the abstract object has been collected by the player.
     * 
     * @return a boolean indicating if the object has been collected or not.
     */
    public boolean isCollected() {
        return collected;
    }

    /**
     * Sets the collected attribute to true, indicating the player collected it.
     */
    public void collect() {
        this.collected = true;
    }

    public void setUncollected() {
        this.collected = false;
    }
}