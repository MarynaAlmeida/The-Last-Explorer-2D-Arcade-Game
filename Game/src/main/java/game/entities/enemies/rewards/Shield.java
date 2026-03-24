package game.entities.enemies.rewards;

import java.util.Random;

import game.main.Position;

/**
 * Represents a shield reward that the player can pick up.
 * 
 * @author Nathaniel Chen
 * @version 1.0
 */
public class Shield extends Reward {
    private int effectDuration;
    private int timeRemainingOnBoard;
    private int timeTillNextSpawn;
    private boolean spawned;
    private Random rn = new Random();
    private int minTicksTillShieldSpawn;
    private int maxTicksTillShieldSpawn;
    private int maxVisibleShieldTime;
    private int minVisibleShieldTime;

    /**
     * Creates a shield object at a specific position on the board with a specific lifetime
     * and effect duration.
     * 
     * @param pos the position on the board that the shield will spawn on.
     * @param effectDuration the time of how long the shield will stay active once picked up.
     * @param timeRemainingOnBoard the time of how long the shield will last on the board before expiring.
     */
    public Shield(Position pos, int effectDuration) {
        super(pos, 0);
        this.effectDuration = effectDuration;
        this.timeRemainingOnBoard = 0;
        this.spawned = false;
        this.minTicksTillShieldSpawn = 60;
        this.maxTicksTillShieldSpawn = 200;
        this.minVisibleShieldTime = 100;
        this.maxVisibleShieldTime = 180;
        this.timeTillNextSpawn = getRandomTicksTillNextShieldSpawn();

    }

    public int getTimeRemaining() {
        return timeRemainingOnBoard;
    }

    public int getTimeTillNextSpawn() {
        return timeTillNextSpawn;
    }

    public boolean isSpawned() {
        return spawned;
    }

    public void spawn() {
        this.spawned = true;
        this.collected = false;
        this.timeRemainingOnBoard = getRandomVisibleShieldTicks();
    }

    public void despawn() {
        this.spawned = false;
        this.timeRemainingOnBoard = 0;
        this.timeTillNextSpawn = getRandomTicksTillNextShieldSpawn();
    }

    /**
     * Returns an integer indicating the remaining time before the shield runs out.
     * 
     * @return an integer of the remaining effect duration.
     */
    public int getEffectDuration() {
        return effectDuration;
    }

    /**
     * Checks to see if the current shield object is expired and if not, decrements the
     * shield's time remaining on the board by one value.
     * 
     * <p>This function is called every tick while the shield is still on the board.</p>
     */
    public void updateOnBoard() {
        if (spawned) {
            timeRemainingOnBoard--;

            if (timeRemainingOnBoard <= 0) {
                despawn();
            }
        } else {
            timeTillNextSpawn--;

            if (timeTillNextSpawn <= 0) {
                spawn();
            }
        }
    }

    private int getRandomTicksTillNextShieldSpawn() {
        return rn.nextInt(maxTicksTillShieldSpawn - minTicksTillShieldSpawn + 1) + minTicksTillShieldSpawn;
    }

    private int getRandomVisibleShieldTicks() {
        return rn.nextInt(maxVisibleShieldTime - minVisibleShieldTime + 1) + minVisibleShieldTime;
    }

    @Override
    public void collect() {
        super.collect();
        despawn();
    }
}