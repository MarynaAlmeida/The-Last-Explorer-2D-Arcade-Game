package game.entities.enemies.rewards;
import game.main.Position;

/**
 * This is a chest which player can open through coins.
 * Only if collecting all the coins on the map, which opening gives a bonus score.
 *
 *  @author Harman Bains
 *  @version 1.0
 */
public class Chest extends Reward {

    private int coinRequirement;
    private int bonusValue;

    /**
     * Generate a chest at a particular position.
     * The chest needs all the coins collected prior to opening as a hatch.
     * @param pos           board position of chest coordinates
     * @param coinRequirement coins needed by player to collect towards the chest
     * @param bonusValue    the additional score given by opening the chest
     */
    public Chest(Position pos, int coinRequirement, int bonusValue) {
        super(pos, bonusValue);
        this.coinRequirement = coinRequirement;
        this.bonusValue = bonusValue;
        this.collected = false;
    }

    /**
     * Attempts to open the chest. The chest can only be opened if the player
     * has collected at least the required number of coins.
     *
     * @param playerCollectedCoins the current # of coins within player
     * @return true when the chest can open along with bonus given, else false
     */
    public boolean attemptOpening(int playerCollectedCoins) {
        if (collected) {
            return false;
        }
        if (playerCollectedCoins >= coinRequirement) {
            collected = true;
            return true;
        }
        return false;
    }

    /**
     * Retrieve bonus value towards score for the opening of the chest.
     *
     * @return the bonus value for score
     */
    public int bonusScore(){
        return bonusValue;
    }

    /**
     * Attaining # of coins needed for chest.
     *
     * @return coin count needed
     */
    @SuppressWarnings("unused")
    public int sufficientCoins(){
        return coinRequirement;
    }
}