package game.entities.enemies;
import game.enums.Direction;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import game.main.GameMap;
import game.main.Position;
import org.junit.jupiter.api.Test;

public class MovingEnemyTest {
    
    @Test
    void testPixelConvertion() {
        GameMap testMap = new GameMap();
        Position defaultPos = new Position(7, 9);
        MovingEnemy enemy = new MovingEnemy(defaultPos, testMap);
        assertEquals(defaultPos.getX() * 32, enemy.getPixelX());
        assertEquals(defaultPos.getY() * 32, enemy.getPixelY());
    }

    @Test
    void testEnemyMovement_validMove() {
        GameMap testMap = new GameMap();
        testMap.loadMap("../Assets/map.txt", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Position defaultPos = new Position(7, 9);
        MovingEnemy enemy = new MovingEnemy(defaultPos, testMap);
        enemy.move();
        enemy.move();
        enemy.move();
        enemy.move();
        assertNotEquals(defaultPos, enemy.getPosition());
    }

    @Test
    void testEnemyMovement_notValidMove() {
        GameMap testMap = new GameMap();
        testMap.loadMap("../Assets/map.txt", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Position defaultPos = new Position(8, 9);
        MovingEnemy enemy = new MovingEnemy(defaultPos, testMap);
        enemy.move();
        enemy.move();
        enemy.move();
        enemy.move();
        assertEquals(defaultPos, enemy.getPosition());
        assertEquals(0, enemy.getVelocityX());
        assertEquals(0, enemy.getVelocityY());
    }

    @Test
    void testUpdateDirection_UP() {
        GameMap testMap = new GameMap();
        testMap.loadMap("../Assets/map.txt", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Position defaultPos = new Position(7, 9);
        MovingEnemy enemy = new MovingEnemy(defaultPos, testMap);
        enemy.updateDirection(Direction.UP);
        assertEquals(0, enemy.getVelocityX());
        assertEquals(-8, enemy.getVelocityY());
    }

    @Test
    void testUpdateDirection_DOWN() {
        GameMap testMap = new GameMap();
        testMap.loadMap("../Assets/map.txt", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Position defaultPos = new Position(7, 9);
        MovingEnemy enemy = new MovingEnemy(defaultPos, testMap);
        enemy.updateDirection(Direction.DOWN);
        assertEquals(0, enemy.getVelocityX());
        assertEquals(8, enemy.getVelocityY());
    }

    @Test
    void testUpdateDirection_LEFT() {
        GameMap testMap = new GameMap();
        testMap.loadMap("../Assets/map.txt", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Position defaultPos = new Position(7, 9);
        MovingEnemy enemy = new MovingEnemy(defaultPos, testMap);
        enemy.updateDirection(Direction.LEFT);
        assertEquals(-8, enemy.getVelocityX());
        assertEquals(0, enemy.getVelocityY());
    }

    @Test
    void testUpdateDirection_RIGHT() {
        GameMap testMap = new GameMap();
        testMap.loadMap("../Assets/map.txt", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Position defaultPos = new Position(7, 9);
        MovingEnemy enemy = new MovingEnemy(defaultPos, testMap);
        enemy.updateDirection(Direction.RIGHT);
        assertEquals(8, enemy.getVelocityX());
        assertEquals(0, enemy.getVelocityY());
    }

    @Test
    void testUpdateMovement() {
        GameMap testMap = new GameMap();
        testMap.loadMap("../Assets/map.txt", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Position defaultPos = new Position(8, 9);
        MovingEnemy enemy = new MovingEnemy(defaultPos, testMap);
        enemy.updateMovement(new Position(14, 0));
        enemy.updateMovement(new Position(14, 0));
        enemy.updateMovement(new Position(14, 0));
        enemy.updateMovement(new Position(14, 0));
        assertFalse(enemy.getVelocityX() == 0 && enemy.getVelocityY()== 0);
    }

    @Test
    void testOppositeDirection_UP() {
        GameMap testMap = new GameMap();
        testMap.loadMap("../Assets/map.txt", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Position defaultPos = new Position(7, 9);
        MovingEnemy enemy = new MovingEnemy(defaultPos, testMap);
        assertEquals(Direction.DOWN, enemy.getOpposite(Direction.UP));
    }

    @Test
    void testOppositeDirection_DOWN() {
        GameMap testMap = new GameMap();
        testMap.loadMap("../Assets/map.txt", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Position defaultPos = new Position(7, 9);
        MovingEnemy enemy = new MovingEnemy(defaultPos, testMap);
        assertEquals(Direction.UP, enemy.getOpposite(Direction.DOWN));
    }

    @Test
    void testOppositeDirection_LEFT() {
        GameMap testMap = new GameMap();
        testMap.loadMap("../Assets/map.txt", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Position defaultPos = new Position(7, 9);
        MovingEnemy enemy = new MovingEnemy(defaultPos, testMap);
        assertEquals(Direction.RIGHT, enemy.getOpposite(Direction.LEFT));
    }

    @Test
    void testOppositeDirection_RIGHT() {
        GameMap testMap = new GameMap();
        testMap.loadMap("../Assets/map.txt", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Position defaultPos = new Position(7, 9);
        MovingEnemy enemy = new MovingEnemy(defaultPos, testMap);
        assertEquals(Direction.LEFT, enemy.getOpposite(Direction.RIGHT));
    }
}
