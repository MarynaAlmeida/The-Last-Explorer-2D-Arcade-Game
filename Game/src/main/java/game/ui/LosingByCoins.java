package game.ui;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.event.MouseEvent;

import java.awt.*;
import java.awt.event.MouseAdapter;

import game.main.Game;
import game.main.Main;

/**
 * Handles visuals for the losing screen by coins including background,
 * coin images and game over text
 * @author Komal Kaur, Nathaniel Chen
 * @version 1.0
 */
public class LosingByCoins extends JPanel{
    private Game g;
    private GameView gv;
    private Image coins;
    private Image backButton;
    private Image gameover;
    private int buttonX, buttonY, buttonW, buttonH, boxH, boxW;

    /**
     * creates the losingcoins panel with back button, background and game over text
     *
     * @param gv the game view used to set the dimensions
     * @param cardLayout the layout used to manage the views
     * @param container the container to store all the views 
     */
    public LosingByCoins(Game g, GameView gv, CardLayout cardLayout, JPanel container){ //so that losing view is drawn OVER the gameview
        this.g = g;
        this.gv = gv;
        this.backButton = new ImageIcon("../Assets/Icons/backbuttonCOINS.png").getImage();
        this.coins = new ImageIcon("../Assets/Icons/Coin.png").getImage();
        this.gameover = new ImageIcon("../Assets/Icons/gameoverCoins.png").getImage();
        this.setPreferredSize(gv.getPreferredSize());
        this.setLayout(null);
        this.boxW = 300; //need test run
        this.boxH = 250;

        buttonW = 80;
        buttonH = 40;
        buttonX = (gv.getPreferredSize().width - buttonW) /2 ;
        buttonY = ((gv.getPreferredSize().height-buttonH) /2) + 100;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                if(e.getX() >= buttonX && e.getX() <= buttonX + buttonW
                && e.getY() >= buttonY && e.getY() <= buttonY+buttonH){
                    gv.stopLoop();
                    Main.remakeGame(container, cardLayout);
                }
            }
        });
    }

    /**
     * Gets the width of the button that the user can click.
     * 
     * @return the width that the button should be.
     */
    public int getBWidth(){
        return this.buttonW;
    }

    /**
     *
     * Gets the height of the button that the user can click.
     * 
     * @return the height that the button should be.
     */
    public int getBHeight(){
        return this.buttonH;
    }

    /**
     * Gets the starting X position of the button.
     * 
     * @return the starting X position of the button.
     */
    public int getBX(){
        return this.buttonX;
    }

    /**
     * Gets the starting Y position of the button.
     * 
     * @return the starting Y position of the button.
     */
    public int getBY(){
        return this.buttonY;
    }

    /**
     * Gets the height of the displayed box showing the player has lost.
     * 
     * @return the height that the box should be.
     */
    public int getBoxHeight(){
        return this.boxH;
    }
    
    /**
     * Gets the width of the displayed box showing the player has lost.
     * 
     * @return the width that the box should be.
     */
    public int getBoxWidth(){
        return this.boxW;
    }

    /**
     * Checks whether the X and Y coordinates of a given click is within the restart button. 
     * @param x the x position of the click.
     * @param y the y position of the click.
     * @return true if the x and y position are within the parameters of the button's x and y values, and false otherwise.
     */
    public boolean isClickInsideButton(int x, int y) {
        return x >= getBX() && x <= getBX() + getBWidth() && y >= getBY() && y <= getBY() + getBHeight();
    }

     /**
     * Draws the background the title and the back button
     * @param g the graphics context for drawing
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        gv.paintComponent(g); //the game bg first
        int boxW = 300; //need test run
        int boxH = 250;
        int boxX = (getWidth() - boxW)/2; //centering starting coordinates
        int boxY = (getHeight() - boxH)/2;

        g.setColor(Color.WHITE);
        g.fillRect(boxX, boxY, boxW, boxH);
        g.drawImage(gameover, boxH + 10, boxY+10, boxX, boxY, null);

        int size = 32;
        int startX = boxX + 90;
        int startY = boxY + 100;
        for(int i=0; i<3; i++){
            g.drawImage(coins, startX + (i*(size+10)), startY, size, size, null);
        }
        g.drawImage(backButton, buttonX, buttonY, buttonW, buttonH, null);
    }
    

}

