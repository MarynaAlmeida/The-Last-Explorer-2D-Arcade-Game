package game.entities.enemies;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import game.enums.PirateType;
import game.main.GameMap;
import game.main.Position;

public class PirateTest {
    
    @Test
    void testPirateType() {
        GameMap testMap = new GameMap();
        testMap.loadMap("../Assets/map.txt", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Position pos = new Position(7, 9);
        Pirate pirate1 = new Pirate(pos, testMap, PirateType.PIRATE1);
        assertEquals(PirateType.PIRATE1, pirate1.getPirateType());
    }

    @Test
    void testPirateUpdateMovement() {
        GameMap testMap = new GameMap();
        testMap.loadMap("../Assets/map.txt", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Position pos = new Position(7, 9);
        Pirate pirate1 = new Pirate(pos, testMap, PirateType.PIRATE1);
        pirate1.updateMovement(new Position(0, 14));
    }
}
