package game.entities.enemies;
import game.main.*;
import game.entities.*;

/**
 * Abstract class for static enemies inflict damage when collided.
 * Subclasses are Thorns and BearTrap which are for purpose of traps.
 */
public abstract class StaticEnemy extends Entity {
    protected int damageValue;
    protected Game game;

    /**
     * Creates a StaticEnemy at a particular position with certain damage values.
     *
     * @param position    the enemy grid position on map
     * @param damageValue the damage happening when explorer collides with enemy
     */
    public StaticEnemy(Position position, int damageValue) {
        super(position);
        this.damageValue = damageValue;
    }

    /**
     * Returns the specific damage happening with this static enemy upon collison.
     *
     * @return the damage quantity
     */
    public int getDamageValue() {
        return damageValue;

    }

    /**
     * Case of a collision with the explorer.
     * Damage occurs except when the explorer has a shield active.
     *
     * @param explorer the explorer who encounters collison
     */
    //@Override
    public void onCollision(Explorer explorer) {
        if (!explorer.isShieldActive()) {
            game.updateHearts(-damageValue);
        }
    }
}