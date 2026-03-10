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

public class Game{
    private GameMap gamemap;
    private Explorer explorer;
    private List<MovingEnemy> movingEnemies;
    //added
    private List<StaticEnemy> staticEnemies;
    private List<Reward> rewards;
    private GameState state;
    private int score;
    private int hearts;
    private int maxHearts;
    private int MovingEnemyDamage;
    private int shieldSpawnCooldown;
    private int ticksSinceLastShieldSpawn;
    private GameView view;
    public boolean invincible = false;
    public int invincibleCounter = 0;

    public Game(){
        //constructor
        this.gamemap = new GameMap();
        this.explorer = new Explorer(new Position(0,14), gamemap);
        this.movingEnemies = new ArrayList<>();
        this.rewards = new ArrayList<>();
        this.state = GameState.MENU;
        //added 
        this.staticEnemies = new ArrayList<>();
        this.maxHearts = 6; // 1 heart = half heart, 6 hearts = 3 full hearts
        this.hearts = maxHearts; // players current heart value
        this.MovingEnemyDamage = 1;
        this.score = 0;
        this.shieldSpawnCooldown = 10;
        this.ticksSinceLastShieldSpawn = 0;
    }
    //start the game put the explorer in the game
    public void startGame(){
        this.state = GameState.PLAYING;
        gamemap.loadMap("../Assets/map.txt", rewards, movingEnemies, staticEnemies);
        //explorer.setPosition(gamemap.getStart());
    }
    public GameMap getMap(){
        return this.gamemap;
    }

    //added 
    public Explorer getExplorer() {
        return explorer;
    }

    //added
    public List<MovingEnemy> getMovingEnemies() {
        return movingEnemies;
    }

    //added
    public List<Reward> getRewards(){
        return rewards;
    }

    //added
    public List<StaticEnemy> getStaticEnemies() {
        return staticEnemies;
    }

    public int getScore(){
        return this.score;
    }

    public int getHearts(){
        return this.hearts;
    }

    public int getMaxHearts(){
        return this.maxHearts;
    }

    //update the hearts int
    public void updateHearts(int hearts){
        this.hearts = hearts;
    }

    public void updateScore(int score){
        this.score = score;
    }

    public int getPixelX(Entity e) {
        if( e instanceof MovingEnemy) {
            return ((MovingEnemy) e).getPixelX(); // The sliding coordinate
        }
        return e.getPosition().getX() * 32; // The grid coordinate converted to pixels
    }

    public int getPixelY(Entity e) {
        if( e instanceof MovingEnemy) {
            return ((MovingEnemy) e).getPixelY(); // The sliding coordinate
        }
        return e.getPosition().getY() * 32; // The grid coordinate converted to pixels
    }

    
    // detect collison between explorer and moving enemies
    public boolean checkCollision(Entity a, Entity b) {
        // explorer is tyle-based and movingEnemy is pixel-based we will get the pixel coordinates for both
        // for a sliding enemy, use pixelX/Y, for a grid explorer, multiply grid pos by 32.
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


    public void tick(){
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
        ticksSinceLastShieldSpawn++;
        if(ticksSinceLastShieldSpawn >= shieldSpawnCooldown){
            ticksSinceLastShieldSpawn = 0;
        }
        checkLoseCondition();
        checkWinCondition();
    }

    public void checkWinCondition(){
        //collected all the coins ; walk over treasure map

        //check from the rewards array list
        if(rewards.isEmpty()){
            return;
        }

        for(Reward r: this.rewards){
            if(!r.isCollected()){
                return;
            }
        }
        state = GameState.WON;
    }

    public void checkLoseCondition(){
        //either lost all their coins or all their hearts

        if(this.hearts <= 0 || rewards.isEmpty()){
            state = GameState.LOST;
        }
    }

    public void endGame(boolean hasWon){
        if(hasWon==true){
            state = GameState.WON;
        }
        else{
            state = GameState.LOST;
        }
    }
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
                break;
            case KeyEvent.VK_S:
                next = new Position(current.getX(), current.getY()+1); //moving downwards
                break;
            case KeyEvent.VK_D:
                next = new Position(current.getX()+1, current.getY()); //moving rightwards
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
