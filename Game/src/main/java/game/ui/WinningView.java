//the explorer has all the coins
package main.java.game.ui;
import main.java.game.Game;
import main.java.game.*;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
class WinningView extends JPanel{
    private Image winningBg;
    private Image chest;
    private Game game;
    public WinningView(Game game){
        this.winningBg = new ImageIcon("Assets/Icons/WinningBackground.png").getImage();
        this.chest = new ImageIcon("Assets/Icons/ClosedChest.png").getImage();
        this.game = game;
        int height = game.getMap().getHeight() * 32;
        int width = game.getMap().getHeight() * 32; //pixels
        this.setPreferredSize(new Dimension(width, height));
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(winningBg, 0, 0, getWidth(), getHeight(),null);
        g.drawImage(chest, 10, 10, chest.getWidth(null), chest.getHeight(null), null);
        g.drawString("ALL COINS COLLECTED", 100, 50);
        g.drawString("THE TREASURE IS YOURS!", 100, 100); //this text comes lower than the first
    }
}