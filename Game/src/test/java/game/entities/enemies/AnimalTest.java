package game.entities.enemies;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import game.enums.AnimalType;
import game.main.GameMap;
import game.main.Position;
/**
 * Unit tests for Animal class.
 *
 * @author Maryna Almeida
 * @version 1.0
 */

public class AnimalTest {
    
    @Test
    void testAnimalType() {
        GameMap testMap = new GameMap();
        testMap.loadMap("../Assets/map.txt", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Position pos = new Position(7, 9);
        Animal fox = new Animal(pos, testMap, AnimalType.FOX);
        assertEquals(AnimalType.FOX, fox.getAnimalType());
    }

    @Test
    void testAnimalUpdateMovement() {
        GameMap testMap = new GameMap();
        testMap.loadMap("../Assets/map.txt", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Position pos = new Position(7, 9);
        Animal fox = new Animal(pos, testMap, AnimalType.FOX);
        fox.updateMovement(new Position(0, 14));
    }
}
