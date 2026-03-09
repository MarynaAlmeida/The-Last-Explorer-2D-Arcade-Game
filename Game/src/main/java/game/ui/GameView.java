package game.ui;
import java.util.List;
import game.entities.enemies.*;
import game.entities.enemies.rewards.Coin;
import game.entities.enemies.rewards.Reward;
import game.entities.enemies.rewards.Shield;
import game.enums.Direction;
import game.main.*;
import static game.enums.CellType.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handles the visuals of the game world, including entities,
 * barriers and rewards on JPanel
 * */
public class GameView extends JPanel implements ActionListener{
    private Game game;
    Timer gameLoop;
    private Image hedgeImage;
    private Image thornImage;
    private Image treeImage;
    private Image explorerImage;
    private Image explorerTurnImage;
    private Image pirate1Image;
    private Image pirate2Image;
    private Image boarImage;
    private Image hareImage;
    private Image foxImage;
    private Image grassImage;
    private Image coinImage;
    private Image chestImage;
    private Image bearTrapImage;
    private Image shieldImage;

/**
 * Creates and stores all icon images in the game
 * @param game the game state to be visualized
 * */
    public GameView(Game game) {
        this.game = game;
        this.explorerImage = new ImageIcon("../Assets/Icons/Explorer.png").getImage();
        this.treeImage = new ImageIcon("../Assets/Icons/Tree.png").getImage();
        this.hedgeImage = new ImageIcon("../Assets/Icons/Hedge.png").getImage();
        this.pirate1Image = new ImageIcon("../Assets/Icons/Pirate1.png").getImage();
        this.pirate2Image = new ImageIcon("../Assets/Icons/Pirate2.png").getImage();
        this.boarImage = new ImageIcon("../Assets/Icons/BoarWalk.png").getImage();
        this.hareImage = new ImageIcon("../Assets/Icons/Hare.png").getImage();
        this.foxImage = new ImageIcon("../Assets/Icons/Fox.png").getImage();
        this.grassImage = new ImageIcon("../Assets/Icons/GrassTile4copy.png").getImage();
        this.thornImage = new ImageIcon("../Assets/Icons/Thorn.png").getImage();
        this.coinImage = new ImageIcon("../Assets/Icons/Coin.png").getImage();
        this.chestImage = new ImageIcon("../Assets/Icons/ClosedChest.png").getImage();
        this.bearTrapImage = new ImageIcon("../Assets/Icons/Beartrap.png").getImage();
        this.shieldImage = new ImageIcon("../Assets/Icons/Shield.png").getImage();
        this.explorerTurnImage = new ImageIcon("../Assets/Icons/ExplorerTurn.png").getImage();

        // Gets the width and height of the game map and calculates its dimension with the tile size
        int width = game.getMap().getWidth() * 32;
        int height = game.getMap().getHeight() * 32;

        // Tells the JFrame how big the panel is going to be
        this.setPreferredSize(new Dimension(width, height));

        gameLoop = new Timer(50, this); // every 50 miliseconds we are going to repaint (20 frames/sec)
        gameLoop.start();
        GameInput input = new GameInput(game);
        addKeyListener(input);
        setFocusable(true);
    
        
    }
    /**
     * Paints all the enemies, barriers and rewards on the game panel
     * @param g the graphics context for drawing
     * */

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.fillRect(0, 0, getWidth(), getHeight());

