package game.ui;

import game.main.Game;

import javax.swing.*;


import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import game.ui.GameView;
public class HomePage extends JPanel{
    private Image background;
    private Image text;
    private Image start;
    private int buttonX, buttonY, buttonW, buttonH;
    //need a start button too
    public HomePage(Game game, JPanel container, CardLayout cardLayout, GameView view){
        this.background = new ImageIcon("../Assets/Icons/HomePage.png").getImage();
        this.start = new ImageIcon("../Assets/Icons/startButton.png").getImage();
        int width = game.getMap().getWidth()*32;
        int height = game.getMap().getHeight()*32;
        this.text = new ImageIcon("../Assets/Icons/text.png").getImage();
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(null);
        buttonW = 100;
        buttonH = 60;
        buttonX = (width - buttonW) /2;
        buttonY = (height-buttonH) /2 + 50;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                if(e.getX() >= buttonX && e.getX() <= buttonX + buttonW
                && e.getY() >= buttonY && e.getY() <= buttonY+buttonH){
                    cardLayout.show(container, "GAME");
                }
            }
        });

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int textX = (getWidth() - 400)/2; //centering starting coordinates
        int textY = (getHeight() - 200)/2;
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);

        g.drawImage(text, textX, textY, 400, 100, null);
        //start button takes you where
        g.drawImage(start,buttonX, buttonY, buttonW, buttonH, null);
    }
}
