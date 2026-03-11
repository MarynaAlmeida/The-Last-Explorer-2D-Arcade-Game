package game.entities.enemies;

import java.util.Random;

import game.entities.Character;
import game.entities.Entity;
import game.enums.CellType;
import game.enums.Direction;
import game.main.Cell;
import game.main.GameMap;
import game.main.Position;

/**
 * Abstract base class for all enemies that navigate the map to the explorer
 * */

public abstract class MovingEnemy extends Character {
    protected int moveSpeed;
    Position defaulPosition;
    protected int pixelX;
    protected int pixelY;
    private int velocityX = 0;
    private int velocityY = 0;
    protected Direction currentDir;
    private int tileSize = 32;
    Direction[] directions = Direction.values(); // up, down, left, right
    Random random = new Random();
    
    /**
     * Initializes moving enemy at a certain position with a default speed
     * 
     * @param pos starting position of enemy on the game map
     * */

    public MovingEnemy(Position pos, GameMap map) {
        super(pos, map);
        moveSpeed = 7;
        pixelX = pos.getX() * tileSize;
        pixelY = pos.getY() * tileSize;
        updateDirection(Direction.RIGHT);
        this.defaulPosition = new Position(pos.getX(), pos.getY());
        
    }

    public void resetPosition() {
        this.setPosition(new Position(defaulPosition.getX(), defaulPosition.getY()));
        // IMPORTANT: Also reset your sliding pixel coordinates!
        // If your pixels aren't reset, the enemy will "teleport" back on the grid 
        // but visually stay where they were.
        this.pixelX = defaulPosition.getX() * 32;
        this.pixelY = defaulPosition.getY() * 32;

    }



    private void move() {
       this.pixelX += velocityX;
       this.pixelY += velocityY;
       //Convert pixels back to Grid coordinates for your Cell logic
       int gridX = this.pixelX / tileSize;
       int gridY = this.pixelY / tileSize;
       Position newGridPos = new Position(gridX, gridY);

       // check if the cell at that position is tranversable
       if(map.isValidEnemyMove(newGridPos)) {
           this.setPosition(newGridPos);
       }
       else{
           // hit an hedge or tree, step back
           this.pixelX -= velocityX;
           this.pixelY -= velocityY;


           // IMPORTANT: Stop them so updateMovement knows to pick a new path!
           this.velocityX = 0;
           this.velocityY = 0;
       }
   }

    public int getPixelX() {
        return this.pixelX;
    }

    public int getPixelY() {
        return this.pixelY;
    }


    private void updateDirection(Direction newDir) {
       // Direction prevDir = this.currentDir;
        this.currentDir = newDir;
        // setting velocity so it can slide 
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
     * Executes a basic path finding algorithm to move one step closer to the explorer.
     * 
     * <p>The enemy first tries horizontal movements, then vertical movements,
     * then attempts perpendicular deviations if blocked.</p>
     * 
     * @param explorerPos the current position of the explorer for the enemy to track.
     * */
    public void updateMovement(Position explorerPos) {
        this.move();
       // if move() set velocity to 0, it means we hit a hedge or tree
       if(this.velocityX == 0 && this.velocityY == 0) {  
           Direction nextDir;
        do {
            nextDir = directions[random.nextInt(directions.length)];
        } while(nextDir == getOpposite(currentDir));
           this.updateDirection(nextDir);
       }
   }     

   // prevent from going back and forth all the time
   private Direction getOpposite(Direction dir) {
        switch(dir) {
            case UP: return Direction.DOWN;
            case DOWN: return Direction.UP;
            case LEFT: return Direction.RIGHT;
            case RIGHT: return Direction.LEFT;
        }
        return dir;
    }

}