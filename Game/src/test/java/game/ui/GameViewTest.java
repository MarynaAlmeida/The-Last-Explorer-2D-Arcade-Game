package game.ui;
import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import game.entities.enemies.rewards.Chest;
import game.entities.enemies.rewards.Reward;
import game.entities.enemies.rewards.Shield;
import game.enums.Direction;
import game.enums.GameState;
import game.main.Game;
import game.main.Position;

import org.junit.jupiter.api.BeforeEach;
/**
 * Unit and integration tests for GameView class
 * @author Komal
 * @version 1.0
 */
public class GameViewTest {
    private GameView gameV;
    private Game game;
    private TutorialView tutorialView;

    @BeforeEach 
    public void setUp(){
        game = new Game();
        CardLayout cardLayout = new CardLayout();
        JPanel container = new JPanel(cardLayout);
        tutorialView = new TutorialView();
        gameV= new GameView(game, container, cardLayout);
    }

    @Test 
    public void checkgetTutorial(){
        assertTrue(tutorialView.getTutorialStatus());
    }   

    @Test 
    public void checksetTutorial(){
        tutorialView.setTutorialState(false);
        assertFalse(tutorialView.getTutorialStatus());
    }

    @Test 
    public void checksetToggled(){
        gameV.toggleMap(); //sets it to true
        assertTrue(gameV.mapToggled());
    }

    @Test 
    public void checkgetToggled(){
        assertFalse(gameV.mapToggled()); //bc constructor sets to false by default
    }

    @Test 
    public void doubleToggled(){
        gameV.toggleMap(); //true
        gameV.toggleMap(); //false now
        assertFalse(gameV.mapToggled());
    }

    //check out of bounds as in negative for starting x cell
    @Test 
    public void checkStartingXcell(){
        assertTrue(gameV.getStartingXCell()>=0);
    }

    @Test 
    public void checkStartingYcell(){
        assertTrue(gameV.getStartingYCell()>=0);
    }

    @Test 
    public void checkStopLoop(){
        gameV.stopLoop();
    }


