package game.main;

import game.entities.enemies.rewards.Coin;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;
import java.util.List;

import game.entities.enemies.MovingEnemy;
import game.entities.enemies.rewards.Reward;
import game.entities.enemies.StaticEnemy;
import game.enums.CellType;

/**
 * Unit tests for the GameMap class.
 *
 * @author Jayden Pai
 * @version 2.0
 */
public class GameMapTest {
    private GameMap map;
    private List<Reward> rewards;
    private List<MovingEnemy> movingEnemies;
    private List<StaticEnemy> staticEnemies;

    @BeforeEach
    void refreshSetup() {
        map = new GameMap();
        rewards = new ArrayList<>();
        movingEnemies = new ArrayList<>();
        staticEnemies = new ArrayList<>();
        String path = "src/test/resources/map.txt";
        map.loadMap(path, rewards, movingEnemies, staticEnemies);
    }

    @Test
    void testDefaultStartPositionIsOrigin() {
        assertEquals(new Position(0, 0), map.getStart());
    }

    @Test
    void testMapHeightIsCorrectAfterLoad() {
        assertEquals(18, map.getHeight());
    }

    @Test
    void testMapWidthIsCorrectAfterLoad() {
        assertEquals(24, map.getWidth());
    }

    @Test
    void testGetCellReturnsNullForNegativeX() {
        assertNull(map.getCell(new Position(-1, 0)));
    }

    @Test
    void testGetCellReturnsNullForNegativeY() {
        assertNull(map.getCell(new Position(0, -1)));
    }

    @Test
    void testGetCellReturnsNullForOutOfBoundsX() {
        assertNull(map.getCell(new Position(24, 0)));
    }

    @Test
    void testGetCellReturnsNullForOutOfBoundsY() {
        assertNull(map.getCell(new Position(0, 18)));
    }

    @Test
    void testGetCellReturnsCorrectCellForValidPosition() {
        assertNotNull(map.getCell(new Position(0, 0)));
    }

    @Test
    void testGetCellByRowColReturnsCorrectCell() {
        assertNotNull(map.getCell(0, 0));
    }

    @Test
    void testIsValidMoveReturnsFalseForNegativeX() {
        assertFalse(map.isValidMove(new Position(-1, 0)));
    }

    @Test
    void testIsValidMoveReturnsFalseForNegativeY() {
        assertFalse(map.isValidMove(new Position(0, -1)));
    }

    @Test
    void testIsValidMoveReturnsFalseForOutOfBoundsY() {
        assertFalse(map.isValidMove(new Position(0, 18)));
    }

    @Test
    void testIsValidMoveReturnsFalseForHedgeCell() {
        assertFalse(map.isValidMove(new Position(0, 0)));
    }

    @Test
    void testIsValidMoveReturnsTrueForEmptyCell() {
        assertTrue(map.isValidMove(new Position(2, 1)));
    }

    @Test
    void testIsValidEnemyMoveReturnsFalseForOutOfBoundsX() {
        assertFalse(map.isValidEnemyMove(new Position(24, 0)));
    }

    @Test
    void testIsValidEnemyMoveReturnsFalseForOutOfBoundsY() {
        assertFalse(map.isValidEnemyMove(new Position(0, 18)));
    }

    @Test
    void testIsValidEnemyMoveReturnsFalseForHedgeCell() {
        assertFalse(map.isValidEnemyMove(new Position(0, 0)));
    }

    @Test
    void testIsValidEnemyMoveReturnsTrueForEmptyCell() {
        assertTrue(map.isValidEnemyMove(new Position(2, 1)));
    }

    @Test
    void testRemoveEntityEmptiesCell() {
        Position pos = new Position(2, 1);
        Cell cell = map.getCell(pos);
        cell.setEntity(new Coin(pos, 1));
        assertFalse(cell.isEmpty());
        map.removeEntity(pos);
        assertTrue(cell.isEmpty());
    }

    @Test
    void testHedgeCellHasCorrectType() {
        Cell cell = map.getCell(new Position(0, 0));
        assertEquals(CellType.HEDGE, cell.getType());
    }

    @Test
    void testEmptyCellHasCorrectType() {
        Cell cell = map.getCell(new Position(2, 1));
        assertEquals(CellType.EMPTY, cell.getType());
    }

    @Test
    void testIsValidEnemyMoveReturnsFalseForChestCell() {
        assertFalse(map.isValidEnemyMove(new Position(23, 1)));
    }

    @Test
    void testIsValidMoveReturnsFalseForOutOfBoundsX() {
        assertFalse(map.isValidMove(new Position(24, 0)));
    }

    @Test
    void testIsValidMoveReturnsFalseWhenCellIsNotEmpty() {
        Position pos = new Position(2, 1);
        Cell cell = map.getCell(pos);
        cell.setEntity(new Coin(pos, 1));
        assertFalse(map.isValidMove(pos));
    }

    @Test
    void testIsValidEnemyMoveReturnsFalseForNegativeX() {
        assertFalse(map.isValidEnemyMove(new Position(-1, 0)));
    }

    @Test
    void testIsValidEnemyMoveReturnsFalseForNegativeY() {
        assertFalse(map.isValidEnemyMove(new Position(0, -1)));
    }
}
