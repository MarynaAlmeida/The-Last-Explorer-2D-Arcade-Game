//the explorer has all the coins
package game.ui;
import game.main.*;
import javax.swing.*;
import java.awt.*;
/**
 * Handles visuals for the winning screen including background,
 * chest images and winning text
 * @author Komal Kaur
 */
public class WinningView extends JPanel{
    private Image winningBg;
    private Image chest;
    private Game game;
    private int width;
    private int height;
    /**
     * Creates the winning view panel with background and chest images
     * @param game the game used to set the dimensions
     */
    public WinningView(Game game){
        this.winningBg = new ImageIcon("../Assets/Icons/WinBackground.png").getImage();
        this.chest = new ImageIcon("../Assets/Icons/ClosedChest.png").getImage();
        this.game = game;
        this.height = game.getMap().getHeight() * 32;
        this.width = game.getMap().getWidth() * 32; //pixels
        
        this.setPreferredSize(new Dimension(width, height));
    }
    /**
     * Draws the winning background, chest images and winning text
     * @param g the graphics context for drawing
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(winningBg, 0, 0, getWidth(),getHeight(),null);
        g.drawImage(chest, 10, 10, chest.getWidth(null), chest.getHeight(null), null);
        g.drawImage(chest, getWidth() - chest.getWidth(null), getHeight() - chest.getHeight(null), chest.getWidth(null), chest.getHeight(null), null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 35));
        g.drawString("ALL COINS COLLECTED", (getWidth()/2) -200 ,250);
        g.drawString("THE TREASURE IS YOURS!", (getWidth()/2) -200 ,290); //this text comes lower than the first
    }
}