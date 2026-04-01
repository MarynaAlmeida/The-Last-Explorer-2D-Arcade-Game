package game.ui;

import javax.swing.JPanel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.CardLayout;

import game.main.Game;

public class GameViewTest {
    private GameView gameV;
    private Game game;

    @Test 
    public void setUp(){
        game = new Game();
        JPanel container = new JPanel();
        CardLayout cardLayout = new CardLayout();
        gameV= new GameView(game, container, cardLayout);
    }

    @Test 
    public void checkgetTutorial(){
        assertTrue(gameV.tutorialPage);
    }   

    @Test 
    public void checksetTutorial(){
        gameV.setTutorialState(false);
        assertFalse(gameV.getTutorialStatus());
    }

    @Test 
    public void checksetToggled(){
        game.toggleMap(); //sets it to true
        assertTrue(gameV.toggleMap());
    }

    @Test 
    public void checkgetToggled(){
        assertFalse(gameV.mapToggled()); //bc constructor sets to false by default
    }

    @Test 
    public void doubleToggled(){
        game.toggleMap(); //true
        game.toggleMap(); //false now
        assertFalse(gameV.mapToggled());
    }

    //check out of bounds as in negative for starting x cell

    @Test 
    public void checkStartingXcell(){
        assertTrue(gameV.getStartingXCell()>=0);
    }

    @Test 
    public void checkStartingYcell(){
        assertTrue(gameV.getStartingYCell()>=0);
    }

    @Test 
    public void checkStopLoop(){
        gameV.stopLoop();
    }
}
