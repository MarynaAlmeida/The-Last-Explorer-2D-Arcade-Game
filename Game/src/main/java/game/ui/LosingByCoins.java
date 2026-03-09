package game.ui;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import game.ui.GameView;
public class LosingByCoins extends JPanel{
    //NO BUTTON YET CHECK!!
    private GameView gv;
    private Image coins;
    public LosingByCoins(GameView gv, CardLayout cardLayout, JPanel container){ //so that losing view is drawn OVER the gameview
        this.gv = gv;
        this.coins = new ImageIcon("Assets/Icons/Coin.png").getImage();
        this.setPreferredSize(gv.getPreferredSize());
        //the button takes you back to the home page 
        JButton back = new JButton("Back");
        int buttonW = 100;
        int buttonH = 70;
        int buttonX = (getWidth() - buttonW) /2 ;
        int buttonY = (getHeight()-buttonH) /2;
        back.setBounds(buttonX, buttonY, buttonW, buttonH);
        back.addActionListener( e ->{
            cardLayout.show(container, "START"
            );
        });
        add(back);
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

        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("No more coins left", boxX + 30, boxY + 50);

        int size = 32;
        int startX = boxX + 50;
        int startY = boxY + 100;
        for(int i=0; i<3; i++){
            g.drawImage(coins, startX + (i*size), startY, size, size, null);
        }
        g.drawString("GAME OVER", boxX +50, boxY + 180);
    }
    

}

