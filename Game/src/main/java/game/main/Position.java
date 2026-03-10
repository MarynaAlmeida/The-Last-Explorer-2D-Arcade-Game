package game.main;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a position on the game map.
 * 
 * <p>This class does not contain information on the position of objects. Instead, associated classes, such as
 * {@link Entity} and it's subclasses, utilize this class.</p>
 * 
 * @author Maryna Almeida
 * @version 1.0
 */
public class Position{
    private int x;
    private int y;

    /**
     * Constructs a new Position with the given coordinates.
     * @param x the x coordinate.
     * @param y the y coordinate.
     */
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the x coordinate of this position.
     * @return the x coordinate.
     */
    public int getX(){
        return x;
    }

    /**
     * Gets the y coordinate of this position.
     * @return the y coordinate.
     */
    public int getY(){
        return y;
    }

    /**
     * Checks whether this position is equal to another object.
     * <p>Two positions are equal if they have the same coordinates (x and y).</p>
     * 
     * @param other the object to compare with.
     * @return true if the positions have the same coordinates (false otherwise).
     */
    @Override
    public boolean equals(Object other){
        if(other == null){
            return false;
        }
        if(this == other){
            return true;
        }
        if(!(other instanceof Position)){
            return false;
        }

        Position otherPos = (Position) other;
        return ((this.x == otherPos.x) && (this.y == otherPos.y));
    }

    /**
     * Generates a hash code for this position using its coordinates.
     * Ensures compatibility with hash based collections since equals was overriden.
     * 
     * @return the hash code for this position.
     */
    @Override
    public int hashCode(){
        return Objects.hash(x, y);
    }

    /**
     * Calculates the Euclidean distance between this position and another position.
     * 
     * @param other the position to measure distance to.
     * @return the Euclidean distance between the two positions.
     */
    public double distanceTo(Position other){
        int distx = this.x - other.x;
        int disty = this.y - other.y;
        return Math.sqrt(distx * distx + disty * disty);
    }

    /**
     * Returns a list of positions directly adjacent to this one.
     * 
     * <p>This method does not check whether the returned positions are valid.</p>
     * 
     * @return a list containing the four adjacent positions.
     */
    public List<Position> getAdjacentPositions(){
        List<Position> adj = new ArrayList<>();

        adj.add(new Position(x, y - 1));
        adj.add(new Position(x, y + 1));
        adj.add(new Position(x - 1, y));
        adj.add(new Position(x + 1, y));

        return adj;
    }
}