package game.ui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import game.main.Game;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.junit.jupiter.api.BeforeEach;
/**
 * Unit and integration tests for WinningView class
 * @author Komal
 * @version 1.0
 */
public class WinningViewTest {
   
    private WinningView wv;
    private Game g;

    @BeforeEach 
    public void setUp(){
        g = new Game();
        wv = new WinningView(g);
    }

    //check the paintComponent
    @Test
    public void checkPaintComponent() {
        BufferedImage img = new BufferedImage(300, 300, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        wv.setSize(20, 20);
        assertDoesNotThrow(() -> wv.paintComponent(g2));
    }
    //is the object getting created
    @Test 
    public void isNotNull(){
        assertNotNull(wv);
    }

    //is it the same calculation
    @Test 
    public void checkHeight(){
        assertEquals(g.getMap().getHeight()*32, wv.getHeight());
    }

    @Test 
    public void checkWidth(){
        assertEquals(g.getMap().getWidth()*3, wv.getWidth());
    }

    //is it out of bounds?
    public void checkNegativeWidth(){
        assertTrue(wv.getWidth() > 0);
    }

    public void checkNegativeHeight(){
        assertTrue(wv.getHeight() > 0);
    }
}
