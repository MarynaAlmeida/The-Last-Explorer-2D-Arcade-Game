package game.ui;

import game.main.Game;

import javax.swing.*;


import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import game.ui.GameView;
/**
 * Handles visuals for the homepage including background, title and start button
 * @author Komal Kaur
 */
public class HomePage extends JPanel{
    private Image background;
    private Image text;
    private Image start;
    private int buttonX, buttonY, buttonW, buttonH;
    //need a start button too
    /**
     * constructor creates the homepage panel with start button, background and title
     * @param game the game used to set the dimensions
     * @param container the container to store all the views 
     * @param cardLayout the layout used to manage the views
     * @param view the game view start button leads to
     */
    public HomePage(Game game, JPanel container, CardLayout cardLayout, GameView view){
        this.background = new ImageIcon("../Assets/Icons/HomePage.png").getImage();
        this.start = new ImageIcon("../Assets/Icons/HomeButton.png").getImage();
        int width = game.getMap().getWidth()*32;
        int height = game.getMap().getHeight()*32;
        this.text = new ImageIcon("../Assets/Icons/Title.png").getImage();
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(null);
        buttonW = 300;
        buttonH = 100;
        buttonX = (width - buttonW) /2;
        buttonY = (height-buttonH) /2 + 150;

        //listens to the mouse clicks and switches to game view when the start button is clicked
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                if(e.getX() >= buttonX && e.getX() <= buttonX + buttonW
                && e.getY() >= buttonY && e.getY() <= buttonY+buttonH){
                    cardLayout.show(container, "GAME");
                    view.requestFocusInWindow();
                }
            }
        });

    }

    /**
     * Draws the background the title and the start button
     * @param g the graphics context for drawing
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int textX = (getWidth() - 700)/2;
        int textY = (getHeight() - 100)/2;
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);

        g.drawImage(text, textX, textY, 700, 100, null);
        //start button takes you where
        g.drawImage(start,buttonX, buttonY, buttonW, buttonH, null);
    }
}
