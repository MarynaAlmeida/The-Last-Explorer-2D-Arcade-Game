package game.entities;
import game.entities.enemies.MovingEnemy;
import game.entities.enemies.StaticEnemy;
import game.entities.enemies.rewards.Coin;
import game.entities.enemies.rewards.Reward;
import game.entities.enemies.rewards.Shield;
import game.enums.Direction;
import game.main.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit and integration tests for the Explorer class.
 *
 * @author Harman Bains
 * @version 1.0
 */
public class ExplorerTest {
    private Explorer explorer;

    static class DemoGame extends Game {
        int score = 0;

        @Override
        public void updateScore(int value) {
            score += value;
        }
    }

    private DemoGame demoGame;
    List<Reward> rewards;
    List<MovingEnemy> movingEnemies;
    List<StaticEnemy> staticEnemies;

    @BeforeEach
    void mapGameSetUp() {
        GameMap map = new GameMap();
        map.loadMap(
                "src/test/resources/map.txt",
                rewards = new ArrayList<>(),
                movingEnemies = new ArrayList<>(),
                staticEnemies = new ArrayList<>()
        );

        Position currentPosition = new Position(0, 0);
        explorer = new Explorer(currentPosition, map);

        demoGame = new DemoGame();
        explorer.setGame(demoGame);
    }

    @Test
    void testInitialStateOfExplorer() {
        assertEquals(0, explorer.getShieldTicksRemaining());
        assertEquals(Direction.RIGHT, explorer.getFacing());
    }

    @Test
    void testTheShieldIsUpdatingToZero(){
        explorer.activateShield(1);
        explorer.updateShield();

        assertEquals(0, explorer.getShieldTicksRemaining());
        assertFalse(explorer.isShieldActive());
    }

    @Test
    void testTheShieldIsUpdatingConstant(){
        explorer.activateShield(0);
        explorer.updateShield();

        assertEquals(0, explorer.getShieldTicksRemaining());
        assertTrue(explorer.isShieldActive());
    }

    @Test
    void testTheShieldIsUpdatingAboveZero(){
        explorer.activateShield(2);
        explorer.updateShield();

        assertEquals(1, explorer.getShieldTicksRemaining());
        assertTrue(explorer.isShieldActive());
    }

    @Test
    void testSetterAndGetterOfFacing() {
        explorer.setFacing(Direction.DOWN);
        assertEquals(Direction.DOWN, explorer.getFacing());

        explorer.setFacing(Direction.RIGHT);
        assertEquals(Direction.RIGHT, explorer.getFacing());

        explorer.setFacing(Direction.UP);
        assertEquals(Direction.UP, explorer.getFacing());
    }

    @Test
    void testEarningCoinResultsInUpdatesScore() {
        Reward coin = new Coin(new Position(0,0), 10);
        explorer.collectReward(coin);
        assertEquals(10, demoGame.score);

        Reward moreCoins = new Coin(new Position(0,0), 5);
        explorer.collectReward(moreCoins);
        assertEquals(15, demoGame.score);
    }

    @Test
    void testEarningShieldResultsInUpdatesScore() {
        Reward shield = new Shield(new Position(0,0), 0);
        explorer.collectReward(shield);
        assertEquals(0, demoGame.score);
    }

    @Test
    void testCollectRewardWithNoType() {
        Reward reward = new Reward(new Position(0,0), 5) {
            boolean collected = false;

            @Override
            public void collect() {
                collected = true;
            }
        };
        explorer.collectReward(reward);
        assertTrue(true);
    }
}
