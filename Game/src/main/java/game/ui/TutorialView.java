package game.ui;
import javax.swing.*;
import java.awt.*;

public class TutorialView extends JPanel{
    private Image tutorialPage;
    private boolean isTutorialActive = true;

    public TutorialView(){
        this.tutorialPage = new ImageIcon("../Assets/Icons/Tutorial.png").getImage();
    }
    
    /**
     * Acts as a getter method for the isTutorialActive variable.
     * 
     * @return a boolean indicating if the tutorial is still active or not.
     */
    public boolean getTutorialStatus() {
        return isTutorialActive;
    }

    /**
     * Acts as a setter method for the isTutorialActive variable.
     * 
     * @param status the status of the tutorial variable.
     */
    public void setTutorialState(boolean status) {
        this.isTutorialActive = status;
    }

    /**
     * Paints the tutorial window.
     * 
     * @param g the graphics context for drawing.
     */
    public void paintTutorial(Graphics g) {
        g.drawImage(tutorialPage, 0, 0, getWidth(), getHeight(), null);
    }

}
