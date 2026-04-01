package game.main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * Unit tests for the Position class.
 *
 * @author Jayden Pai
 * @version 2.0
 */
public class PositionTest {
    @Test
    void testGetXReturnsCorrectValue() {
        Position p = new Position(3, 5);
        assertEquals(3, p.getX());
    }

    @Test
    void testGetYReturnsCorrectValue() {
        Position p = new Position(3, 5);
        assertEquals(5, p.getY());
    }

    @Test
    void testNegativeCoordinatesAreStoredCorrectly() {
        Position p = new Position(-4, -7);
        assertEquals(-4, p.getX());
        assertEquals(-7, p.getY());
    }

    @Test
    void testEqualPositionsAreEqual() {
        Position p1 = new Position(2, 3);
        Position p2 = new Position(2, 3);
        assertEquals(p1, p2);
    }

    @Test
    void testPositionNotEqualToNull() {
        Position p = new Position(2, 3);
        assertNotEquals(p, null);
    }

    @Test
    void testPositionsDifferentXAreNotEqual() {
        Position p1 = new Position(1, 3);
        Position p2 = new Position(2, 3);
        assertNotEquals(p1, p2);
    }

    @Test
    void testPositionsDifferentYAreNotEqual() {
        Position p1 = new Position(2, 3);
        Position p2 = new Position(2, 4);
        assertNotEquals(p1, p2);
    }

    @Test
    void testEqualPositionsHaveSameHashCode() {
        Position p1 = new Position(2, 3);
        Position p2 = new Position(2, 3);
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    void testDifferentPositionsHaveDifferentHashCode() {
        Position p1 = new Position(2, 3);
        Position p2 = new Position(3, 2);
        assertNotEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    void testDistanceToSamePositionIsZero() {
        Position p = new Position(3, 4);
        assertEquals(0.0, p.distanceTo(p));
    }

    @Test
    void testDistanceToKnownPositionIsCorrect() {
        Position p1 = new Position(0, 0);
        Position p2 = new Position(3, 4);
        assertEquals(5.0, p1.distanceTo(p2), 0.0001);
    }

    @Test
    void testDistanceIsSymmetric() {
        Position p1 = new Position(1, 2);
        Position p2 = new Position(4, 6);
        assertEquals(p1.distanceTo(p2), p2.distanceTo(p1), 0.0001);
    }

    @Test
    void testDistanceWithNegativeCoordinates() {
        Position p1 = new Position(-3, -4);
        Position p2 = new Position(0, 0);
        assertEquals(5.0, p1.distanceTo(p2), 0.0001);
    }

    @Test
    void testPositionNotEqualToDifferentType() {
        Position p = new Position(2, 3);
        assertNotEquals(p, "not a position");
    }
}
