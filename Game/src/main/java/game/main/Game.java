//Game 
package game.main;
import game.entities.*;
import game.entities.enemies.*;
import game.entities.enemies.rewards.*;
import game.enums.*;
import game.ui.GameView;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.*;

/**
 * Creates the maze game and updates the maze.
 * 
 * @author Komal Kaur
 * @version 1.0
 */
public class Game{
    private GameMap gamemap;
    private Explorer explorer;
    private List<MovingEnemy> movingEnemies;
    private List<StaticEnemy> staticEnemies;
    private Chest chest;
    private List<Reward> rewards;
    private GameState state;
    private int score;
    private int coinsCollected;
    private int hearts;
    private int maxHearts;
    private int MovingEnemyDamage;
    private GameView view;
    public boolean invincible = false;
    public int invincibleCounter = 0;
    private boolean onStaticEntity;


    /**
     * Constructor for the game class.
     *
     * <p>This constructor initializes the values, hearts, score, cooldowns, and arrays for all entities.</p>
     */
    public Game(){
        this.gamemap = new GameMap();
        this.explorer = new Explorer(new Position(0,14), gamemap);
        this.explorer.setGame(this);
        this.movingEnemies = new ArrayList<>();
        this.rewards = new ArrayList<>();
        this.state = GameState.MENU;
        this.staticEnemies = new ArrayList<>();
        this.maxHearts = 6; /** 1 heart = half heart, 6 hearts = 3 full hearts */
        this.hearts = maxHearts;
        this.MovingEnemyDamage = 1;
        this.score = 0;
        this.coinsCollected = 0;
        this.onStaticEntity = false;
        this.chest = new Chest(new Position(24, 2), 10, 3);
    }

    /**
     * Sets the game state to 'Playing' and loads the map.
     */
    public void startGame(){
        this.state = GameState.PLAYING;
        gamemap.loadMap("../Assets/map.txt", rewards, movingEnemies, staticEnemies);
        //explorer.setPosition(gamemap.getStart());
    }

    /**
     * Acts as a getter for the current game's map.
     *
     * @return the current game's map.
     */
    public GameMap getMap(){
        return this.gamemap;
    }

    /**
     * Acts as a getter for the chest.
     *
     * @return the game chest.
     */
    public Chest getChest() {
        return chest;
    }

    /**
     * Acts as a getter for the explorer.
     *
     * @return the current game's explorer object.
     */
    public Explorer getExplorer() {
        return explorer;
    }

    /**
     * Acts as the getter for moving enemies.
     *
     * @return the array list of all moving enemies in the game.
     */
    public List<MovingEnemy> getMovingEnemies() {
        return movingEnemies;
    }

    /**
     * Acts as the getter for all rewards.
     *
     * @return the array list of all rewards in the game.
     */
    public List<Reward> getRewards(){
        return rewards;
    }

    /**
     * Acts as the getter for all static enemies.
     *
     * @return the array list of all static enemies in the game.
     */
    public List<StaticEnemy> getStaticEnemies() {
        return staticEnemies;
    }

    /**
     * Acts as the getter for the game score.
     *
     * @return the current game's score.
     */
    public int getScore(){
        return this.score;
    }

    /**
     * Acts as the getter for the explorer's current total hearts.
     *
     * @return an int of the total hearts the explorer has.
     */
    public int getHearts(){
        return this.hearts;
    }

    /**
     * Acts as the getter for the explorer's maximum number of hearts.
     *
     * @return an int of the total hearts the explorer is set to have at the start of the game.
     */
    public int getMaxHearts(){
        return this.maxHearts;
    }

    /**
     * Acts as the setter for updating the explorer's total hearts.
     *
     * @param hearts the amount to increment or decrement the explorer's hearts.
     */
    public void updateHearts(int hearts){
        this.hearts = hearts;
    }

    /**
     * Cause damage specifically applying to static enemies.
     * Shield can create immunity against damage.
     *
     * @param damageValue the damage collision towards explorer between enemy.
     */
    public void applyDamage(int damageValue) {
        if (!explorer.isShieldActive() && !invincible) {
            this.hearts -= damageValue;
            updateHearts(this.hearts);
        }
    }

    /**
     * Acts as the setter for updating the explorer's total score.
     *
     * @param amountToIncrease the amount to increment or decrement the explorer's score.
     */
    public void updateScore(int amountToIncrease){
        this.score += amountToIncrease;
    }

    /** Returns the horizontal pixel coordinate of the given entity.
     * <p>If the entity is a {@link MovingEnemy}, it returns a sliding
     * pixel position. Otherwise, it calculates the pixel position based on the
     * entity's grid coordinates multiplied by the tile size.</p>
     * @param e the entity that the x-coordinate is being asked.
     * @retun x-coordinate in pixel.
     */
    public int getPixelX(Entity e) {
        if( e instanceof MovingEnemy) {
            return ((MovingEnemy) e).getPixelX();
        }
        return e.getPosition().getX() * 32;
    }

    /** Returns the vertical pixel coordinate of the given entity.
     * <p>If the entity is a {@link MovingEnemy}, it returns a sliding
     * pixel position. Otherwise, it calculates the pixel position based on the
     * entity's grid coordinates multiplied by the tile size.</p>
     * @param e the entity that the y-coordinate is being asked.
     * @retun y-coordinate in pixel.
     */
    public int getPixelY(Entity e) {
        if( e instanceof MovingEnemy) {
            return ((MovingEnemy) e).getPixelY();
        }
        return e.getPosition().getY() * 32;
    }


