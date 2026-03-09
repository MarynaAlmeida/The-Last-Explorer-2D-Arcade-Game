//Game 
package game.main;
import game.entities.*;
import game.entities.enemies.*;
import game.entities.enemies.rewards.*;
import game.enums.*;
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
    private int MovingEnemyDamage;
    private int shieldSpawnCooldown;
    private int ticksSinceLastShieldSpawn;
    private boolean onStaticEntity;

    public Game(){
        //constructor
        this.gamemap = new GameMap();
        this.explorer = new Explorer(new Position(0,14), gamemap);
        this.explorer.setGame(this);
        this.movingEnemies = new ArrayList<>();
        this.rewards = new ArrayList<>();
        this.state = GameState.MENU;
        //added 
        this.staticEnemies = new ArrayList<>();
        //how many hearts does the explorer have?
        this.hearts = 0;
        this.MovingEnemyDamage = 1;
        this.score = 0;
        this.shieldSpawnCooldown = 20;
        this.ticksSinceLastShieldSpawn = 0;
        this.onStaticEntity = false;
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
    //update the hearts int
    public void updateHearts(int hearts){
        this.hearts += hearts;
    }

    public void updateScore(int amountToIncrease){
        this.score += amountToIncrease;
    }

    public void tick(){
        Position explorerPos = this.explorer.getPosition();

        //moving the enemies to the explorer's position
        for(MovingEnemy m: movingEnemies){
            m.updateMovement(explorer.getPosition());
        }
        for(MovingEnemy m: movingEnemies){
            Position enemyPos = m.getPosition();
            if(enemyPos.equals(explorerPos)){
                //update hearts
                if(!explorer.isShieldActive()){
                    updateHearts(-1);
                } else {
                    explorer.deactivateShield();
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

        ticksSinceLastShieldSpawn++;
        if(ticksSinceLastShieldSpawn >= shieldSpawnCooldown){
            ticksSinceLastShieldSpawn = 0;

            for (Reward shield : rewards) {
                if (!shield.isCollected() && shield.getValue() == 0) {
                    shield.setUncollected();
                }
            }
        }

        for (Reward reward : rewards) {
            if (reward.getPosition().equals(explorerPos) && !reward.isCollected()) {
                explorer.collectReward(reward);
                break;
            }
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
