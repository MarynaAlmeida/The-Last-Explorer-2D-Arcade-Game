package game.main;
import game.ui.*;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The entry point of the game. Responsible for initializing the game logic,
 * setting up the GUI window and starting the game
 * */
public class Main {
    public static void main(String[] args) throws Exception {
        Game explorerGame = new Game();

        explorerGame.startGame();
        
        JFrame window = new JFrame("The Last Explorer");
        

        CardLayout cardLayout = new CardLayout(); //the one that manages all the views by " "
        JPanel container = new JPanel(cardLayout); //has all the views

        GameView view = new GameView(explorerGame);
        WinningView win = new WinningView(explorerGame);
        LosingByCoins loseC = new LosingByCoins(view, cardLayout, container);
        LosingbyHearts loseH = new LosingbyHearts(view, cardLayout, container);
        HomePage home = new HomePage(explorerGame, container, cardLayout, view);

        container.add(home, "START");
        container.add(view, "GAME");
        container.add(loseC ,"COINSLOSING");
        container.add(loseH, "HEARTSLOSING");
        container.add(win, "WIN");

        //start the game with showing the home page
        cardLayout.show(container, "WIN"); 

        //add the container to the window
        window.add(container);
        // window.add(view);
        window.pack();
        view.requestFocusInWindow();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
