package game.main;

import game.ui.GameView;
import game.ui.TutorialView;

import java.awt.event.*;

/**
 * Controls which keys can be pressed at certain times, and records the users inputs.
 * 
 * <p>After receiving inputs, this class then calls methods based on the key inputted.</p>
 * 
 * @author Nathaniel Chen
 * @version 1.0
 */
public class GameInput implements KeyListener{
    private Game game;
    private GameView gameView;
    private TutorialView tutorialView;

    /**
     * Initializes the {@link Game} and {@link GameView} class objects.
     * 
     * @param game the {@link Game} object that this game should work with to record/send keystrokes.
     * @param gameView the {@link GameView} object that this game should work with to update visuals based on keystrokes.
     */
    public GameInput(Game game, GameView gameView, TutorialView tutorialView){
        this.game = game;
        this.gameView = gameView;
        this.tutorialView = tutorialView;
    }

    @Override
    public void keyPressed(KeyEvent k){
        if (tutorialView.getTutorialStatus()) {
            if (k.getKeyCode() == KeyEvent.VK_Q) {
                tutorialView.setTutorialState(false);
            }
            return;
        }

        if (k.getKeyCode() == KeyEvent.VK_M) {
            gameView.toggleMap();
            gameView.repaint();
            return;
        }

        if (gameView.mapToggled()) {
            return;
        }

        game.input(k);
        gameView.repaint();
    }

    @Override
    public void keyReleased(KeyEvent k){}

    @Override
    public void keyTyped(KeyEvent k){}
}