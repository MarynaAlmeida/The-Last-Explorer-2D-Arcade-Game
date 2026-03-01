package game.entities.enemies.rewards;

import game.entities.Entity;
import game.main.Position;

public abstract class Reward extends Entity {
    protected int value;
    protected boolean collected;

    public Reward(Position pos, int value) {

    }

    public int getValue() {
        return 0;
    }

    public boolean isCollected() {
        return false;
    }

    public abstract void collect();

}