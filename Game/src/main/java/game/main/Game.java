//Game 
package game.main;

import java.util.ArrayList;

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
        explorer.setPosition(this.gamemap.get)
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

    }

    public void checkWinCondition(){
        //collected all the coins ; walk over treasure map
        
    }

    public void checkLoseCondition(){
        //either lost all their coins or all their hearts
    }

    public void endGame(bool hasWon){
        if(hasWon==true){
            state = WON;
        }
        else{
            state = LOST;
        }
    }
    public void input(Keyevent e){
        
    }
}