        int tileSize = 32;
        drawBarriers(g, tileSize);
        drawMovingEntities(g, tileSize);
        drawStaticEntities(g, tileSize);
        drawRewards(g, tileSize);
    }
    /**
     * Draws base grass tile for every cell and barries at a specific position
     * @param g the graphics context for drawing
     * @param tileSize tile size of the game map
     * */

    private void drawBarriers(Graphics g, int tileSize) {
        GameMap map = game.getMap();
        tileSize = 32;
        /**
         * Loops through the rows and columns of the grid and draws
         * the cell type and entities at the calculated pixel position
         * */
        for (int r = 0; r < map.getHeight(); r++) {
            for (int c = 0; c < map.getWidth(); c++) {
                Cell cell = map.getCell(c, r); /* Gets the cell at this grid position */
                int x = c * tileSize; /* Translate grid to pixels */
                int y = r * tileSize; /* Translate grid to pixels */
                g.drawImage(grassImage, x, y, tileSize, tileSize, null); /* Sets each tile of the panel to be a grass */
                switch (cell.getType()) {
                    case HEDGE:
                        g.drawImage(hedgeImage, x, y, tileSize , tileSize, null);
                        break;
                    case TREE:
                        // tileSize * 1.09 lets you modify the size of the object
                        g.drawImage(treeImage, x, y, (int) (tileSize * 1.09) ,(int) (tileSize * 1.09) , null);
                        break;
                    case EMPTY:
                        break;
                }
            }
        }
    }

    /**
     * Draws the moving entities (explorer, enemies)
     * @param g the graphics context for drawing
     * @param tileSize tile size of the game map
     * */
    private void drawMovingEntities(Graphics g, int tileSize) {
        // draw explorer
        Position pPos = game.getExplorer().getPosition();
        if(game.getExplorer().getFacing() == Direction.LEFT) {
            g.drawImage(explorerTurnImage, pPos.getX() * tileSize, pPos.getY() * tileSize, tileSize, tileSize, null);
        }
        else if(game.getExplorer().getFacing() == Direction.RIGHT){ 
            g.drawImage(explorerImage, pPos.getX() * tileSize, pPos.getY() * tileSize, tileSize, tileSize, null);
        }
        // Draw Enemies
        for (MovingEnemy enemy : game.getMovingEnemies()) {
            Position ePos = enemy.getPosition();
            if(enemy instanceof Animal) {
                Animal animal = (Animal) enemy;
                switch(animal.getAnimalType()) {
                    case HARE:
                        g.drawImage(hareImage, ePos.getX() * tileSize, ePos.getY() * tileSize, tileSize, tileSize, null);
                        break;
                    case BOAR:
                        g.drawImage(boarImage, ePos.getX() * tileSize, ePos.getY() * tileSize, tileSize, tileSize, null);
                        break;
                    case FOX:
                        g.drawImage(foxImage, ePos.getX() * tileSize, ePos.getY() * tileSize, tileSize, tileSize, null);
                        break;
                }
            }
            if(enemy instanceof Pirate) {
                Pirate pirate = (Pirate) enemy;
                switch(pirate.getPirateType()) {
                    case PIRATE1:
                        g.drawImage(pirate1Image, ePos.getX() * tileSize, ePos.getY() * tileSize, tileSize, tileSize, null);
                        break;
                    case PIRATE2:
                        g.drawImage(pirate2Image, ePos.getX() * tileSize, ePos.getY() * tileSize, tileSize, tileSize, null);
                        break;
                }
            }

        }
    }

    /**
     * Draws the rewards (shield, coin)
     * @param g the graphics context for drawing
     * @param tileSize tile size of the game map
     * */
    private void drawRewards(Graphics g, int tileSize) {
        List<Reward> rewards = game.getRewards();
        for(Reward r : rewards) {
            if(!r.isCollected()){
                Position rPos = r.getPosition();
                if(r instanceof Coin) {
                    g.drawImage(coinImage,rPos.getX() * tileSize, rPos.getY() * tileSize , (int) (tileSize * 0.5), (int) (tileSize * 0.5),null );
                }
                else if(r instanceof Shield) {
                    g.drawImage(shieldImage,rPos.getX() * tileSize, rPos.getY() * tileSize , (int) (tileSize * 0.6), (int) (tileSize * 0.6),null );
                }
            }
        }
    }

    /**
     * Draws the static entities (bear trap, thorns)
     * @param g the graphics context for drawing
     * @param tileSize tile size of the game map
     * */
    private void drawStaticEntities(Graphics g, int tileSize) {
        List<StaticEnemy> staticEnemies = game.getStaticEnemies();
        for(StaticEnemy sE : game.getStaticEnemies() ) {
            Position ePos = sE.getPosition();
            if(sE instanceof Thorns) {
                g.drawImage(thornImage, ePos.getX() * tileSize, ePos.getY() * tileSize, tileSize, tileSize , null);
            }
            if(sE instanceof BearTrap) {
                g.drawImage(bearTrapImage, ePos.getX() * tileSize, ePos.getY() * tileSize, tileSize, tileSize, null);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
        game.tick();
        repaint();
    }

}
