//Game 
package game.main;
import game.entities.*;
import game.entities.enemies.*;
import game.entities.enemies.rewards.*;
import game.enums.*;
import java.util.List;


import java.util.ArrayList;


import java.awt.event.*;
class Game{
    private GameMap gamemap;
    private Explorer explorer;
    private List<MovingEnemy> movingEnemies;
    private List<Reward> rewards;
    private GameState state;
    private int score;
    private int hearts;
    private int MovingEnemyDamage;
    private int shieldSpawnCooldown;
    private int ticksSinceLastShieldSpawn;

    public Game(){
        //constructor
        this.gamemap = new GameMap(10, 10);
        this.explorer = new Explorer(new Position(0,0));
        this.movingEnemies = new ArrayList<>();
        this.rewards = new ArrayList<>();
        this.state = GameState.MENU;
        //how many hearts does the explorer have?
        this.hearts = 0;
        this.MovingEnemyDamage = 1;
        this.score = 0;
        this.shieldSpawnCooldown = 10;
        this.ticksSinceLastShieldSpawn = 0;
    }
    //start the game put the explorer in the game
    public void startGame(){
        this.state = GameState.PLAYING;
        gamemap.loadMap("map.txt");
        explorer.setPosition(gamemap.getStart());
    }

    public int getScore(){
        return this.score;
    }

    public int getHearts(){
        return this.hearts;
    }
    //update the hearts int
    public void updateHearts(int hearts){
        this.hearts = hearts;
    }

    public void updateScore(int score){
        this.score = score;
    }

    public void tick(){
        //moving the enemies to the explorer's position
        for(MovingEnemy m: movingEnemies){
            m.updateMovement(explorer.getPosition());
        }
        for(MovingEnemy m: movingEnemies){
            Position enemy = m.getPosition();
            Position explorerPos = this.explorer.getPosition();
            if(enemy.equals(explorerPos)){
                //update hearts
                if(!explorer.isShieldActive()){
                    updateHearts(hearts - MovingEnemyDamage);
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
                next = new Position(current.getX()-1, current.getY());
                break;
            case KeyEvent.VK_S:
                next = new Position(current.getX(), current.getY()+1); //moving upwards
                break;
            case KeyEvent.VK_D:
                next = new Position(current.getX()+1, current.getY()); //moving upwards
                break;
        }

        if(next!=null && gamemap.isValidMove(next)){
            //what if there is something on the next cell?
            //ASSUMING I HAVE THE GETENTITY FUNCTION IN THE CELL CLASS
            Cell cell = gamemap.getCell(next);
            Entity en = cell.getEntity();
            if(en !=null){
                en.onCollision(explorer);
            }
            gamemap.removeEntity(next);
            explorer.setPosition(next);
            gamemap.placeEntity(explorer, next);
            checkWinCondition();
            checkLoseCondition();
        }
        
    }
}
