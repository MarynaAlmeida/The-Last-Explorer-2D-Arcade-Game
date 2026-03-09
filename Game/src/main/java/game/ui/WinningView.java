//the explorer has all the coins
package game.ui;
import game.main.*;
import javax.swing.*;
import java.awt.*;
public class WinningView extends JPanel{
    private Image winningBg;
    private Image chest;
    private Game game;
    public WinningView(Game game){
        this.winningBg = new ImageIcon("../Assets/Icons/WinningBackground.png").getImage();
        this.chest = new ImageIcon("../Assets/Icons/ClosedChest.png").getImage();
        this.game = game;
        int height = game.getMap().getHeight() * 32;
        int width = game.getMap().getWidth() * 32; //pixels
        this.setPreferredSize(new Dimension(width, height));
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(winningBg, 0, 0, getWidth(), getHeight(),null);
        g.drawImage(chest, 10, 10, chest.getWidth(null), chest.getHeight(null), null);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("ALL COINS COLLECTED", (getWidth()/2) -150 ,80);
        g.drawString("THE TREASURE IS YOURS!", (getWidth()/2) -150 ,120); //this text comes lower than the first
    }
}