    //all painting graphics tests
    //does it build
    //draws tutorial page
     @Test
    public void checkPaintComponent() {
        

        game.startGame();
        BufferedImage img = new BufferedImage(300, 300, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();

        gameV.setSize(300, 300);

        assertDoesNotThrow(() -> gameV.paintComponent(g2));
    }

    
    //shouldnt built if map not toggled does it draw partial map
    @Test
    public void checkPaintComponentPartial(){

        game.startGame();
        BufferedImage img = new BufferedImage(300, 300, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();

        gameV.setSize(300, 300);
        tutorialView.setTutorialState(false);
        assertDoesNotThrow(()-> gameV.paintComponent(g2));
    }

    @Test
    public void checkPaintComponentWholeMap(){
        game.startGame();
        BufferedImage img = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        gameV.setSize(500, 500);
        tutorialView.setTutorialState(false);
        gameV.toggleMap();
        assertDoesNotThrow(() -> gameV.paintComponent(g2));
    }

    @Test
    public void checkDrawWholeMap(){
        BufferedImage img = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        gameV.setSize(500, 500);
        assertDoesNotThrow(() -> gameV.drawWholeMap(g2));
    }

    @Test 
    public void checkPartialMap(){
        game.startGame();
        BufferedImage img = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        gameV.setSize(500, 500);
        assertDoesNotThrow(() -> gameV.drawPartialMap(g2));
    }

    @Test 
    public void checkdrawHearts(){
        BufferedImage img = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        gameV.setSize(500, 500);
        assertDoesNotThrow(() -> gameV.drawHearts(g2, 2));
    }

    @Test 
    public void checkVisibleBarriers(){
        game.startGame();
        BufferedImage img = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        gameV.setSize(500, 500);
        assertDoesNotThrow(() -> gameV.drawVisibleBarriers(g2));
    }

    @Test 
    public void checkVisibleEntities(){
        BufferedImage img = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        gameV.setSize(500, 500);
        assertDoesNotThrow(() -> gameV.drawVisibleMovingEntities(g2));
    }

    @Test 
    public void checkVisibleStaticEntities(){
        BufferedImage img = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        gameV.setSize(500, 500);
        assertDoesNotThrow(() -> gameV.drawVisibleStaticEntities(g2));
    }

    @Test 
    public void checkVisibleRewards(){
        BufferedImage img = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        gameV.setSize(500, 500);
        assertDoesNotThrow(() -> gameV.drawVisibleRewards(g2));
    }
    //integration 
    //hearts losing
    @Test
    public void checkLostStateHearts(){
        game.startGame();
        game.updateHearts(-6);
        game.tick();
        assertEquals(GameState.LOST, game.getState());
    }

    //losing by coins
    @Test
    public void checkLostStateCoins(){
        game.startGame();
        game.updateScore(-100);
        game.tick();
        assertEquals(GameState.LOST, game.getState());
    }
    
    @Test
    public void checkActionPerformedWonState(){
        game.startGame();
        for(Reward r : game.getRewards()){
            if(r instanceof game.entities.enemies.rewards.Coin){
                r.collect();
            }
        }
        for(Reward r : game.getRewards()){
        if(r instanceof game.entities.enemies.rewards.Chest){
            // move explorer to wherever the chest actually is
            game.getExplorer().setPosition(r.getPosition());
            break;
            }
        }
    
        game.tick();
        assertEquals(GameState.WON, game.getState());
    }

    @Test
    public void checkDrawRewardsOpenChest(){
        game.startGame();
        for(Reward r : game.getRewards()){
            if(r instanceof Chest){
                r.collect();
                break;
            }
        }
        BufferedImage img = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        gameV.setSize(500, 500);
        assertDoesNotThrow(() -> gameV.drawWholeMap(g2));
    }

    @Test
    public void checkDrawRewardsShieldSpawned(){
        game.startGame();
        for(Reward r : game.getRewards()){
            if(r instanceof Shield){
                ((Shield)r).spawn();
                break;
            }
        }
        BufferedImage img = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        gameV.setSize(500, 500);
        assertDoesNotThrow(() -> gameV.drawWholeMap(g2));
    }
    @Test
    public void checkDrawMovingEntitiesExplorerLeft(){
        game.startGame();
        // set explorer facing LEFT
        game.getExplorer().setFacing(Direction.LEFT);
        BufferedImage img = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        gameV.setSize(500, 500);
        assertDoesNotThrow(() -> gameV.drawWholeMap(g2));
    }

    @Test
public void checkDrawVisibleRewardsOpenChest(){
    game.startGame();
    for(Reward r : game.getRewards()){
        if(r instanceof Chest){
            game.getExplorer().setPosition(r.getPosition());
            r.collect();
            break;
        }
    }
    BufferedImage img = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2 = img.createGraphics();
    gameV.setSize(500, 500);
    assertDoesNotThrow(() -> gameV.drawVisibleRewards(g2));
}

@Test
public void checkDrawVisibleRewardsClosedChest(){
    game.startGame();
    for(Reward r : game.getRewards()){
        if(r instanceof Chest){
            game.getExplorer().setPosition(r.getPosition());
            break;
        }
    }
    BufferedImage img = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2 = img.createGraphics();
    gameV.setSize(500, 500);
    assertDoesNotThrow(() -> gameV.drawVisibleRewards(g2));
}
@Test
public void checkDrawVisibleRewardsShieldSpawned(){
    game.startGame();
    for(Reward r : game.getRewards()){
        if(r instanceof Shield){
            game.getExplorer().setPosition(r.getPosition()); 
            ((Shield) r).spawn();
            break;
        }
    }
    BufferedImage img = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2 = img.createGraphics();
    gameV.setSize(500, 500);
    assertDoesNotThrow(() -> gameV.drawVisibleRewards(g2));
}
}



