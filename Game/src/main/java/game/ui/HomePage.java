package game.ui;

import game.main.Game;

import javax.swing.*;
import java.awt.*;
import game.ui.GameView;
public class HomePage extends JPanel{
    private Image background;
    private Image pirateFlag; //check if you really need this
    private Image boar;
    private Image explorer;
    private Image hare;
    private Image chest;
    private Image foxRun;
    //need a start button too
    public HomePage(Game game, JPanel container, CardLayout cardLayout, GameView view){
        this.background = new ImageIcon("../Assets/Icons/HomePage.png").getImage();

        int width = game.getMap().getWidth()*32;
        int height = game.getMap().getHeight()*32;

        this.setPreferredSize(new Dimension(width, height));

        JButton start = new JButton("Start");
        int buttonW = 100;
        int buttonH = 70;
        int buttonX = (width - buttonW) /2;
        int buttonY = (height-buttonH) /2;
        start.setBounds(buttonX, buttonY, buttonW, buttonH);
        start.addActionListener( e ->{
            cardLayout.show(container, "GAME");
            view.requestFocusInWindow();
        });
        add(start);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        g.setFont(getFont());

        g.drawString("The Last Explorer", 300, 300);
        //start button takes you where
    }
}
