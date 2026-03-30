package game.main;

import game.ui.GameView;
import game.ui.TutorialView;

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
    private TutorialView tutorialView;

    @BeforeEach
    void refreshSetup() {
        game = new Game();
        game.startGame();
        JPanel container = new JPanel();
        CardLayout cardLayout = new CardLayout();
        gameView = new GameView(game, container, cardLayout);
        gameView.stopLoop();
        TutorialView tutorialView = new TutorialView();
        gameInput = new GameInput(game, gameView, tutorialView);
    }

    private KeyEvent makeKey(int keyCode, char keyChar) {
        return new KeyEvent(gameView, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, keyCode, keyChar);
    }

    @Test
    void testPressingQWhileTutorialActiveDeactivatesTutorial() {
        tutorialView.setTutorialState(true);
        gameInput.keyPressed(makeKey(KeyEvent.VK_Q, 'q'));
        assertFalse(tutorialView.getTutorialStatus());
    }

    @Test
    void testPressingNonQWhileTutorialActiveDoesNotDeactivateTutorial() {
        tutorialView.setTutorialState(true);
        gameInput.keyPressed(makeKey(KeyEvent.VK_W, 'w'));
        assertTrue(tutorialView.getTutorialStatus());
    }

    @Test
    void testPressingMTogglesMap() {
        tutorialView.setTutorialState(false);
        assertFalse(gameView.mapToggled());
        gameInput.keyPressed(makeKey(KeyEvent.VK_M, 'm'));
        assertTrue(gameView.mapToggled());
    }

    @Test
    void testPressingMTwiceTogglesMapBackOff() {
        tutorialView.setTutorialState(false);
        gameInput.keyPressed(makeKey(KeyEvent.VK_M, 'm'));
        gameInput.keyPressed(makeKey(KeyEvent.VK_M, 'm'));
        assertFalse(gameView.mapToggled());
    }

    @Test
    void testPressingMovementKeyWhileMapToggledDoesNotMoveExplorer() {
        tutorialView.setTutorialState(false);
        gameView.toggleMap();
        Position before = game.getExplorer().getPosition();
        gameInput.keyPressed(makeKey(KeyEvent.VK_D, 'd'));
        assertEquals(before, game.getExplorer().getPosition());
    }

    @Test
    void testKeyReleasedDoesNothing() {
        tutorialView.setTutorialState(false);
        Position before = game.getExplorer().getPosition();
        gameInput.keyReleased(makeKey(KeyEvent.VK_D, 'd'));
        assertEquals(before, game.getExplorer().getPosition());
    }

    @Test
    void testKeyTypedDoesNothing() {
        tutorialView.setTutorialState(false);
        Position before = game.getExplorer().getPosition();
        gameInput.keyTyped(makeKey(KeyEvent.VK_D, 'd'));
        assertEquals(before, game.getExplorer().getPosition());
    }
}