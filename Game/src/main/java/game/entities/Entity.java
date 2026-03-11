package game.entities;

import game.entities.enemies.StaticEnemy;
import game.entities.enemies.rewards.Reward;
import game.main.Position;

/**
 * Represents an abstract entity.
 * 
 * <p>This class is a superclass for the {@link Character}, {@link StaticEnemy} and {@link Reward} classes.</p>
 * 
 * @author Jayden Pai
 * @version 1.0
 */
public abstract class Entity{
    protected Position position;

    /**
     * Constructs an entity at the specified position.
     *
     * @param position the intial position of the entity.
     */
    public Entity(Position position){
        this.position = position;
    }

    /**
     * Returns the current position of the entity.
     *
     * @return the entity's position.
     */
    public Position getPosition(){
        return position;
    }

    /**
     * Sets the position of the entity.
     *
     * @param position the new position to assign to the entity.
     */
    public void setPosition(Position position){
        this.position = position;
    }
}