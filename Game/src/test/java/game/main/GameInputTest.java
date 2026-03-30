package game.main;

import game.ui.GameView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Integration tests for the GameInput class.
 *
 * @author Jayden Pai
 * @version 1.0
 */
public class GameInputTest {
    private Game game;
    private GameView gameView;
    private GameInput gameInput;

    @BeforeEach
    void refreshSetup() {
        game = new Game();
        game.startGame();
        JPanel container = new JPanel();
        CardLayout cardLayout = new CardLayout();
        gameView = new GameView(game, container, cardLayout);
        gameView.stopLoop();
        gameInput = new GameInput(game, gameView);
    }

    private KeyEvent makeKey(int keyCode, char keyChar) {
        return new KeyEvent(gameView, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, keyCode, keyChar);
    }

    @Test
    void testPressingQWhileTutorialActiveDeactivatesTutorial() {
        gameView.setTutorialState(true);
        gameInput.keyPressed(makeKey(KeyEvent.VK_Q, 'q'));
        assertFalse(gameView.getTutorialStatus());
    }

    @Test
    void testPressingNonQWhileTutorialActiveDoesNotDeactivateTutorial() {
        gameView.setTutorialState(true);
        gameInput.keyPressed(makeKey(KeyEvent.VK_W, 'w'));
        assertTrue(gameView.getTutorialStatus());
    }

    @Test
    void testPressingMTogglesMap() {
        gameView.setTutorialState(false);
        assertFalse(gameView.mapToggled());
        gameInput.keyPressed(makeKey(KeyEvent.VK_M, 'm'));
        assertTrue(gameView.mapToggled());
    }

    @Test
    void testPressingMTwiceTogglesMapBackOff() {
        gameView.setTutorialState(false);
        gameInput.keyPressed(makeKey(KeyEvent.VK_M, 'm'));
        gameInput.keyPressed(makeKey(KeyEvent.VK_M, 'm'));
        assertFalse(gameView.mapToggled());
    }

    @Test
    void testPressingMovementKeyWhileMapToggledDoesNotMoveExplorer() {
        gameView.setTutorialState(false);
        gameView.toggleMap();
        Position before = game.getExplorer().getPosition();
        gameInput.keyPressed(makeKey(KeyEvent.VK_D, 'd'));
        assertEquals(before, game.getExplorer().getPosition());
    }

    @Test
    void testKeyReleasedDoesNothing() {
        gameView.setTutorialState(false);
        Position before = game.getExplorer().getPosition();
        gameInput.keyReleased(makeKey(KeyEvent.VK_D, 'd'));
        assertEquals(before, game.getExplorer().getPosition());
    }

    @Test
    void testKeyTypedDoesNothing() {
        gameView.setTutorialState(false);
        Position before = game.getExplorer().getPosition();
        gameInput.keyTyped(makeKey(KeyEvent.VK_D, 'd'));
        assertEquals(before, game.getExplorer().getPosition());
    }
}