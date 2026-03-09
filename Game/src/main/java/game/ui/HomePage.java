package game.ui;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import main.java.game.ui.GameView;
public class HomePage {
    private Image background;
    private Image pirateFlag; //check if you really need this
    private Image boar;
    private Image explorer;
    private Image hare;
    private Image chest;
    private Image foxRun;
    //need a start button too
    public HomePage(){
        this.background = new ImageIcon("Assets/Icons/EmptyHeart.png").getImage();
        this.pirateFlag = new ImageIcon("Assets/Icons/EmptyHeart.png").getImage();
        this.boar = new ImageIcon("Assets/Icons/EmptyHeart.png").getImage();
        this.explorer = new ImageIcon("Assets/Icons/EmptyHeart.png").getImage();
        this.hare = new ImageIcon("Assets/Icons/EmptyHeart.png").getImage();
        this.chest = new ImageIcon("Assets/Icons/EmptyHeart.png").getImage();
        this.foxRun = new ImageIcon("Assets/Icons/EmptyHeart.png").getImage();
    }
}
