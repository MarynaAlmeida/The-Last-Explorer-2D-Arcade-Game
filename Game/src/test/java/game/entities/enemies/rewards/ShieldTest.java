package game.entities.enemies.rewards;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import game.main.Position;

public class ShieldTest {
    @Test
    public void testInitializedShield() {
        Shield shield = new Shield(new Position(1, 2), 10);

        // Checks to see the values of the shield class after it is initialized.
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

        // Checks to see if the time remaining once the shield spawns is within its expected boundaries, as set in the shield class.
        assertTrue(shield.getTimeRemaining() >= 100 && shield.getTimeRemaining() <= 180);
    }

    @Test
    public void testDespawn() {
        Shield shield = new Shield(new Position(1, 2), 10);

        shield.spawn();
        shield.despawn();

        // Checks to see if the shield is now despawned, if the time remaining on the board is set to 0, and if
        // the time until the shield spawns again is within the expected range, as set in the shield class.
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

        // Updates the shield until the remaining time on the board is 0, then breaks out of the loop to continue testing.
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

        // Updates the shield until the time until it spawns again is less than or equal to 0, then breaks out of the loop.
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
