package game.entities;

import game.main.Position;

public abstract class Entity{
    protected Position position;

    /**
     * Constructs an Entity at the specified position.
     *
     * @param position the intial position of the entity
     */
    public Entity(Position position){
        this.position = position;
    }

    /**
     * Returns the current position of the entity.
     *
     * @return the entity's position
     */
    public Position getPosition(){
        return position;
    }

    /**
     * Sets the position of the entity.
     *
     * @param position the new position to assign to the entity
     */
    public void setPosition(Position position){
        this.position = position;
    }
}