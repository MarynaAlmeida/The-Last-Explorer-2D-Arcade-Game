package game.entities.enemies.rewards;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import game.main.Position;

public class CoinTest {

    @Test
    public void testInitializedCoin() {
        Coin coin = new Coin(new Position(10, 15), 1);

        assertEquals(1, coin.getValue());
        assertFalse(coin.isCollected());
    }

    @Test
    public void testCollectedCoin() {
        Coin coin = new Coin(new Position(10, 15), 2);

        coin.collect();

        assertTrue(coin.isCollected());
    }

    @Test
    public void testSetUncollected() {
        Coin coin = new Coin(new Position(10, 15), 2);

        coin.collect();
        assertTrue(coin.isCollected());
        coin.setUncollected();
        assertFalse(coin.isCollected());
    }
}