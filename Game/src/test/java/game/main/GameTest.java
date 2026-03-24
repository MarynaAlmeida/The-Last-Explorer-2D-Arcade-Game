package game.main;

import game.enums.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Game class.
 *
 * @author Jayden Pai
 * @version 1.0
 */
public class GameTest {
    private Game game;

    @BeforeEach
    void refreshSetup() {
        game = new Game();
    }

    @Test
    void testInitialStateIsMenu() {
        assertEquals(GameState.MENU, game.getState());
    }

    @Test
    void testInitialHeartsIsSix() {
        assertEquals(6, game.getHearts());
    }

    @Test
    void testInitialMaxHeartsIsSix() {
        assertEquals(6, game.getMaxHearts());
    }

    @Test
    void testInitialScoreIsZero() {
        assertEquals(0, game.getScore());
    }

    @Test
    void testUpdateScoreIncrementsCorrectly() {
        game.updateScore(5);
        assertEquals(5, game.getScore());
    }

    @Test
    void testUpdateScoreDecrementsCorrectly() {
        game.updateScore(-3);
        assertEquals(-3, game.getScore());
    }

    @Test
    void testUpdateScoreAccumulatesCorrectly() {
        game.updateScore(5);
        game.updateScore(3);
        assertEquals(8, game.getScore());
    }

    @Test
    void testUpdateHeartsChangesCorrectly() {
        game.updateHearts(4);
        assertEquals(4, game.getHearts());
    }

    @Test
    void testUpdateHeartsToZero() {
        game.updateHearts(0);
        assertEquals(0, game.getHearts());
    }

    @Test
    void testSetStateChangesState() {
        game.setState(GameState.PLAYING);
        assertEquals(GameState.PLAYING, game.getState());
    }

    @Test
    void testLoseConditionTriggersWhenZeroHearts() {
        game.updateHearts(0);
        game.checkLoseCondition();
        assertEquals(GameState.LOST, game.getState());
    }

    @Test
    void testLoseConditionTriggersWhenScoreIsNegative() {
        game.updateScore(-1);
        game.checkLoseCondition();
        assertEquals(GameState.LOST, game.getState());
    }

    @Test
    void testLoseConditionDoesNotTriggerWithPositiveHeartsAndScore() {
        game.checkLoseCondition();
        assertEquals(GameState.MENU, game.getState());
    }

    @Test
    void testGetPixelXReturnsCorrectValueForExplorer() {
        // Start: (0, 14), 0 * 32 = 0
        assertEquals(0, game.getPixelX(game.getExplorer()));
    }

    @Test
    void testGetPixelYReturnsCorrectValueForExplorer() {
        // Start: (0, 14), 14 * 32 = 448
        assertEquals(448, game.getPixelY(game.getExplorer()));
    }

    @Test
    void testCollisionDetectedWhenEntitiesOverlap() {
        game.getChest().setPosition(new Position(0, 14));
        assertTrue(game.checkCollision(game.getExplorer(), game.getChest()));
    }

    @Test
    void testNoCollisionWhenEntitiesAreFarApart() {
        game.getChest().setPosition(new Position(22, 16));
        assertFalse(game.checkCollision(game.getExplorer(), game.getChest()));
    }

    @Test
    void testGetMapReturnsNonNull() {
        assertNotNull(game.getMap());
    }

    @Test
    void testGetExplorerReturnsNonNull() {
        assertNotNull(game.getExplorer());
    }

    @Test
    void testGetExplorerStartsAtCorrectPosition() {
        assertEquals(new Position(0, 14), game.getExplorer().getPosition());
    }
}
