package game.main;

import game.main.Game;
import game.ui.GameView;
import java.awt.event.*;

public class GameInput implements KeyListener{
    private Game game;
    private GameView gameView;

    public GameInput(Game game, GameView gameView){
        this.game = game;
        this.gameView = gameView;
    }

    @Override
    public void keyPressed(KeyEvent k){
        if (gameView.getTutorialStatus()) {
            if (k.getKeyCode() == KeyEvent.VK_Q) {
                gameView.setTutorialState(false);
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