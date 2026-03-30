package game.entities.enemies;
import game.entities.Explorer;
import game.main.Game;
import game.main.GameMap;
import game.main.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit and integration tests for the Thorn class.
 *
 * @author Harman Bains
 * @version 1.0
 */

public class ThornTest {

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
    void ThornsOnCollisionCausingDamage(){
        int damageValue = 2;
        Thorns thorn = new Thorns(new Position(0,0), damageValue);
        thorn.setGame(game);
        int initialHearts = game.getHearts();
        thorn.onCollision(explorer);
        assertEquals(initialHearts - damageValue, game.getHearts());
    }


}
