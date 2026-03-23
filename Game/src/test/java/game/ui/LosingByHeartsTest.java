package game.ui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import game.main.Game;

public class LosingByHeartsTest {
    private Game game;
    private GameView gv;
    private LosingbyHearts lh;
    //setup 
    @Test 
    public void setUp(){
        game = new Game();
        gv = new GameView(game, gv, null);
        lh = new LosingbyHearts(game, gv, null, gv);
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
        assertEquals(gv.getPreferredSize().width - lh.getBWidth(), lh.getBX());
    }

    @Test 
    public void checkBY(){
        assertEquals(gv.getPreferredSize().height - lh.getBHeight(), lh.getBY());
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
    public void checkClickOutside() {
        assertFalse(lh.isClickInsideButton(10, 10)); //this will be inside the box and should trigger the homepage view
    }
   
    //right on the edge
    @Test 
    public void checkClickOnEdge(){
        assertFalse(lh.isClickInsideButton(lh.getBX(), lh.getBY())); //this will be inside the box and should trigger the homepage view
    }
    //edge cases
    //integration tests
    //is the game being remade 
    
    @Test 
    public void checkLosingHeartsView(){
        assertNotNull(lh);
    }

    //did it loop stop?
    public void checkLoop(){
        
    }
    //did the view pop when heart are 0
    //did the button clicking trigger homepage view



}
