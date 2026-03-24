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
        assertEquals(80, lh.getBWidth());
    }
    @Test 
    public void checkBHeight(){
        assertEquals(40, lh.getBHeight());
    }

    @Test 
    public void checkBX(){
        assertEquals((gv.getPreferredSize().width - lh.getBWidth())/2, lh.getBX());
    }

    @Test 
    public void checkBY(){
        assertEquals(((gv.getPreferredSize().height - lh.getBHeight())/2) + 100, lh.getBY());
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
        assertTrue(lh.isClickInsideButton(lh.getBX()+10, lh.getBY()+10)); //this will be inside the box and should trigger the homepage view
    }
    //outside the box
    @Test 
    public void checkClickOutside(){
        assertFalse(lh.isClickInsideButton(10, 10)); //this will be inside the box and should trigger the homepage view
    }
   
    //right on the edge
    @Test 
    public void checkClickOutside1(){
        assertTrue(lh.isClickInsideButton(lh.getBX(), lh.getBY())); //this will be inside the box and should trigger the homepage view
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
        assertTrue(gv.gameLoop.isRunning()); //should be running
        //button pressed
        lh.handleMouse(lh.getBX()+10, lh.getBY()+10, cardLayout, container);
        assertFalse(gv.gameLoop.isRunning()); //should stop
        
    }

    @Test 
    //when hearts are zero is the game state now lost?
    public void checkViewPopping(){
        game.updateHearts(-6);
        game.tick();
        assertEquals(GameState.LOST, game.getState()); 
    }

}
