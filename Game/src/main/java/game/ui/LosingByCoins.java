package game.ui;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.event.MouseEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

import game.main.Game;
import game.main.Main;
import game.entities.enemies.rewards.Reward;
import game.enums.GameState;
import game.ui.GameView;

/**
 * Handles visuals for the losing screen by coins including background,
 * coin images and game over text
 * @author Komal Kaur
 * @version 1.0
 */
public class LosingByCoins extends JPanel{
    //NO BUTTON YET CHECK!!
    private GameView gv;
    private Image coins;
    private Image backButton;
    private Image gameover;
    private int buttonX, buttonY, buttonW, buttonH;
    private Game g;


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

