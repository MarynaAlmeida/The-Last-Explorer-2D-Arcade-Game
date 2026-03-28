package game.entities.enemies;
import game.entities.Explorer;
import game.main.Position;


/**
 * Thorns is a trap is a static enemy that when traversed into causes damage.
 * 
 * @author Harman Bains
 * @version 1.0
 */
public class Thorns extends StaticEnemy {
    /**
     * Makes a thorn trap on a particular position.
     *
     * @param position    thorn trap's grid position on map.
     * @param damageValue the damage occurring once contacted.
     */
    public Thorns(Position position, int damageValue) {
        super(position, damageValue);
    }

    /**
     * The case for collision between the explorer by causing damage through thorn trap.
     * 
     * <p> This method follows its superclass {@link StaticEnemy}.
     *
     * @param explorer the explorer that moved into the trap.
     */
    public void onCollision(Explorer explorer) {
        super.onCollision(explorer);

    }
}
