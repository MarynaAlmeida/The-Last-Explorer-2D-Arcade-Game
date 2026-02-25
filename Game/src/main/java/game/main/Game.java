//Game 
package main.java.game.main;
class Game{
    private Gamemap gamemap;
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
    }
    //start the game put the explorer in the game
    public void startGame(){
        // gamemap.
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
        
    }

    public void checkLoseCondition(){

    }

    public void endGame(bool hasWon){

    }
    public void input(Keyevent e){
        
    }
}