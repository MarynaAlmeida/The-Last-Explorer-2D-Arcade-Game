package game.entities.enemies;
import game.entities.Explorer;
import game.main.Game;
import game.main.GameMap;
import game.main.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit and integration tests for the abstract StaticEnemy class.
 *
 * @author Harman Bains
 * @version 1.0
 */
public class StaticEnemiesTest {
    private Explorer explorer;
    private StaticEnemy enemy;
    private Game game;

    // Concrete of testing abstract class
    private static class TestStaticEnemy extends StaticEnemy {
        public TestStaticEnemy(Position position, int damageValue) {
            super(position, damageValue);
        }
    }

    @BeforeEach
    void setUp() {
        game = new Game();

        explorer = new Explorer(new Position(0, 0), new GameMap());
        explorer.setGame(game);

        // Ensure starting hearts are consistent
        while (game.getHearts() < game.getMaxHearts()) {
            game.updateHearts(game.getHearts() + 1);
        }

        explorer.deactivateShield(); // ensure shield starts OFF
    }

    @Test
    void testConstructorSetsPositionAndDamageValue() {
        Position pos = new Position(5, 7);

        enemy = new TestStaticEnemy(pos, 4);
        enemy.setGame(game);
        assertEquals(pos, enemy.getPosition());
        assertEquals(4, enemy.getDamageValue());
    }

    @Test
    void testGetDamageValueReturnsCorrectValue() {
        enemy = new TestStaticEnemy(new Position(0, 0), 3);
        enemy.setGame(game);
        assertEquals(3, enemy.getDamageValue());

        enemy = new TestStaticEnemy(new Position(0, 0), 0);
        enemy.setGame(game);
        assertEquals(0, enemy.getDamageValue());

        enemy = new TestStaticEnemy(new Position(0, 0), 10);
        enemy.setGame(game);
        assertEquals(10, enemy.getDamageValue());
    }

    @Test
    void testOnCollisionDamagesExplorerWhenNoShield() {
        int damage = 2;
        enemy = new TestStaticEnemy(new Position(1, 1), damage);
        enemy.setGame(game);

        int initialHearts = game.getHearts();

        enemy.onCollision(explorer);

        assertEquals(initialHearts - damage, game.getHearts());
    }

    @Test
    void testOnCollisionDoesNotDamageWhenShieldActive() {
        int damage = 1;
        enemy = new TestStaticEnemy(new Position(2, 2), damage);
        enemy.setGame(game);

        int initialHearts = game.getHearts();
        explorer.activateShield(5);// shield ON
        enemy.onCollision(explorer);

        assertEquals(initialHearts, game.getHearts());
    }

    @Test
    void testZeroDamageDoesNotAffectHearts() {
        enemy = new TestStaticEnemy(new Position(3, 3), 0);
        enemy.setGame(game);
        int initialHearts = game.getHearts();
        enemy.onCollision(explorer);
        assertEquals(initialHearts, game.getHearts());
    }

    @Test
    void testNegativeDamageIncreasesHeartsWhenNoShield() {
        enemy = new TestStaticEnemy(new Position(4, 4), -2);
        enemy.setGame(game);

        int initialHearts = game.getHearts();

        enemy.onCollision(explorer);

        assertEquals(initialHearts + 2, game.getHearts());
    }

    @Test
    void testShieldPreventsHeartGainFromNegativeDamage() {
        enemy = new TestStaticEnemy(new Position(5, 5), -3);
        enemy.setGame(game);

        int initialHearts = game.getHearts();
        explorer.activateShield(20);
        enemy.onCollision(explorer);

        assertEquals(initialHearts, game.getHearts());
    }
}
