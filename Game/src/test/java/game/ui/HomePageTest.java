package game.ui;
import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import game.enums.GameState;
import game.main.Game;
import org.junit.jupiter.api.BeforeEach;
/**
 * Unit and integration tests for HomePage class
 * @author Komal
 * @version 1.0
 */
public class HomePageTest {
    private GameView gameV;
    private Game game;
    private HomePage hp;
    private JPanel container;    
    private CardLayout cardLayout;

    @BeforeEach
    public void setUp(){
        game = new Game();
        game.startGame();
        
        
        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);
        gameV = new GameView(game, container, cardLayout);
        hp = new HomePage(game, container, cardLayout, gameV);
    }

    //getters
    @Test 
    public void checkWidth(){
        assertEquals(300, hp.getButtonW());
    }
    @Test 
    public void checkHeight(){
        assertEquals(100, hp.getButtonH());
    }

    @Test
    public void checkX(){
        assertEquals(((game.getMap().getWidth()*32)- hp.getButtonW())/2, hp.getButtonX());
    }

    @Test 
    public void checkY(){
        assertEquals((((game.getMap().getHeight()*32)- hp.getButtonH())/2)+150, hp.getButtonY());
    }


     //inside the box
    @Test 
    public void checkClickInside(){
        assertTrue(hp.isClickInsideButton(hp.getButtonX()+10, hp.getButtonY()+10)); //this will be inside the box and should trigger the homepage view
    }
    //outside the box
    @Test 
    public void checkClickOutside(){
        assertFalse(hp.isClickInsideButton(10, 10)); //this will be inside the box and should trigger the homepage view
    }
   
    //right on the edge
    @Test 
    public void checkClickOutside1(){
        assertTrue(hp.isClickInsideButton(hp.getButtonX(), hp.getButtonY())); //this will be inside the box and should trigger the homepage view
    }
    @Test
    public void checkPaintComponent(){
        BufferedImage img = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        hp.setSize(500, 500);
        assertDoesNotThrow(() -> hp.paintComponent(g2));
    }
    //edge cases

    //integration tests
    //is the game being remade 
    
    @Test 
    public void checkLosingHeartsView(){
        assertNotNull(hp);
    }

    @Test
    public void checkHandleMouseInside(){
        assertDoesNotThrow(() -> 
            hp.handleMouse(hp.getButtonX()+10, hp.getButtonY()+10, container, cardLayout)
        );
    }

    @Test
    public void checkHandleMouseOutside(){
        assertDoesNotThrow(() -> 
            hp.handleMouse(0, 0, container, cardLayout)
        );
    }
}
