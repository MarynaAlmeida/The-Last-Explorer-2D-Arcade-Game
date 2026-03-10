package game.ui;

import javax.swing.*;
import java.awt.*;
import game.main.*;
import game.ui.GameView;

public class LosingbyHearts extends JPanel{
    //check for the button IMPORTANT
    private GameView gv;
    private Image heart;
    public LosingbyHearts(GameView gv, CardLayout cardLayout, JPanel container){ //so that losing view is drawn OVER the gameview
        this.gv = gv;
        this.heart = new ImageIcon("../Assets/Icons/EmptyHeart.png").getImage();
        this.setPreferredSize(gv.getPreferredSize());
        this.setLayout(null);
        JButton back = new JButton("Back");
        int buttonW = 80;
        int buttonH = 40;
        int buttonX = (gv.getPreferredSize().width - buttonW) /2 ;
        int buttonY = ((gv.getPreferredSize().height-buttonH) /2) + 100;
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
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("No more hearts left", boxX + 50, boxY + 50);
        int size = 32;
        int startX = boxX + 70;
        int startY = boxY + 100;
        for(int i=0; i<4; i++){
            g.drawImage(heart, startX + (i*(size+10)), startY, size, size, null);
        }
        g.drawString("GAME OVER", boxX +90, boxY + 180);
    }

    //the button takes you back to the home page 

}
