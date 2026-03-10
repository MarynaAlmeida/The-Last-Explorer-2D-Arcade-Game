package game.ui;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.event.MouseEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

import game.ui.GameView;
public class LosingByCoins extends JPanel{
    //NO BUTTON YET CHECK!!
    private GameView gv;
    private Image coins;
    private Image backButton;
    private Image gameover;
    private int buttonX, buttonY, buttonW, buttonH;
    public LosingByCoins(GameView gv, CardLayout cardLayout, JPanel container){ //so that losing view is drawn OVER the gameview
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
                    cardLayout.show(container, "START");
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

        int size = 32;
        int startX = boxX + 90;
        int startY = boxY + 100;
        for(int i=0; i<3; i++){
            g.drawImage(coins, startX + (i*(size+10)), startY, size, size, null);
        }
        g.drawImage(backButton, buttonX, buttonY, buttonW, buttonH, null);
    }
    

}

