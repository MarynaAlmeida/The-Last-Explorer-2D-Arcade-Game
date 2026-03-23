package game.ui;
import java.awt.CardLayout;
import javax.swing.JPanel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import game.main.Game;
import org.junit.jupiter.api.BeforeEach;

public class GameViewTest {
    private GameView gameV;
    private Game game;

    @BeforeEach 
    public void setUp(){
        game = new Game();
        JPanel container = new JPanel();
        CardLayout cardLayout = new CardLayout();
        gameV= new GameView(game, container, cardLayout);
    }

    @Test 
    public void checkgetTutorial(){
        assertTrue(gameV.getTutorialStatus());
    }   

    @Test 
    public void checksetTutorial(){
        gameV.setTutorialState(false);
        assertFalse(gameV.getTutorialStatus());
    }

    @Test 
    public void checksetToggled(){
        gameV.toggleMap(); //sets it to true
        assertTrue(gameV.mapToggled());
    }

    @Test 
    public void checkgetToggled(){
        assertFalse(gameV.mapToggled()); //bc constructor sets to false by default
    }

    @Test 
    public void doubleToggled(){
        gameV.toggleMap(); //true
        gameV.toggleMap(); //false now
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
