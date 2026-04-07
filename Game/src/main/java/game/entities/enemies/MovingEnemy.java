package game.entities.enemies;

import java.util.Random;
import game.entities.Character;
import game.enums.Direction;
import game.main.GameMap;
import game.main.Position;

/**
 * Abstract base class for all enemies that navigate the map to the explorer.
 * 
 *  @author Maryna Almeida
 * @version 2.0
 * */

public class MovingEnemy extends Character {
    protected int moveSpeed;
    private int pixelX;
    private int pixelY;
    private int velocityX = 0;
    private int velocityY = 0;
    private Direction currentDir;
    private int tileSize = 32;
    private Direction[] directions = Direction.values();
    private Random random = new Random();
    
    /**
     * Initializes a moving enemy at a specific grid position, converting it to
     * pixel coordinates with a default movement speed.
     * @param pos starting position of enemy on the game map.
     * @param map game map that the enemies exist on.
     * */

    public MovingEnemy(Position pos, GameMap map) {
        super(pos, map);
        moveSpeed = 7;
        pixelX = pos.getX() * tileSize;
        pixelY = pos.getY() * tileSize;
        updateDirection(Direction.RIGHT);
    }
    /**
     * Converts pixel movements back into pixel coordinates to check for collision/traversability.
     */

    void move() {
       int potentialX = pixelX + velocityX;
       int potentialY = pixelY + velocityY;
    
       /** Converts pixels back to grid coordinates. */
       int gridX = potentialX / tileSize;
       int gridY = potentialY / tileSize;
       Position potentialGridPos = new Position(gridX, gridY);

       /** Checks if the cell at that position is tranversable. */
       if(map.isValidEnemyMove(potentialGridPos)) {
           this.pixelX += velocityX;
           this.pixelY += velocityY;
           this.setPosition(potentialGridPos);
       }
       else {
           /** Resets the velocity, so a new path can be chosen. */
           this.velocityX = 0;
           this.velocityY = 0;
       }
   }

   /** Returns pixel coordinates.
    * @return current x-coordinate in pixels.
   */
    public int getPixelX() {
        return this.pixelX;
    }

    /** Returns pixel coordinates.
     * @return current y-coordinate in pixels.
    */
    public int getPixelY() {
        return this.pixelY;
    }

    /** Returns velocity in the y-direction.
     * @return current velocity in the y-direction.
    */
    public int getVelocityX() {
        return this.velocityX;
    }

    /** Returns velocity in the x-direction.
     * @return current velocity in the x-direction.
    */
    public int getVelocityY() {
        return this.velocityY;
    }

    /** Updates the enemy's current direction and sets the 
     * velocity vectors to enable smooth sliding animation.
     * @param newDir the new {@link Direction} for the enemy to face.
     */
    void updateDirection(Direction newDir) {
        this.currentDir = newDir;
        switch (newDir) {
            case UP:
                this.velocityX = 0;
                this.velocityY = -tileSize/4;
                break;
            case DOWN:
                this.velocityX = 0;
                this.velocityY = tileSize/4;
                break;
            case LEFT:
                this.velocityX = -tileSize/4;
                this.velocityY = 0;
                break;
            case RIGHT:
                this.velocityX = tileSize/4;
                this.velocityY = 0;
                break;    
        }
    }

    /**
     * Executes a basic pathfinding algorithm to move through the map.
     * 
     * <p>If the enemy hits a hedge or tree, it selects a new random direction 
     * (excluding the one it just came from) to ensure continuous movement.</p>
     * 
     * @param explorerPos the current position of the explorer for the enemy to track.
     * */
    public void updateMovement(Position explorerPos) {
        this.move();
       if(this.velocityX == 0 && this.velocityY == 0) {  
           Direction nextDir;
        do {
            nextDir = directions[random.nextInt(directions.length)];
        } while(nextDir == getOpposite(currentDir));
           this.updateDirection(nextDir);
       }
   }     

   /** Calculates and returns the opposite of the given direction.
    * This is used to prevent the enemy from immediately reversing its path.
    * @param dir current direction to be inverted.
    * @return the {@link Direction} opposite to the current direction.
    */
    Direction getOpposite(Direction dir) {
        return switch(dir) {
            case UP -> Direction.DOWN;
            case DOWN -> Direction.UP;
            case LEFT -> Direction.RIGHT;
            case RIGHT -> Direction.LEFT;
        };
        
    }
}