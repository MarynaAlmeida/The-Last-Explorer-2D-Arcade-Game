package game.entities.enemies;
import game.main.*;
import game.entities.*;

/**
 * Abstract class for static enemies.
 * 
 * <p>These static enemies inflict damage when collided.</p>
 * 
 * <p>The subclasses are {@link Thorns} and {@link BearTrap}, which are for purpose of traps.
 * 
 * @author Harman Bains
 * @version 1.0
 */
public abstract class StaticEnemy extends Entity {
    protected int damageValue;
    protected Game game;

    /**
     * Creates a StaticEnemy at a particular position with certain damage values.
     *
     * @param position    the enemy grid position on map.
     * @param damageValue the damage happening when the explorer collides with enemy.
     */
    public StaticEnemy(Position position, int damageValue) {
        super(position);
        this.damageValue = damageValue;
    }

    /**
     * Returns the specific damage happening with this static enemy upon collison.
     *
     * @return the damage quantity.
     */
    public int getDamageValue() {
        return damageValue;

    }

    /**
     * Case of a collision with the explorer.
     * Damage occurs except when the explorer has a shield active.
     *
     * @param explorer the explorer who encounters collison.
     */
    public void onCollision(Explorer explorer) {
        if (!explorer.isShieldActive()) {
            game.updateHearts(game.getHearts()-damageValue);
        }
    }

    /**
     * Setter for the game variable.
     *
     * @param game the variable which will set up the game.
     */
    public void setGame(Game game) {
        this.game = game;
    }
}