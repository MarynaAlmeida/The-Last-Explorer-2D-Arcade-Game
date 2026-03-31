package game.entities.enemies.rewards;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import game.main.Position;

public class ChestTest {
    @Test
    public void testInitializedChest() {
        Chest chest = new Chest(new Position(1, 2), 5, 10);

        assertFalse(chest.isCollected());
        assertEquals(10, chest.bonusScore());
        assertEquals(5, chest.sufficientCoins());

        assertFalse(chest.attemptOpening(4));
    }

    @Test
    public void testAttemptOpenings() {
        Chest chest = new Chest(new Position(1, 2), 5, 10);

        assertFalse(chest.attemptOpening(4));
        assertFalse(chest.attemptOpening(-1));
        assertFalse(chest.attemptOpening(0));

        assertTrue(chest.attemptOpening(5));
        assertFalse(chest.attemptOpening(100)); // The chest is now considered "opened" from the previous test.
        assertTrue(chest.isCollected());
    }
}
