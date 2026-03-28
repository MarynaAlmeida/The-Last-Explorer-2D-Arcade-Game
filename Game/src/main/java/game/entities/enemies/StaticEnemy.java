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
     * @param game        the variable of the game itself
     */
    public StaticEnemy(Position position, int damageValue) {
        super(position);
        this.damageValue = damageValue;
        this.game = null;
    }

    /**
     * Returns the specific damage happening with this static enemy upon collision.
     *
     * @return the damage quantity.
     */
    public int getDamageValue() {
        return damageValue;

    }

    /**
     * Setter for the game variable.
     *
     * @param game the variable which will set up the game.
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Case of a collision with the explorer.
     * Damage occurs when game active and static enemy damage within range to explorer.
     *
     * @param explorer the explorer who encounters collision.
     */
    public void onCollision(Explorer explorer) {
        if (game != null) {
            game.staticEnemyDamage(damageValue);
        }
    }
}