package game.main;
import game.ui.*;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Initalizes the game logic and sets up the GUI window and starts the game.
 * 
 * @author Maryna Almeida
 * @version 1.0
 * */
public class Main {
    /**
     * Initializes necessary variables, and calls {@link Game} to start the game.
     * 
     * @param args parameters given when main is called from the terminal.
     * @throws Exception throws any potential errors that may arise.
     */
    public static void main(String[] args) throws Exception {
        Game explorerGame = new Game();

        explorerGame.startGame();
        
        JFrame window = new JFrame("The Last Explorer");
        

        CardLayout cardLayout = new CardLayout(); //the one that manages all the views by " "
        JPanel container = new JPanel(cardLayout); //has all the views

        GameView view = new GameView(explorerGame, container, cardLayout);
        WinningView win = new WinningView(explorerGame);
        LosingByCoins loseC = new LosingByCoins(explorerGame, view, cardLayout, container);
        LosingbyHearts loseH = new LosingbyHearts(explorerGame, view, cardLayout, container);
        HomePage home = new HomePage(explorerGame, container, cardLayout, view);

        container.add(home, "START");
        container.add(view, "GAME");
        container.add(loseC ,"COINSLOSING");
        container.add(loseH, "HEARTSLOSING");
        container.add(win, "WIN");

        //start the game with showing the home page
        cardLayout.show(container, "START"); 

        //add the container to the window
        window.add(container);
        // window.add(view);
        window.pack();
        view.requestFocusInWindow();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void remakeGame(JPanel container, CardLayout cardLayout) {
        Game explorerGame = new Game();
        explorerGame.startGame();

        GameView view = new GameView(explorerGame, container, cardLayout);
        WinningView win = new WinningView(explorerGame);
        LosingByCoins loseC = new LosingByCoins(explorerGame, view, cardLayout, container);
        LosingbyHearts loseH = new LosingbyHearts(explorerGame, view, cardLayout, container);
        HomePage home = new HomePage(explorerGame, container, cardLayout, view);

        container.removeAll();

        container.add(home, "START");
        container.add(view, "GAME");
        container.add(loseC ,"COINSLOSING");
        container.add(loseH, "HEARTSLOSING");
        container.add(win, "WIN");
        
        container.revalidate();
        container.repaint();

        cardLayout.show(container, "START");
    }
}
