package game.ui;
import java.util.List;
import game.entities.enemies.*;
import game.entities.enemies.rewards.Coin;
import game.entities.enemies.rewards.Reward;
import game.entities.enemies.rewards.Shield;
import game.enums.Direction;
import game.main.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handles the visuals of the game world, including entities,
 * barriers and rewards on JPanel.
 * 
 * @author Komal Kaur
 * @version 1.0
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
    private Image tutorialPage;
    private boolean isTutorialActive = true;

    private boolean isMapToggled = false;
    private int zoomedInTileSize;

    private int zoomedInTotalTilesForWidth;
    private int zoomedInTotalTilesForHeight;

/**
 * Creates and stores all icon images in the game.
 * 
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
        this.tutorialPage = new ImageIcon("../Assets/Icons/Tutorial.png").getImage();
        this.explorerTurnImage = new ImageIcon("../Assets/Icons/ExplorerTurn.png").getImage();

        // Gets the width and height of the game map and calculates its dimension with the tile size
        int width = game.getMap().getWidth() * 32;
        int height = game.getMap().getHeight() * 32;

        zoomedInTileSize = 48;

        zoomedInTotalTilesForWidth = 16;
        zoomedInTotalTilesForHeight = 12;

        // Tells the JFrame how big the panel is going to be
        this.setPreferredSize(new Dimension(width, height));

        gameLoop = new Timer(50, this); // every 50 miliseconds we are going to repaint (20 frames/sec)
        gameLoop.start();
        GameInput input = new GameInput(game, this);
        addKeyListener(input);
        setFocusable(true);
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
     * Acts as the setter to toggle the map feature.
     */
    public void toggleMap() {
        isMapToggled = !isMapToggled;
    }

    /**
     * Acts as a getter for the status of whether the map is toggled or not.
     * 
     * @return a boolean that is true if the map is toggled, and false if not.
     */
    public boolean mapToggled() {
        return isMapToggled;
    }

    /**
     * Paints the tutorial window.
     * 
     * @param g the graphics context for drawing.
     */
    private void paintTutorial(Graphics g) {
        g.drawImage(tutorialPage, 0, 0, getWidth(), getHeight(), null);
    }

    /**
     * Paints all the enemies, barriers and rewards on the game panel.
     * 
     * @param g the graphics context for drawing.
     * */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.fillRect(0, 0, getWidth(), getHeight());

        if (mapToggled() && !isTutorialActive) {
            drawWholeMap(g);
        } else {
            drawPartialMap(g);
        }

        if (isTutorialActive) {
            paintTutorial(g);
        }
    }

    /**
     * Draws the entire map.
     * 
     * <p>This method is called when the map is toggled.</p>
     * 
     * @param g the graphics context for drawing.
     */
    public void drawWholeMap(Graphics g) {
        int tileSize = 32;
        drawBarriers(g, tileSize);
        drawMovingEntities(g, tileSize);
        drawStaticEntities(g, tileSize);
        drawRewards(g, tileSize);
    }

    /**
     * Draws a part of the map around the explorer.
     * 
     * <p>This method is called when the map is toggled off.</p>
     * 
     * @param g the graphics context for drawing.
     */
    public void drawPartialMap(Graphics g) {
        drawVisibleBarriers(g);
        drawVisibleMovingEntities(g);
        drawVisibleStaticEntities(g);
        drawVisibleRewards(g);
        paintRewardHUDs(g);
    }

    /**
     * Draws base grass tile for every cell and barries at a specific position.
     * 
     * @param g the graphics context for drawing.
     * @param tileSize tile size of the game map.
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
     * Draws the moving entities (explorer, enemies).
     * 
     * @param g the graphics context for drawing.
     * @param tileSize tile size of the game map.
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
     * Draws the rewards (shield, coin).
     * 
     * @param g the graphics context for drawing.
     * @param tileSize tile size of the game map.
     * */
    private void drawRewards(Graphics g, int tileSize) {
        List<Reward> rewards = game.getRewards();
        for(Reward r : rewards) {
            if(!r.isCollected()){
                Position rPos = r.getPosition();
                if(r instanceof Coin) {
                    g.drawImage(coinImage,rPos.getX() * tileSize + 8, rPos.getY() * tileSize + 8, (int) (tileSize * 0.5), (int) (tileSize * 0.5),null );
                }
                else if(r instanceof Shield) {
                    g.drawImage(shieldImage,rPos.getX() * tileSize + 10, rPos.getY() * tileSize + 10, (int) (tileSize * 0.6), (int) (tileSize * 0.6),null );
                }
            }
        }
    }

    /**
     * Draws the static entities (bear trap, thorns).
     * 
     * @param g the graphics context for drawing.
     * @param tileSize tile size of the game map.
     * */
    private void drawStaticEntities(Graphics g, int tileSize) {
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

    /**
    * Gets the starting cell column that should be painted when the map is toggled off.
    * 
    * @return an integer indicating the starting cell column that should be painted.
    */
    public int getStartingXCell() {
        GameMap map = game.getMap();
        Position explorerPos = game.getExplorer().getPosition();

        return Math.max(0, Math.min(explorerPos.getX() - zoomedInTotalTilesForWidth / 2,
        map.getHeight() - zoomedInTotalTilesForHeight));
    }

    /**
     * Gets the starting cell row that should be painted when the map is toggled off.
     * 
     * @return an integer indicating the starting cell row that should be painted.
     */
    public int getStartingYCell() {
        GameMap map = game.getMap();
        Position explorerPos = game.getExplorer().getPosition();

        return Math.max(0, Math.min(explorerPos.getY() - zoomedInTotalTilesForHeight / 2,
        map.getHeight() - zoomedInTotalTilesForHeight));
    }

    /**
     * Draws game barriers within a specified range of the explorer.
     * 
     * <p>This method is only called when the map is toggled off, and the map that
     * the user can see is limited.</p>
     * 
     * @param g the graphics context for drawing.
     */
    public void drawVisibleBarriers(Graphics g) {
        GameMap map = game.getMap();

        /**
         * Loops through the rows and columns of the grid and draws
         * the cell type and entities at the calculated pixel position
         * */
        for (int r = 0; r < zoomedInTotalTilesForHeight; r++) {
            for (int c = 0; c < zoomedInTotalTilesForWidth; c++) {
                int xOfCell = getStartingXCell() + c;
                int yOfCell = getStartingYCell() + r;

                Cell cell = map.getCell(xOfCell, yOfCell); /* Gets the cell at this grid position */
                int x = c * zoomedInTileSize; /* Translate grid to pixels */
                int y = r * zoomedInTileSize; /* Translate grid to pixels */

                g.drawImage(grassImage, x, y, zoomedInTileSize, zoomedInTileSize, null); /* Sets each tile of the panel to be a grass */
                switch (cell.getType()) {
                    case HEDGE:
                        g.drawImage(hedgeImage, x, y, zoomedInTileSize, zoomedInTileSize, null);
                        break;
                    case TREE:
                        // tileSize * 1.09 lets you modify the size of the object
                        g.drawImage(treeImage, x, y, (int) (zoomedInTileSize * 1.09) ,(int) (zoomedInTileSize * 1.09) , null);
                        break;
                    case EMPTY:
                        break;
                }
            }
        }
    }
    /**
     * Draws moving entities within a specified range of the explorer.
     * 
     * <p>This method is only called when the map is toggled off, and the map that
     * the user can see is limited.</p>
     * 
     * @param g the graphics context for drawing.
     */
    public void drawVisibleMovingEntities(Graphics g) {
         // draw explorer
        Position pPos = game.getExplorer().getPosition();

        int x = (pPos.getX() - getStartingXCell()) * zoomedInTileSize;
        int y = (pPos.getY() - getStartingYCell()) * zoomedInTileSize;

        if(game.getExplorer().getFacing() == Direction.LEFT) {
            g.drawImage(explorerTurnImage, x, y, zoomedInTileSize, zoomedInTileSize, null);
        }
        else if(game.getExplorer().getFacing() == Direction.RIGHT){ 
            g.drawImage(explorerImage, x, y, zoomedInTileSize, zoomedInTileSize, null);
        }
        // Draw Enemies
        for (MovingEnemy enemy : game.getMovingEnemies()) {
            Position ePos = enemy.getPosition();

            int enemyX = ePos.getX() - getStartingXCell();
            int enemyY = ePos.getY() - getStartingYCell();
            if (enemyX < zoomedInTotalTilesForWidth && enemyY < zoomedInTotalTilesForHeight) {
                int enemyXCell = enemyX * zoomedInTileSize;
                int enemyYCell = enemyY * zoomedInTileSize;
                if(enemy instanceof Animal) {
                    Animal animal = (Animal) enemy;
                    switch(animal.getAnimalType()) {
                        case HARE:
                            g.drawImage(hareImage, enemyXCell, enemyYCell, zoomedInTileSize, zoomedInTileSize, null);
                            break;
                        case BOAR:
                            g.drawImage(boarImage, enemyXCell, enemyYCell, zoomedInTileSize, zoomedInTileSize, null);
                            break;
                        case FOX:
                            g.drawImage(foxImage, enemyXCell, enemyYCell, zoomedInTileSize, zoomedInTileSize, null);
                            break;
                    }
                }
                if(enemy instanceof Pirate) {
                    Pirate pirate = (Pirate) enemy;
                    switch(pirate.getPirateType()) {
                        case PIRATE1:
                            g.drawImage(pirate1Image, enemyXCell, enemyYCell, zoomedInTileSize, zoomedInTileSize, null);
                            break;
                        case PIRATE2:
                            g.drawImage(pirate2Image, enemyXCell, enemyYCell, zoomedInTileSize, zoomedInTileSize, null);
                            break;
                    }
                }
            }
        }
    }
    
    /**
     * Draws static enemies within a specific range of the explorer.
     * 
     * <p>This method is only called when the map is toggled off, and the map that
     * the user can see is limited.</p>
     * 
     * @param g the graphics context for drawing.
     */
    public void drawVisibleStaticEntities(Graphics g) {
        for(StaticEnemy sE : game.getStaticEnemies() ) {
            Position ePos = sE.getPosition();
            
            int enemyX = ePos.getX() - getStartingXCell();
            int enemyY = ePos.getY() - getStartingYCell();

            if (enemyX < zoomedInTotalTilesForWidth && enemyY < zoomedInTotalTilesForHeight) {
                int enemyXCell = enemyX * zoomedInTileSize;
                int enemyYCell = enemyY * zoomedInTileSize;

                if(sE instanceof Thorns) {
                    g.drawImage(thornImage, enemyXCell, enemyYCell, zoomedInTileSize, zoomedInTileSize , null);
                }
                if(sE instanceof BearTrap) {
                    g.drawImage(bearTrapImage, enemyXCell, enemyYCell, zoomedInTileSize, zoomedInTileSize, null);
                }
            }
        }
    }

    /**
     * Paints rewards within a specified range of the explorer.
     * 
     * <p>This method is only called when the map is toggled off, and the map that
     * the user can see is limited.</p>
     * 
     * @param g the graphics context for drawing.
     */
    public void drawVisibleRewards(Graphics g) {
        for(Reward r : game.getRewards()) {
            Position rPos = r.getPosition();
            
            int rewardX = rPos.getX() - getStartingXCell();
            int rewardY = rPos.getY() - getStartingYCell();

            if (rewardX < zoomedInTotalTilesForWidth && rewardY < zoomedInTotalTilesForHeight) {
                int rewardXCell = rewardX * zoomedInTileSize;
                int rewardYCell = rewardY * zoomedInTileSize;

                if(!r.isCollected()){
                    if(r instanceof Coin) {
                        g.drawImage(coinImage, rewardXCell + 12, rewardYCell +12, (int) (zoomedInTileSize * 0.5), (int) (zoomedInTileSize * 0.5),null );
                    }
                    else if(r instanceof Shield) {
                        g.drawImage(shieldImage, rewardXCell + 17, rewardYCell + 17, (int) (zoomedInTileSize * 0.6), (int) (zoomedInTileSize * 0.6),null );
                    }
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
        if (!isTutorialActive && !isMapToggled) {
            game.tick();
        }
        repaint();
    }

    /**
     * Paints both the coin and shield HUDs.
     * 
     * @param g the graphics context for the drawing.
     */
    public void paintRewardHUDs(Graphics g) {
        
        String score = "Coins: " + game.getScore();

        g.setFont(new Font("Arial", Font.BOLD, 27));

        g.setColor(Color.BLACK);
        g.drawString(score, getWidth() - 135, getHeight() - (getHeight() - 25));
        g.drawString(score, getWidth() - 141, getHeight() - (getHeight() - 25));
        g.drawString(score, getWidth() - 138, getHeight() - (getHeight() - 22));
        g.drawString(score, getWidth() - 138, getHeight() - (getHeight() - 28));
        
        g.setColor(Color.WHITE);
        g.drawString(score, getWidth() - 138, getHeight() - (getHeight() - 25));

        if (game.getExplorer().isShieldActive()) {
            int secondsRemaining = game.getExplorer().getShieldTicksRemaining() / 20;

            g.drawImage(shieldImage, 100, 0, 30, 30, null);
            
            String shieldSecondsRemaining = Integer.toString(secondsRemaining);

            g.setColor(Color.BLACK);
            g.drawString(shieldSecondsRemaining, 135, 26);
            g.drawString(shieldSecondsRemaining, 141, 26);
            g.drawString(shieldSecondsRemaining, 138, 24);
            g.drawString(shieldSecondsRemaining, 138, 28);

            g.setColor(Color.WHITE);
            g.drawString(shieldSecondsRemaining, 138, 26);
        }

    }


}
