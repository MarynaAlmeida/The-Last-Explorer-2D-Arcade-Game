package game.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import game.enums.GameState;
import game.main.*;
import game.ui.GameView;

import game.enums.GameState;
import game.main.Game;
import game.entities.enemies.rewards.Reward;


public class LosingbyHearts extends JPanel{
    //check for the button IMPORTANT
    private Game g;
    private GameView gv;
    private Image backbutton;
    private Image gameover;
    private Image heart;
    private int buttonX, buttonY, buttonW, buttonH;
    
    public LosingbyHearts(Game g, GameView gv, CardLayout cardLayout, JPanel container){ //so that losing view is drawn OVER the gameview
        this.g = g;
        this.gv = gv;
        this.backbutton = new ImageIcon("../Assets/Icons/buttonHeart.png").getImage();
        this.heart = new ImageIcon("../Assets/Icons/EmptyHeart.png").getImage();
        this.gameover = new ImageIcon("../Assets/Icons/gameoverHearts.png").getImage();
        this.setPreferredSize(gv.getPreferredSize());
        this.setLayout(null);

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
                if(e.getX() >= buttonX && e.getX() <= buttonX + buttonW
                && e.getY() >= buttonY && e.getY() <= buttonY+buttonH){
                    gv.stopLoop();
                    Main.remakeGame(container, cardLayout);
                }
            }
        });
    }
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
        //not sure about this
        
        // g.setColor(Color.RED);
        // g.setFont(new Font("Arial", Font.BOLD, 20));
        // g.drawString("No more hearts left", boxX + 50, boxY + 50);
        int size = 32;
        int startX = boxX + 70;
        int startY = boxY + 100;
        for(int i=0; i<4; i++){
            g.drawImage(heart, startX + (i*(size+10)), startY, size, size, null);
        }
        g.drawImage(backbutton,buttonX, buttonY, buttonW, buttonH, null);
    }

    //the button takes you back to the home page 

}
