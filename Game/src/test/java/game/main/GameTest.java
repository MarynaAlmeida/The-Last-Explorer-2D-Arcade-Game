package game.main;

import game.entities.enemies.BearTrap;
import game.entities.enemies.Thorns;
import game.entities.enemies.rewards.Coin;
import game.entities.enemies.Pirate;
import game.entities.enemies.rewards.Chest;
import game.entities.enemies.rewards.Shield;
import game.enums.PirateType;
import game.enums.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JPanel;
import java.awt.event.KeyEvent;

/**
 * Integration tests for the Game class.
 *
 * @author Jayden Pai
 * @version 4.0
 */
public class GameTest {
    private Game game;

    @BeforeEach
    void refreshSetup() {
        game = new Game();
        game.startGame();
    }

    private KeyEvent makeKey(int keyCode, char keyChar) {
        return new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, keyCode, keyChar);
    }

    @Test
    void testInitialHeartsIsSix() {
        assertEquals(6, game.getHearts());
    }

    @Test
    void testInitialMaxHeartsIsSix() {
        assertEquals(6, game.getMaxHearts());
    }

    @Test
    void testInitialScoreIsZero() {
        assertEquals(0, game.getScore());
    }

    @Test
    void testUpdateScoreIncrementsCorrectly() {
        game.updateScore(5);
        assertEquals(5, game.getScore());
    }

    @Test
    void testUpdateScoreDecrementsCorrectly() {
        game.updateScore(-3);
        assertEquals(-3, game.getScore());
    }

    @Test
    void testUpdateScoreAccumulatesCorrectly() {
        game.updateScore(5);
        game.updateScore(3);
        assertEquals(8, game.getScore());
    }

    @Test
    void testUpdateHeartsChangesCorrectly() {
        game.updateHearts(4);
        assertEquals(4, game.getHearts());
    }

    @Test
    void testUpdateHeartsToZero() {
        game.updateHearts(0);
        assertEquals(0, game.getHearts());
    }

    @Test
    void testSetStateChangesState() {
        game.setState(GameState.WON);
        assertEquals(GameState.WON, game.getState());
    }

    @Test
    void testLoseConditionTriggersWhenZeroHearts() {
        game.updateHearts(0);
        game.checkLoseCondition();
        assertEquals(GameState.LOST, game.getState());
    }

    @Test
    void testLoseConditionTriggersWhenScoreIsNegative() {
        game.updateScore(-1);
        game.checkLoseCondition();
        assertEquals(GameState.LOST, game.getState());
    }

    @Test
    void testLoseConditionDoesNotTriggerWithPositiveHeartsAndScore() {
        game.checkLoseCondition();
        assertEquals(GameState.PLAYING, game.getState());
    }

    @Test
    void testGetPixelXReturnsCorrectValueForExplorer() {
        // Starting position (0, 14)
        assertEquals(0, game.getPixelX(game.getExplorer()));
    }

    @Test
    void testGetPixelYReturnsCorrectValueForExplorer() {
        // Starting position (0, 14)
        assertEquals(14 * 32, game.getPixelY(game.getExplorer()));
    }

    @Test
    void testCollisionDetectedWhenEntitiesOverlap() {
        game.getChest().setPosition(new Position(0, 14));
        assertTrue(game.checkCollision(game.getExplorer(), game.getChest()));
    }

    @Test
    void testGetMapReturnsNonNull() {
        assertNotNull(game.getMap());
    }

    @Test
    void testGetExplorerReturnsNonNull() {
        assertNotNull(game.getExplorer());
    }

    @Test
    void testGetExplorerStartsAtCorrectPosition() {
        assertEquals(new Position(0, 14), game.getExplorer().getPosition());
    }

    @Test
    void testPressingWMovesExplorerUp() {
        game.getExplorer().setPosition(new Position(3, 11));
        game.input(makeKey(KeyEvent.VK_W, 'w'));
        assertEquals(new Position(3, 10), game.getExplorer().getPosition());
    }

    @Test
    void testPressingSMovesExplorerDown() {
        game.getExplorer().setPosition(new Position(3, 11));
        game.input(makeKey(KeyEvent.VK_S, 's'));
        assertEquals(new Position(3, 12), game.getExplorer().getPosition());
    }

    @Test
    void testPressingAMovesExplorerLeft() {
        game.getExplorer().setPosition(new Position(3, 11));
        game.input(makeKey(KeyEvent.VK_A, 'a'));
        assertEquals(new Position(2, 11), game.getExplorer().getPosition());
    }

    @Test
    void testPressingDMovesExplorerRight() {
        game.getExplorer().setPosition(new Position(3, 11));
        game.input(makeKey(KeyEvent.VK_D, 'd'));
        assertEquals(new Position(4, 11), game.getExplorer().getPosition());
    }

    @Test
    void testMovingIntoWallDoesNotMoveExplorer() {
        game.getExplorer().setPosition(new Position(1, 11));
        game.input(makeKey(KeyEvent.VK_A, 'a'));
        assertEquals(new Position(1, 11), game.getExplorer().getPosition());
    }

    @Test
    void testGetMovingEnemiesReturnsNonNull() {
        assertNotNull(game.getMovingEnemies());
    }

    @Test
    void testGetRewardsReturnsNonNull() {
        assertNotNull(game.getRewards());
    }

    @Test
    void testGetStaticEnemiesReturnsNonNull() {
        assertNotNull(game.getStaticEnemies());
    }

    @Test
    void testStartGameSetsStatePlaying() {
        // startGame called in @BeforeEach
        assertEquals(GameState.PLAYING, game.getState());
    }

    @Test
    void testTickRecognizesLoseConditionWhenHeartsAreZero() {
        game.updateHearts(0);
        game.tick();
        assertEquals(GameState.LOST, game.getState());
    }

    @Test
    void testTickCollectsCoinWhenExplorerOnCoin() {
        game.getRewards().clear();
        Position coinPos = new Position(3, 11);
        Coin coin = new Coin(coinPos, 5);
        game.getRewards().add(coin);
        game.getExplorer().setPosition(coinPos);
        game.tick();
        assertTrue(coin.isCollected());
        assertEquals(5, game.getScore());
    }

    @Test
    void testTickDeductsScoreWhenExplorerOnThorns() {
        Position thornPos = new Position(3, 11);
        Thorns thorn = new Thorns(thornPos, 1);
        game.getStaticEnemies().add(thorn);
        game.getExplorer().setPosition(thornPos);
        game.tick();
        assertEquals(-1, game.getScore());
    }

    @Test
    void testTickDeductsScoreWhenExplorerOnBearTrap() {
        Position trapPos = new Position(3, 11);
        BearTrap trap = new BearTrap(trapPos, 4);
        game.getStaticEnemies().add(trap);
        game.getExplorer().setPosition(trapPos);
        game.tick();
        assertEquals(-3, game.getScore());
    }

    @Test
    void testTickShieldBlocksStaticEnemyDamage() {
        Position thornPos = new Position(3, 11);
        Thorns thorn = new Thorns(thornPos, 1);
        game.getStaticEnemies().add(thorn);
        game.getExplorer().setPosition(thornPos);
        game.getExplorer().activateShield(10);
        game.tick();
        assertEquals(0, game.getScore());
        assertFalse(game.getExplorer().isShieldActive());
    }

    @Test
    void testTickInvincibilityCounterIncrementsWhenInvincible() {
        game.invincible = true;
        game.invincibleCounter = 0;
        game.tick();
        assertTrue(game.invincibleCounter > 0);
    }

    @Test
    void testTickResetsInvincibilityAfterThreshold() {
        game.invincible = true;
        game.invincibleCounter = 21;
        game.tick();
        assertFalse(game.invincible);
        assertEquals(0, game.invincibleCounter);
    }

    @Test
    void testTickWinConditionTriggersWhenAllCoinsCollectedAndOnChest() {
        game.getRewards().clear();
        Position chestPos = new Position(4, 11);
        Chest chest = new Chest(chestPos, 0, 10);
        game.getRewards().add(chest);
        game.getExplorer().setPosition(chestPos);

        game.tick();
        assertEquals(GameState.WON, game.getState());
    }

    @Test
    void testTickWinConditionDoesNotTriggerWithUncollectedCoins() {
        game.getRewards().clear();
        Position coinPos = new Position(3, 11);
        Coin coin = new Coin(coinPos, 1);
        game.getRewards().add(coin);

        game.tick();
        assertEquals(GameState.PLAYING, game.getState());
    }

    @Test
    void testTickChestOpensWhenSufficientCoins() {
        game.getRewards().clear();
        Position chestPos = new Position(3, 11);
        Chest chest = new Chest(chestPos, 0, 10);
        game.getRewards().add(chest);
        game.getExplorer().setPosition(chestPos);

        game.tick();
        assertTrue(chest.isCollected());
        assertEquals(10, game.getScore());
    }

    @Test
    void testTickChestDoesNotOpenWhenInsufficientCoins() {
        game.getRewards().clear();
        Position chestPos = new Position(3, 11);
        Chest chest = new Chest(chestPos, 10, 5);
        game.getRewards().add(chest);
        game.getExplorer().setPosition(chestPos);

        game.tick();
        assertFalse(chest.isCollected());
        assertEquals(0, game.getScore());
    }

    @Test
    void testGetPixelXForMovingEnemy() {
        Position pos = new Position(3, 11);
        Pirate pirate = new Pirate(pos, game.getMap(), PirateType.PIRATE1);
        assertEquals(3 * 32, game.getPixelX(pirate));
    }

    @Test
    void testGetPixelYForMovingEnemy() {
        Position pos = new Position(3, 11);
        Pirate pirate = new Pirate(pos, game.getMap(), PirateType.PIRATE1);
        assertEquals(11 * 32, game.getPixelY(pirate));
    }

    @Test
    void testTickDoesNotCollectUnspawnedShield() {
        Position shieldPos = new Position(3, 11);
        Shield shield = new Shield(shieldPos, 10);
        // Shield not spawned by default
        game.getRewards().add(shield);
        game.getExplorer().setPosition(shieldPos);
        game.tick();
        assertFalse(shield.isCollected());
    }

    @Test
    void testTickCollectsSpawnedShield() {
        Position shieldPos = new Position(3, 11);
        Shield shield = new Shield(shieldPos, 10);
        shield.spawn();
        game.getRewards().add(shield);
        game.getExplorer().setPosition(shieldPos);
        game.tick();
        assertTrue(shield.isCollected());
        assertTrue(game.getExplorer().isShieldActive());
    }

    @Test
    void testTickReducesHeartsWhenMovingEnemyCollidesWithExplorer() {
        Position pos = new Position(3, 11);
        game.getExplorer().setPosition(pos);
        Pirate pirate = new Pirate(pos, game.getMap(), PirateType.PIRATE1);
        game.getMovingEnemies().add(pirate);
        int initialHearts = game.getHearts();
        game.tick();
        assertTrue(game.getHearts() < initialHearts);
    }

    @Test
    void testCheckWinConditionDoesNotTriggerWhenOnlyAllCoinsCollected() {
        game.getRewards().clear();
        Coin coin = new Coin(new Position(3, 11), 1);
        coin.collect();
        game.getRewards().add(coin);
        game.checkWinCondition();
        assertEquals(GameState.PLAYING, game.getState());
    }

    @Test
    void testInputWithInvalidKeyDoesNotMoveExplorer() {
        Position initial = game.getExplorer().getPosition();
        game.input(makeKey(KeyEvent.VK_E, 'e'));
        assertEquals(initial, game.getExplorer().getPosition());
    }

    @Test
    void testTickDoesNotDeductScoreWhenAlreadyOnStaticEnemy() {
        Position thornPos = new Position(3, 11);
        Thorns thorn = new Thorns(thornPos, 1);
        game.getStaticEnemies().add(thorn);
        game.getExplorer().setPosition(thornPos);
        game.tick(); // first tick score goes to -1
        game.tick(); // second tick already on thorns so no further deduction
        assertEquals(-1, game.getScore());
    }

    @Test
    void testTickDoesNotReduceHeartsWhenInvincible() {
        Position pos = new Position(3, 11);
        game.getExplorer().setPosition(pos);
        Pirate pirate = new Pirate(pos, game.getMap(), PirateType.PIRATE1);
        game.getMovingEnemies().add(pirate);
        game.invincible = true;
        int initialHearts = game.getHearts();
        game.tick();
        assertEquals(initialHearts, game.getHearts());
    }

    @Test
    void testTickDoesNotReduceHeartsWhenShieldActiveOnEnemyCollision() {
        Position pos = new Position(3, 11);
        game.getExplorer().setPosition(pos);
        Pirate pirate = new Pirate(pos, game.getMap(), PirateType.PIRATE1);
        game.getMovingEnemies().add(pirate);
        game.getExplorer().activateShield(10);
        int initialHearts = game.getHearts();
        game.tick();
        assertEquals(initialHearts, game.getHearts());
    }

    @Test
    void testTickDoesNotCollectAlreadyCollectedCoin() {
        Position coinPos = new Position(3, 11);
        Coin coin = new Coin(coinPos, 5);
        coin.collect(); // pre collect coin
        game.getRewards().add(coin);
        game.getExplorer().setPosition(coinPos);
        game.tick();
        assertEquals(0, game.getScore());
    }

    @Test
    void testTickDoesNotReopenAlreadyCollectedChest() {
        game.getRewards().clear();
        Position chestPos = new Position(3, 11);
        Chest chest = new Chest(chestPos, 0, 10);
        chest.attemptOpening(0); // pre open chest
        game.getRewards().add(chest);
        game.getExplorer().setPosition(chestPos);
        game.tick();
        assertEquals(0, game.getScore());
    }

    @Test
    void testCheckWinConditionDoesNotTriggerWhenNotOnChest() {
        game.getRewards().clear();
        Chest chest = new Chest(new Position(3, 11), 0, 10);
        game.getRewards().add(chest);
        game.checkWinCondition();
        assertEquals(GameState.PLAYING, game.getState());
    }

    @Test
    void testNoCollisionWhenEntityIsRight() {
        game.getExplorer().setPosition(new Position(0, 14));
        game.getChest().setPosition(new Position(2, 14));
        assertFalse(game.checkCollision(game.getExplorer(), game.getChest()));
    }

    @Test
    void testNoCollisionWhenEntityIsLeft() {
        game.getExplorer().setPosition(new Position(4, 14));
        game.getChest().setPosition(new Position(2, 14));
        assertFalse(game.checkCollision(game.getExplorer(), game.getChest()));
    }

    @Test
    void testNoCollisionWhenEntityIsBelow() {
        game.getExplorer().setPosition(new Position(0, 14));
        game.getChest().setPosition(new Position(0, 16));
        assertFalse(game.checkCollision(game.getExplorer(), game.getChest()));
    }

    @Test
    void testNoCollisionWhenEntityIsAbove() {
        game.getExplorer().setPosition(new Position(0, 14));
        game.getChest().setPosition(new Position(0, 12));
        assertFalse(game.checkCollision(game.getExplorer(), game.getChest()));
    }
}
