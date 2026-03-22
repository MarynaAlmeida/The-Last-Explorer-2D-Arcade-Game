package game.ui;

import javax.swing.JPanel;
import java.awt.CardLayout;
import game.enums.GameState;
import game.main.Game;

public class LosingByHeartsTest {
    private Game game;
    private GameView gv;
    private LosingbyHearts lh;
    private JPanel container;    
    private CardLayout cardLayout;
    //setup 
    @Test 
    public void setUp(){
        game = new Game();
        container = new JPanel();
        cardLayout = new CardLayout();
        gv = new GameView(game, gv, null);
        lh = new LosingbyHearts(game, gv, null, gv);

    }
    //check if getters are the same

    @Test 
    public void checkBWidth(){
        assertEqual(80, lh.getBWidth());
    }
    @Test 
    public void checkBHeight(){
        assertEqual(40, lh.getBHeight());
    }

    @Test 
    public void checkBX(){
        assertEqual(gv.getPreferredSize().width - lh.getBWidth(), lh.getBX());
    }

    @Test 
    public void checkBY(){
        assertEqual(gv.getPreferredSize().height - lh.getBHeight(), lh.getBY());
    }

    @Test
    public void checkBoxH(){
        assertEqual(250, lh.getBoxHeight());
    }

    @Test 
    public void checkBoxW(){
        assertEqual(300, lh.getBoxWidth());
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
    public void checkClickOutside(){
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
        assertTrue(gv.gameLoop.isRunning()); //should be running
        //button pressed
        lh.isClickInsideButton(lh.getBX()+10, lh.getBY()+10);
        assertFalse(gv.gameLoop.isRunning()); //should stop
        
    }
    //did the view pop when heart are 0
    public void checkViewPopping(){
        container.add(lh, "HEARTSLOSING"); 
        cardLayout.show(container, "HEARTSLOSING");
        game.updateHearts(-6); //this makes the heart zero
        game.tick(); //checks losing condition
        gv.actionPerformed(null); //did it trigger the state being LOST nad showing heartslosing view
        assertEquals(GameState.LOST, game.getState()); //has the game state changed?
        assertTrue(lh.isVisible());
    }

    //did the button clicking trigger homepage view
    public void checkHomepage(){
        
        lh.handleMouse(lh.getBX()+10, lh.getBY()+10, cardLayout, container);
        assertFalse(lh.isVisible()); //shouldnt be visible because its built again in remake
    }


}
