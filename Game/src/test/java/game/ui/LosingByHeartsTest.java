package game.ui;

import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import game.enums.GameState;
import game.main.Game;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
/**
 * Unit and integration tests for LosingByHearts class
 * @author Komal
 * @version 1.0
 */
public class LosingByHeartsTest {
    private Game game;
    private GameView gv;
    private LosingbyHearts lh;
    private JPanel container;    
    private CardLayout cardLayout;
    //setup 
    @BeforeEach
    public void setUp(){
        game = new Game();
        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);
        
        gv = new GameView(game, container, cardLayout);
        lh = new LosingbyHearts(game, gv, cardLayout, container);

    }

    //check the paintComponent
    @Test
    public void checkPaintComponent() {
        game = new Game();

        gv = new GameView(game, null, null) {
            @Override
            public void paintComponent(Graphics g) {}
        };

        lh = new LosingbyHearts(game, gv, null, null);

        BufferedImage img = new BufferedImage(300, 300, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();

        lh.setSize(300, 300);

        assertDoesNotThrow(() -> lh.paintComponent(g2));
    }
    //check if getters are the same

    @Test 
    public void checkBWidth(){
        assertEquals(80, lh.getButtonWidth());
    }
    @Test 
    public void checkBHeight(){
        assertEquals(40, lh.getButtonHeight());
    }

    @Test 
    public void checkBX(){
        assertEquals((gv.getPreferredSize().width - lh.getButtonWidth())/2, lh.getButtonX());
    }

    @Test 
    public void checkBY(){
        assertEquals(((gv.getPreferredSize().height - lh.getButtonHeight())/2) + 100, lh.getButtonY());
    }

    @Test
    public void checkBoxH(){
        assertEquals(250, lh.getBoxHeight());
    }

    @Test 
    public void checkBoxW(){
        assertEquals(300, lh.getBoxWidth());
    }

    //if the click is in boundaries 
     //inside the box
     @Test 
    public void checkClickInside(){
        assertTrue(lh.isClickInsideButton(lh.getButtonX()+10, lh.getButtonY()+10)); //this will be inside the box and should trigger the homepage view
    }
    //outside the box
    @Test 
    public void checkClickOutside() {
        assertFalse(lh.isClickInsideButton(10, 10)); //this will be inside the box and should trigger the homepage view
    }
   
    //right on the edge
    @Test 
    public void checkClickOutside1(){
        assertTrue(lh.isClickInsideButton(lh.getButtonX(), lh.getButtonY())); //this will be inside the box and should trigger the homepage view
    }
    //edge cases

    //integration tests
    //is the game being remade 
    
    @Test 
    public void checkLosingHeartsView(){
        assertNotNull(lh);
    }

    @Test 
    //did it loop stop?
    public void checkLoop(){
        assertTrue(gv.getTimer().isRunning()); //should be running
        //button pressed
        lh.handleMouse(lh.getButtonX()+10, lh.getButtonY()+10, cardLayout, container);
        assertFalse(gv.getTimer().isRunning()); //should stop
        
    }

    @Test 
    //when hearts are zero is the game state now lost?
    public void checkViewPopping(){
        game.updateHearts(-6);
        game.tick();
        assertEquals(GameState.LOST, game.getState()); 
    }

    @Test
    public void checkClickXTooSmall(){
        assertFalse(lh.isClickInsideButton(lh.getButtonX()-1, lh.getButtonY()+10));
    }

    @Test
    public void checkClickXTooLarge(){
        assertFalse(lh.isClickInsideButton(lh.getButtonX()+lh.getButtonWidth()+1, lh.getButtonY()+10));
    }

    @Test
    public void checkClickYTooSmall(){
        assertFalse(lh.isClickInsideButton(lh.getButtonX()+10, lh.getButtonY()-1));
    }

    @Test
    public void checkClickYTooLarge(){
        assertFalse(lh.isClickInsideButton(lh.getButtonX()+10, lh.getButtonY()+lh.getButtonHeight()+1));
    }

    @Test
    public void checkHandleMouseOutside(){
        assertTrue(gv.getTimer().isRunning());

        lh.handleMouse(0, 0, cardLayout, container);

        assertTrue(gv.getTimer().isRunning());
    }
}
