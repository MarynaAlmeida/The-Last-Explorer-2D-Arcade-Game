package game.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import game.main.*;


/**
 * Handles visuals for the losing screen by hearts including background,
 * heart images and game over text
 * @author Komal Kaur
 */
public class LosingbyHearts extends JPanel{
    //check for the button IMPORTANT
    private GameView gv;
    private Image backbutton;
    private Image gameover;
    private Image heart;
    private int buttonX, buttonY, buttonW, buttonH, boxH, boxW;
    /**
     * creates the losinghearts panel with back button, background and game over text
     *
     * @param gv the game view used to set the dimensions
     * @param cardLayout the layout used to manage the views
     * @param container the container to store all the views 
     */
    
    public LosingbyHearts(GameView gv, CardLayout cardLayout, JPanel container){ //so that losing view is drawn OVER the gameview
        this.gv = gv;
        this.backbutton = new ImageIcon("../Assets/Icons/buttonHeart.png").getImage();
        this.heart = new ImageIcon("../Assets/Icons/EmptyHeart.png").getImage();
        this.gameover = new ImageIcon("../Assets/Icons/gameoverHearts.png").getImage();
        this.setPreferredSize(gv.getPreferredSize());
        this.setLayout(null);

        this.boxW = 300; //need test run
        this.boxH = 250;
        buttonW = 80;
        buttonH = 40;
        buttonX = (gv.getPreferredSize().width - buttonW) /2 ;
        buttonY = ((gv.getPreferredSize().height-buttonH) /2) + 100;
        //adding image instead of a button
        //using mouse event

        //mouseadapter has mousepressed
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                handleMouse(e.getX(), e.getY(), cardLayout, container);
            }
        });
    }
    
    //mouse clicked function
    public void handleMouse(int x, int y, CardLayout cardLayout, JPanel container){
        if(isClickInsideButton(x,y)){
            gv.stopLoop();
            Main.remakeGame(container, cardLayout);
        }
    }

    //getters for button height and width first
    public int getButtonWidth(){
        return this.buttonW;
    }

    public int getButtonHeight(){
        return this.buttonH;
    }

    //getters for Button x and y
    public int getButtonX(){
        return this.buttonX;
    }

    public int getButtonY(){
        return this.buttonY;
    }


    //box getters
    public int getBoxHeight(){
        return this.boxH;
    }
    
    public int getBoxWidth(){
        return this.boxW;
    }

    //mouse event boundary

    public boolean isClickInsideButton(int x, int y) {
        return x >= getButtonX() && x <= getButtonX() + getButtonWidth() && y >= getButtonY() && y <= getButtonY() + getButtonHeight();
    }
    /**
     * Draws the background the title and the back button
     * @param g the graphics context for drawing
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        gv.paintComponent(g); //the game bg first
        
        int boxX = (getWidth() - getBoxWidth())/2; //centering starting coordinates
        int boxY = (getHeight() - getBoxHeight())/2;

        g.setColor(Color.WHITE);
        g.fillRect(boxX, boxY, boxW, boxH);
        g.drawImage(gameover, boxH + 10, boxY+10, boxX, boxY, null);
        
        int size = 32;
        int startX = boxX + 70;
        int startY = boxY + 100;
        for(int i=0; i<4; i++){
            g.drawImage(heart, startX + (i*(size+10)), startY, size, size, null);
        }
        g.drawImage(backbutton,getButtonX(), getButtonY(), getButtonWidth(), getButtonHeight(), null);
    }

}
