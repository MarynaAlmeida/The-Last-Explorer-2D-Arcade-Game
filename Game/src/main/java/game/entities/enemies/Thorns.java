package game.entities.enemies;
import game.entities.Explorer;
import game.entities.enemies.StaticEnemy;
import game.main.Position;


/**
 * Thorns is a trap is a static enemy that when traversed into causes damage .
 */
public class Thorns extends StaticEnemy {

    /**
     * Making a thorn trap on a particular position.
     *
     * @param position    thorn trap's grid position on map
     * @param damageValue the damage occurring once contacted
     */
    public Thorns(Position position, int damageValue) {
        super(position, damageValue);

    }

    /**
     * The case for collision between the explorer by causing damage through thorn trap
     * Follows its superclass static enemy.
     *
     * @param explorer the explorer moves into the trap
     */

    public void onCollision(Explorer explorer) {
        super.onCollision(explorer);

    }
}
