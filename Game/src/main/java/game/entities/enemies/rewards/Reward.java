package game.entities.enemies.rewards;

import game.entities.Entity;
import game.main.Position;

/**
 * Represents an abstract collectable reward in the game.
 * 
 * 
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
        super(position);
        this.value = value;
        this.collected = false;
    }

    public int getValue() {
        return this.value;
    }

    public boolean isCollected() {
        return collected;
    }

    public abstract void collect();
}