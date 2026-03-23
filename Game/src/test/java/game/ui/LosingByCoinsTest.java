package game.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.BeforeEach;
import javax.swing.*;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import org.junit.jupiter.api.Test;

import game.main.Game;

public class LosingByCoinsTest {
    private Game game;
    private GameView gv;
    private LosingByCoins lc;

    @BeforeEach
    public void setUp() {
        game = new Game();
        gv = new GameView(game, gv, null);
        lc = new LosingByCoins(game, gv, null, gv);
    }

    @Test 
    public void checkBWidth(){
        assertEquals(80, lc.getBWidth());
    }
    @Test 
    public void checkBHeight(){
        assertEquals(40, lc.getBHeight());
    }

    @Test 
    public void checkBX(){
        assertEquals(gv.getPreferredSize().width - lc.getBWidth() / 2, lc.getBX());
    }

    @Test 
    public void checkBY(){
        assertEquals(gv.getPreferredSize().height - lc.getBHeight() / 2 + 100, lc.getBY());
    }

    @Test
    public void checkBoxH(){
        assertEquals(250, lc.getBoxHeight());
    }

    @Test 
    public void checkBoxW(){
        assertEquals(300, lc.getBoxWidth());
    }

    //if the click is in boundaries 
     //inside the box
     @Test 
    public void testClickInsideButton(){
        assertTrue(lc.isClickInsideButton(lc.getBX()+10, lc.getBY()+10)); //this will be inside the box and should trigger the homepage view
        assertFalse(lc.isClickInsideButton(10, 10)); //this will be inside the box and should trigger the homepage view
        assertTrue(lc.isClickInsideButton(lc.getBX(), lc.getBY())); //this will be inside the box and should trigger the homepage view
        
        assertFalse(lc.isClickInsideButton(1000, 1000));
        assertFalse(lc.isClickInsideButton(0, 0));

        assertTrue(lc.isClickInsideButton(lc.getBX() + lc.getBWidth(), lc.getBY() + lc.getBHeight()));

        assertFalse(lc.isClickInsideButton(lc.getBX(), lc.getBY() + lc.getBHeight() + 1));
        assertFalse(lc.isClickInsideButton(lc.getBX(), lc.getBY() - 1));

        assertFalse(lc.isClickInsideButton(lc.getBX() + lc.getBWidth() + 1, lc.getBY()));
        assertFalse(lc.isClickInsideButton(lc.getBX() - 1, lc.getBY()));
    }

    @Test
    public void testPaintComponent() {
        Game game = new Game();

        GameView gv = new GameView(game, null, null) {
            @Override
            public void paintComponent(Graphics g) {}
        };

        LosingByCoins lc = new LosingByCoins(game, gv, null, null);

        BufferedImage img = new BufferedImage(300, 300, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();

        lc.setSize(300, 300);

        assertDoesNotThrow(() -> lc.paintComponent(g2));
    }
}