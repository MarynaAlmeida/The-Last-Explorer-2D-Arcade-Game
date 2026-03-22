package game.entities.enemies;
import game.entities.Explorer;
import game.main.Game;
import game.main.GameMap;
import game.main.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the abstract BearTrap class.
 *
 * @author Harman Bains
 * @version 1.0
 */

public class BearTrapTest {

    private Game game;
    private Explorer explorer;

    @BeforeEach
    void setUp() {
        game = new Game();

        explorer = new Explorer(new Position(0, 0), new GameMap());
        explorer.setGame(game);

        // Ensure starting hearts are consistent
        while (game.getHearts() < game.getMaxHearts()) {
            game.updateHearts(1);
        }

        explorer.deactivateShield(); // ensure shield starts OFF
    }

    @Test
    void BearTrapOnCollisionEffects(){
        int damageValue = 3;
        int initialHearts = game.getHearts();
        BearTrap bearTrap = new BearTrap(new Position(0, 0), damageValue);
        bearTrap.setGame(game);
        bearTrap.onCollision(explorer);

        assertEquals(initialHearts - damageValue, game.getHearts());
    }
}
