package game.main;
import game.enums.CellType;
import game.entities.Entity;

/**
 * Demonstrates a single cell in the game map's grid.
 * 
 * <p>This class contains the position, cell type (e.g. hedge/tree/ etc...), and a maximum one entity.</p>
 * 
 * @author Harman Bains
 * @version 1.0
 */
public class Cell {
    private Position position;
    private final CellType type;
    private Entity entity;

    /**
     * Generates a cell on a particular position with a certain cell type.
     *
     * @param position the coordinated cell.
     * @param type     initializing cell type.
     */
    public Cell(Position position, CellType type) {
        this.position = position;
        this.type = type;
    }

    /**
     * Checks if the cell can traverse through entities.
     *
     * @return true if the cell is can traverse, false if it encounters a wall/obstacle.
     */
    public boolean isTraversable() {
        return type != CellType.HEDGE && type != CellType.TREE;
    }

    /**
     * Removes entity and returns it into state of this cell.
     * After removing entity, the cell is empty.
     *
     * @return the removed entity, or null if the case cell is empty prior.
     */
    public Entity removeEntity() {
        Entity removedEntity = this.entity;
        this.entity = null;
        return removedEntity;
    }

    /**
     * Applies an entity into this cell.
     * Overwrites possible entities available without verifying.
     *
     * @param entity the entity to put into this cell (e.g. may note to be null).
     */
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    /**
     * Provides a return on present entity in cell or can be empty implying null.
     *
     * @return the present or null entity.
     */
    public Entity getEntity() {
        return entity;
    }

    /**
     * Ensures to finding if cell contains no entity.
     *
     * @return true for the cell becoming empty, false if the entity available.
     */
    public boolean isEmpty() {
        return entity == null;
    }

    /**
     * Returns the current cell of a type.
     *
     * @return the specific type of cell.
     */
    public CellType getType() {
        return type;
    }

    /**
     * Applying a position to a cell.
     *
     * @param position the position of this cell.
     */

    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Returns the current position of cell.
     *
     * @return position.
     */
    public Position getPosition() {
        return position;
    }

}
