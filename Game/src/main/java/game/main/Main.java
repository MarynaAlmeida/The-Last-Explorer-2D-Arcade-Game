package main.java.game.main;
import main.java.game.ui.*;
import javax.swing.JFrame;

/**
 * The entry point of the game. Responsible for initializing the game logic,
 * setting up the GUI window and starting the game
 * */
public class Main {
    public static void main(String[] args) throws Exception {
        Game explorerGame = new Game();

        explorerGame.startGame();

        JFrame window = new JFrame("The Last Explorer");
        GameView view = new GameView(explorerGame);

        window.add(view);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
