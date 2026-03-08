package main.java.game.entities.enemies.rewards;

import main.java.game.main.Position;

/**
 * Represents a coin reward that players must pick up to win the game.
 * 
 * @author Nathaniel Chen
 * @version 1.0
 */
public class Coin extends Reward {
    /**
     * Creates an uncollected coin object on the map at a specific position on the board with a specific value.
     * 
     * @param pos the position where the coin will be placed on the board.
     * @param value the total value that the player will be rewarded with when the coin is picked up.
     */
    public Coin(Position pos, int value) {
        super(pos, value);
    }
}