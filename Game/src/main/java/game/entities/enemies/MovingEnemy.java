package game.entities.enemies;

import game.entities.Character;
import game.main.GameMap;
import game.main.Position;

/**
 * Abstract base class for all enemies that navigate the map to the explorer
 * */

public abstract class MovingEnemy extends Character {
    protected int moveSpeed;
    /**
     * Initializes moving enemy position with a default speed
     * @param pos starting position of enemy on the game map
     * */

    public MovingEnemy(Position pos, GameMap map) {
        super(pos, map);
        moveSpeed = 1;
    }

    /**
     * Executes a basic path finding algorithm to move one step closer to the explorer
     * The enemy first tries horizontal movements, then vertical movements,
     * then attempts perpendicular deviations if blocked
     * @param explorerPos the current position of the explorer for enemy to track
     * */
    public void updateMovement(Position explorerPos) {
        Position currentPos = this.getPosition();
        int compareX = Integer.compare(explorerPos.getX(), currentPos.getX());
        int compareY = Integer.compare(explorerPos.getY(), currentPos.getY());

        Position targetXPos = new Position(currentPos.getX() + compareX, currentPos.getY());
        Position targetYPos = new Position(currentPos.getX(), currentPos.getY() + compareY);

        /**
         * If the enemy is not already in the same position as the explorer
         * it will move horizontally, in the x-axis, to catch the explorer
         * */
        if(compareX != 0 && canMoveTo(targetXPos)){
            this.setPosition(targetXPos);
            return;
        }

        /**
         * If the enemy can't move horizontally because it's blocked by an object
         * it will move vertically, in the y-axis, to catch the explorer
         * */
        if(compareY != 0 && canMoveTo(targetYPos)){
            this.setPosition(targetYPos);
            return;
        }

        /**
         * If both ways, horizontally and vertically, are blocked
         * it will try to move in a perpendicular direction, either up or down
         * */
        if(compareX != 0) {
            Position alternativeUpPos = new Position(currentPos.getX(), currentPos.getY() + 1);
            Position alternativeDownPos = new Position(currentPos.getX(), currentPos.getY() - 1);
            if (canMoveTo(alternativeUpPos)) {
                this.setPosition(alternativeUpPos);
                return;
            }
            else if(canMoveTo(alternativeDownPos)) {
                this.setPosition(alternativeDownPos);
                return;
            }
        }

        /**
         * If moving vertically, up or down, are blocked
         * it will try to move in a perpendicular direction, either left or right
         * */
        if(compareY != 0) {
            Position alternativeLeftPos = new Position(currentPos.getX() - 1, currentPos.getY());
            Position alternativeRightPos = new Position(currentPos.getX() + 1, currentPos.getY());
            if (canMoveTo(alternativeLeftPos)) {
                this.setPosition(alternativeLeftPos);
            }
            else if(canMoveTo(alternativeRightPos)) {
                this.setPosition(alternativeRightPos);
            }
        }
    }
}