package game.entities.enemies.rewards;

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

    /**
     * Creates a shield object at a specific position on the board with a specific lifetime
     * and effect duration.
     * 
     * @param pos the position on the board that the shield will spawn on.
     * @param effectDuration the time of how long the shield will stay active once picked up.
     * @param timeRemainingOnBoard the time of how long the shield will last on the board before expiring.
     */
    public Shield(Position pos, int effectDuration, int timeRemainingOnBoard) {
        super(pos, 0);
        this.effectDuration = effectDuration;
        this.timeRemainingOnBoard = timeRemainingOnBoard;
    }

    /**
     * Returns true if the item hasn't been picked up yet and if the shield's remaining time 
     * on the board is 0 seconds or less.
     * 
     * <p>This function is called every tick while the shield is still on the board.</p>
     * 
     * @return a boolean value depending on if the item is expired or not.
     */
    public bool isExpired() {
        return !collected && timeRemainingOnBoard <= 0;
    }

    /**
     * Checks to see if the current shield object is expired and if not, decrements the
     * shield's time remaining on the board by one value.
     * 
     * <p>This function is called every tick while the shield is still on the board.</p>
     */
    public void updateOnBoard() {
        if (!isExpired()) {
            timeRemainingOnBoard--;
        }
    }

    @Override
    public void collect() {
        this.collected = true;
    }
}