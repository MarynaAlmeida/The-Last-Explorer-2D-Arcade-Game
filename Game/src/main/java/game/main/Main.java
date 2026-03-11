package game.main;
import game.ui.*;
import javax.swing.JFrame;

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
        GameView view = new GameView(explorerGame);

        window.add(view);
        window.pack();
        view.requestFocusInWindow();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
