package game.entities.enemies;
import game.entities.Explorer;
import game.main.*;

/**
 * Acts as a static enemy that when stepped onto causes damage.
 * 
 * @author Harman Bains
 * @version 1.0
 */
public class BearTrap extends StaticEnemy {
    /**
     * Makes a bear trap on a particular position.
     *
     * @param position    bear trap's grid position on map
     * @param damageValue the damage occurring once activated
     */
    public BearTrap(Position position, int damageValue) {
        super(position, damageValue);
    }

    /**
     * The case for collision between the explorer by posing damage through bear trap
     * Follows through superclass implementation.
     *
     * @param explorer the explorer applied a step(s) onto trap
     */
    @Override
    public void onCollision(Explorer explorer) {
        super.onCollision(explorer);
    }
}