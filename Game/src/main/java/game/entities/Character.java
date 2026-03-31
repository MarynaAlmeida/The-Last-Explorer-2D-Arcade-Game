package game.entities;

import game.main.Position;
import game.main.GameMap;
import game.enums.Direction;

/**
 * Represents an abstract character class.
 * 
 * <p>This is a subclass that extends from {@link Entity}.</p>
 * 
 * @author Jayden Pai
 * @version 1.0
 */
public abstract class Character extends Entity{
    protected GameMap map;

    /**
     * Constructs a Character at the given position on the specified game map.
     *
     * @param position the starting position of the character
     * @param map the game map the character exists on
     */
    public Character(Position position, GameMap map){
        super(position);
        this.map = map;
    }

    /**
     * Attempts to move character in the specified direction.
     *
     * <p>The new position is calculated based on the direction
     * and validity is checked before the move is performed.</p>
     *
     * @param dir the direction to move the character.
     * @return true if movement was successful (false if invalid).
     */
    public boolean move(Direction dir){
        Position currentPos = this.getPosition();
        int x = currentPos.getX();
        int y = currentPos.getY();

        if (dir == null) return false;

        switch(dir){
            case LEFT:
                x--;
                break;
            case RIGHT:
                x++;
                break;
            case UP:
                y--;
                break;
            case DOWN:
                y++;
                break;
        }

        Position newPos = new Position(x, y);

        if (!canMoveTo(newPos)){
            return false;
        }
        this.setPosition(newPos);
        return true;
    }

    /**
     * Determines whether the character can move to the specified position.
     * 
     * <p>Checks that position and map are not null and verifies using game map
     * that the move is valid.</p>
     *
     * @param pos the position to check
     * @return true if the character can move to the position (false otherwise)
     */
    protected boolean canMoveTo(Position pos){
        if(pos == null || map == null){
            return false;
        }
        return map.isValidMove(pos);
    }
    
}