    /** Detects the collision between the moving enemy and the explorer.
     * Since explorer is tyle-based and moving enemy is pixel-based, it will get
     * the pixel coordinates for both.
     * @param a the first entity.
     * @param b the second entity.
     * @return true if the entities cell overlap, false otherwise.
     */
    public boolean checkCollision(Entity a, Entity b) {
        int size = 32;
        int aX = getPixelX(a);
        int aY = getPixelY(a);
        int bX = getPixelX(b);
        int bY = getPixelY(b);
        return aX < bX + size &&
               aX + size > bX &&
               aY < bY + size &&
               aY + size > bY;
    }

    /**
     * Represents a single tick in the game.
     *
     * <p>This method will be called every tick, which will then perform various operations to update the game state.</p>
     */
    public void tick(){
        Position explorerPos = this.explorer.getPosition();
        if(invincible) {
            invincibleCounter++;
            if(invincibleCounter > 20) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

        for(MovingEnemy m: movingEnemies){
            m.updateMovement(explorer.getPosition());
        }
        for(MovingEnemy m: movingEnemies){
            if(checkCollision(m, explorer )) {
                if(!explorer.isShieldActive() && !invincible){
                    this.hearts -= MovingEnemyDamage;
                    updateHearts(hearts);
                    invincible = true;
                    invincibleCounter = 0;
                }
            }
        }

        boolean standingOnHazard = false;

        for (StaticEnemy s : staticEnemies) {
            Position staticPos = s.getPosition();

            if (staticPos.equals(explorerPos)) {
                standingOnHazard = true;

                if (!onStaticEntity) {
                    if (!explorer.isShieldActive()) {
                        if (s instanceof Thorns) {
                            this.updateScore(-1);
                        } else if (s instanceof BearTrap) {
                            this.updateScore(-3);
                        }
                    } else {
                        explorer.deactivateShield();
                    }
                }
            }
        }

        onStaticEntity = standingOnHazard;
        explorer.updateShield();

        for (Reward reward : rewards) {
            if (reward instanceof Shield) {
                ((Shield) reward).updateOnBoard();
            }

            if (reward.getPosition().equals(explorerPos)) {
                if(reward instanceof Chest) {
                    Chest chest = (Chest) reward;
                    if(!chest.isCollected()) {
                        if(chest.attemptOpening(this.coinsCollected)) {
                            this.updateScore(chest.bonusScore());
                        }
                    }
                    continue;
                }
                
                if (reward instanceof Shield) {
                    if (!((Shield) reward).isSpawned()) {
                        continue;
                    }
                }

                if (!reward.isCollected()) {
                    explorer.collectReward(reward);
                    if(reward instanceof Coin) {
                        coinsCollected++;
                    }
                    break;
                }


            }
        }

        checkLoseCondition();
        checkWinCondition();
    }

    /**
     * Checks to see if the game's winning conditions is satisfied, and sets the game's state to 'WON' if so.
     */
    public void checkWinCondition(){
        boolean allCoinsAreCollected = true;
        //collected all the coins ; walk over treasure map

        //check from the rewards array list
        for (Reward r : rewards) {
            if (r instanceof Coin && !r.isCollected()) {
                allCoinsAreCollected = false;
                break;
            }
        }

        if (!allCoinsAreCollected) {
            return;
        }

        for (Reward r : rewards) {
            if (r instanceof Chest && r.getPosition().equals(explorer.getPosition())) {
                state = GameState.WON;
                return;
            }
        }
    }

    /**
     * Checks to see if the game's losing conditions are satisfied, and sets the game's state to 'LOST' if so.     *
     */
    public void checkLoseCondition(){
        //either lost all their coins or all their hearts
        if(this.hearts <= 0 || this.score < 0) {
            state = GameState.LOST;
        }
    }

    /**
     * Acts as a getter for the state enumerator.
     *
     * @return an enumerator value indicating the state of the game.
     */
    public GameState getState() {
        return state;
    }

    /**
     * Acts as a setter for the state enumerator.
     *
     * @param state the state to set the enumerator.
     */
    public void setState(GameState state) {
        this.state = state;
    }

    /**
     * Updates the users position, and performs relevant logic after the player moves, based on the user's input.
     *
     * @param e the key that the user pressed.
     */
    public void input(KeyEvent e){
        //this is the one that actually implements it
        Position current = explorer.getPosition();
        Position next = null;
        switch(e.getKeyCode()){
            case KeyEvent.VK_W:
                next = new Position(current.getX(), current.getY()-1); //moving upwards
                break;
            case KeyEvent.VK_A:
                next = new Position(current.getX()-1, current.getY());//moving leftwards
                explorer.setFacing(Direction.LEFT);
                break;
            case KeyEvent.VK_S:
                next = new Position(current.getX(), current.getY()+1); //moving downwards
                break;
            case KeyEvent.VK_D:
                next = new Position(current.getX()+1, current.getY()); //moving rightwards
                explorer.setFacing(Direction.RIGHT);
                break;
        }

        if(next!=null && gamemap.isValidMove(next)){
            Position oldPos = explorer.getPosition();
            Cell cell = gamemap.getCell(next);
            Entity en = cell.getEntity();

            // if(en !=null){
            //     //en.onCollision(explorer);
            // }

            gamemap.removeEntity(oldPos);
            explorer.setPosition(next);
            gamemap.placeEntity(explorer, next);
            checkWinCondition();
            checkLoseCondition();
        }

    }
}
