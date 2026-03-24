package game.main;
import game.entities.Entity;
import game.enums.CellType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Cell class.
 *
 * @author Harman Bains
 * @version 1.0
 */
public class CellTest {

    @Test
    void testSetsAndGetsPosition() {
        Position origin = new Position(0, 0);
        Cell cell = new Cell(origin, CellType.START);

        Position setterPosition = new Position(3, 4);
        cell.setPosition(setterPosition);
        assertEquals(setterPosition, cell.getPosition(), "Position must match the arguments");
    }

    @Test
    void testCellTypeGetter(){
        Position oneStep = new Position(1, 0);
        Cell cell = new Cell(oneStep, CellType.EMPTY);
        assertEquals(CellType.EMPTY, cell.getType(), "Type should coincide with the argument");
    }

    @Test
    void testIsTraversableIsTrueForTraversableCells() {
        Cell cell = new Cell(new Position(0, 0), CellType.EMPTY);
        assertTrue(cell.isTraversable(), "EMPTY cell is traversable");

        cell = new Cell(new Position(0, 0), CellType.START);
        assertTrue(cell.isTraversable(), "START cell is traversable");

        cell = new Cell(new Position(0, 0), CellType.END);
        assertTrue(cell.isTraversable(), "END cell is traversable");
    }

    @Test
    void testIsTraversableIsFalseForNonTraversableCells() {
        Cell cell = new Cell(new Position(0, 0), CellType.HEDGE);
        assertFalse(cell.isTraversable(), "HEDGE cell is not traversable");

        cell = new Cell(new Position(0, 0), CellType.TREE);
        assertFalse(cell.isTraversable(), "TREE cell is not traversable");
    }

    @Test
    void testIsEmptyReturnsTrueWhenNoEntity() {
        Cell cell = new Cell(new Position(0, 0), CellType.EMPTY);
        assertTrue(cell.isEmpty(), "New cell should be empty");

        cell.setEntity(null);
        assertTrue(cell.isEmpty(), "After set entity null case it results in empty");
    }

    @Test
    void testGetTypeReturnsConstructorValue() {
        Cell cell1 = new Cell(new Position(0, 0), CellType.HEDGE);
        assertEquals(CellType.HEDGE, cell1.getType());

        Cell cell2 = new Cell(new Position(0, 0), CellType.START);
        assertEquals(CellType.START, cell2.getType());
    }

    @Test
    void testSetEntityAndGetEntity() {
        Cell cell = new Cell(new Position(0, 0), CellType.EMPTY);

        Entity dummy = new Entity(new Position(0, 0)) {};
        cell.setEntity(dummy);

        assertSame(dummy, cell.getEntity(), "getEntity should return what was set");
    }

    @Test
    void testRemoveEntityReturnsAndClearsEntity() {
        Cell cell = new Cell(new Position(0, 0), CellType.EMPTY);

        Entity dummy = new Entity(new Position(0, 0)){};
        cell.setEntity(dummy);

        Entity removed = cell.removeEntity();

        assertSame(dummy, removed, "removeEntity should return what was stored");
        assertNull(cell.getEntity(), "Entity should be null after removal");
        assertTrue(cell.isEmpty(), "Cell should be empty after removeEntity");
    }

    @Test
    void testRemoveEntityOnEmptyCellReturnsNull() {
        Cell cell = new Cell(new Position(0, 0), CellType.EMPTY);
        assertNull(cell.getEntity());
        assertTrue(cell.isEmpty());

        Entity removed = cell.removeEntity();

        assertNull(removed, "remove Entity being on an empty cell should result null");
        assertTrue(cell.isEmpty(), "Cell still empty");
    }

    @Test
    void testIsEmptyReturnsFalseWhenEntityExists() {
        Position pos = new Position(0, 0);
        Cell cell = new Cell(pos, CellType.EMPTY);

        Entity entity = new Entity(new Position(0, 0)) {};
        cell.setEntity(entity);

        assertFalse(cell.isEmpty(), "Cell should not be empty when entity is set");
    }

}

