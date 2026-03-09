package game.entities;

import game.entities.enemies.rewards.*;
import game.entities.enemies.*;
import game.main.*;
import game.enums.*;

/**
 * Represents explorer character and its interactions
 * with the game world regarding reward collection and status
 * */

public class Explorer extends Character{
    private boolean hasShield;
    private int shieldTicksRemaining;
    Game game;

    /**
     * Initializes explorer in specific position with no active shield
     * @param pos position of explorer on the game map
     * */

    public Explorer(Position pos, GameMap map) {
        // should Character constructor have GameMap or Game as parameter
        super(pos, map);
        this.hasShield = false;
        this.shieldTicksRemaining = 0;
    }

    /**
     * Acts as a setter for the game variable.
     * 
     * @param game the variable which will set this class' game variable to.
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Acts as the getter for the shieldTicksRemaining variable.
     * 
     * @return an integer indicating how many ticks the shield remains for.
     */
    public int getShieldTicksRemaining() {
        return shieldTicksRemaining;
    }

    /**
     * When explorer gets a reward the reward will be collected and activated/updated
     * @param reward specific Reward explorer will collect
     * */

    public void collectReward(Reward reward) {
        if(reward instanceof Shield) {
            Shield s = (Shield) reward;
            activateShield(s.getEffectDuration());
        }
        else if(reward instanceof Coin) {
            game.updateScore(reward.getValue());
        }
        reward.collect();
    }

    /**
     * Activates explorer's shield and sets its duration
     * @param shieldTime the number of game ticks the shield will remain active
     * */

    public void activateShield(int shieldTime) {
        hasShield = true;
        shieldTicksRemaining = shieldTime;

    }
    /**
     * Checks if shield is activated or not
     * */

    public boolean isShieldActive() {
        return hasShield;
    }

    /**
     * Decrements shield timer by one tick
     * and deactivates shield once the timer reaches zero
     * */
    public void updateShield() {
        if(shieldTicksRemaining > 0) {
            shieldTicksRemaining--;
            if(shieldTicksRemaining == 0) {
                hasShield = false;
            }
        }
    }

    /**
     * Acts as a setter for deactivating the shield in case the user runs into a moving or static enemy.
     */
    public void deactivateShield() {
        hasShield = false;
    }
}