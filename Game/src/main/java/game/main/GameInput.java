package game.main;
import java.awt.event.*;

public class GameInput implements KeyListener{
    private Game game;
    public GameInput(Game game){
        this.game = game;
    }
    @Override
    public void keyPressed(KeyEvent k){
                game.input(k);
    }
    @Override
    public void keyReleased(KeyEvent k){

    }
    @Override
    public void keyTyped(KeyEvent k){

    }
}