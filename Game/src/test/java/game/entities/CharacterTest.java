package game.entities;
import game.entities.enemies.MovingEnemy;
import game.entities.enemies.StaticEnemy;
import game.entities.enemies.rewards.Reward;
import game.enums.Direction;
import game.main.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import game.main.GameMap;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Unit tests for the abstract Character class.
 *
 * @author Harman Bains
 * @version 1.0
 */
public class CharacterTest {

    // concrete of testing abstract class
    private static class TestCharacter extends Character {
        public TestCharacter(Position position, GameMap map) {
            super(position, map);
        }
    }

    private Character theCharacter;
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
        theCharacter = new TestCharacter(new Position(4, 4), map);
    }

    @Test
    void testCharacterMovementsandDirections(){

        Position currentPosition = theCharacter.getPosition();

        boolean goRight = theCharacter.move(Direction.RIGHT);
        assertTrue(goRight, "Move RIGHT");
        assertEquals(new Position(5, 4), theCharacter.getPosition(),
                "Position after RIGHT move");

        boolean nullCase = theCharacter.move(null);
        assertFalse(nullCase, "Direction is null");

        boolean goLeft = theCharacter.move(Direction.LEFT);
        assertTrue(goLeft, "Move LEFT");
        theCharacter.move(Direction.LEFT);
        assertEquals(currentPosition, theCharacter.getPosition(),
                "Move LEFT essentially the first state");

        theCharacter.move(Direction.UP);
        assertEquals(new Position(4, 3), theCharacter.getPosition(),
                "Position after UP move");

        theCharacter.move(Direction.DOWN);
        assertEquals(new Position(4, 4), theCharacter.getPosition(),
                "Position after DOWN move");
    }

    @Test
    void testCanMoveToFindingNullInstances() {
        assertFalse(theCharacter.canMoveTo(null),
                "position is null means canMoveto method is false");

        GameMap initialMap = theCharacter.map;
        theCharacter.map = null;
        assertFalse(theCharacter.canMoveTo(new Position(4, 4)),
                "map is null so canMoveTo method is false ");

        theCharacter.map = initialMap;
    }

    @Test
    void testCanMoveToFindingOutOfBounds() {
        assertFalse(theCharacter.canMoveTo(new Position(-2, 4)),
                "negative x means canMoveTo is false");

        assertFalse(theCharacter.canMoveTo(new Position(4, -1)),
                "negative y means canMoveTo is false");
    }

    }
