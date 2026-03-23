package game.entities.enemies.rewards;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import game.main.Position;

public class ShieldTest {
    @Test
    public void testInitializedShield() {
        Shield shield = new Shield(new Position(1, 2), 10);

        assertEquals(0, shield.getValue());
        assertEquals(10, shield.getEffectDuration());
        assertFalse(shield.isCollected());
        assertFalse(shield.isSpawned());
        assertTrue(shield.getTimeTillNextSpawn() >= 60 && shield.getTimeTillNextSpawn() <= 200);
    }

    @Test
    public void testSpawn() {
        Shield shield = new Shield(new Position(1, 2), 10);

        shield.spawn();

        assertTrue(shield.isSpawned());
        assertFalse(shield.isCollected());

        assertTrue(shield.getTimeRemaining() >= 100 && shield.getTimeRemaining() <= 180);
    }

    @Test
    public void testDespawn() {
        Shield shield = new Shield(new Position(1, 2), 10);

        shield.spawn();
        shield.despawn();

        assertFalse(shield.isSpawned());
        assertEquals(shield.getTimeRemaining(), 0);
        assertTrue(shield.getTimeTillNextSpawn() >= 60 && shield.getTimeTillNextSpawn() <= 200);
    }

    @Test
    public void testCollect() {
        Shield shield = new Shield(new Position(1, 2), 10);

        shield.spawn();
        shield.collect();

        assertFalse(shield.isSpawned());
        assertTrue(shield.isCollected());
    }

    @Test
    public void testUpdateOnBoardWhenSpawned() {
        Shield shield = new Shield(new Position(1, 2), 10);

        
        shield.spawn();
        int previousTime = shield.getTimeRemaining();

        shield.updateOnBoard();
        
        assertEquals(previousTime, shield.getTimeRemaining() + 1);

        for (;;) {
            if (shield.getTimeRemaining() <= 0) {
                break;
            } else {
                shield.updateOnBoard();
            }
        }

        shield.updateOnBoard();
        testDespawn();
    }

    @Test
    public void testUpdateOnBoardWhenDespawned() {
        Shield shield = new Shield(new Position(1, 2), 10);

        int previousTime = shield.getTimeTillNextSpawn();

        shield.updateOnBoard();

        assertEquals(previousTime, shield.getTimeTillNextSpawn() + 1);

        for (;;) {
            if (shield.getTimeTillNextSpawn() <= 0) {
                break;
            } else {
                shield.updateOnBoard();
            }
        }

        shield.updateOnBoard();
        testSpawn();
    }
}
