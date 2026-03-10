
package game.main;

import game.entities.*;
import game.entities.enemies.*;
import game.entities.enemies.rewards.*;
import game.enums.AnimalType;
import game.enums.CellType;
import game.enums.PirateType;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

/**
 * Represents the game map for the current maze game.
 * 
 * @author Komal Kaur
 * @version 1.0
 */
public class GameMap{
    private int height;
    private int width;
    private Cell[][] grid;
    private Position start;

    /** 
     * Constructs the start and end positions of the game.
     * 
     * <p>This constructor can be modified to have custom start and end positions.</p>
     */
    public GameMap(){
            // removed this.height and this.width since Game Map will be based on the size of the map file
            //added
            this.start = new Position(0,0);
    }

    /**
     * Acts as a getter for the start position of the game.
     * 
     * @return the start position of the game.
     */
    public Position getStart(){
        return this.start;
    }

    /**
     * Sets objects in the grid based on the type of char that is on the map file.
     * 
     * @param blueprint gets each char at the specific row and column.
     * @param rewards keeps a list of all the rewards set in the game map.
     * @param movingEnemies keeps a list of all the moving enemies set in the game map.
     * @param staticEnemies keeps a list of all the static enemies set in the game map.
     * */
    private void fillGrid(String[] blueprint, List<Reward> rewards, List<MovingEnemy> movingEnemies, List<StaticEnemy> staticEnemies) {
        for(int r = 0; r < this.height; r++){
            for(int c = 0; c < this.width; c++){
                char symbol = blueprint[r].charAt(c);
                Position pos = new Position(c, r);
                CellType type = CellType.EMPTY;
                grid[r][c] = new Cell(pos, type);

                // char x on map.txt means a hedge was placed at that tile
                if (symbol == 'x'){
                    type = CellType.HEDGE;
                }
                // char t on map.txt means a tree was placed at that tile
                else if (symbol == 't') {
                    type = CellType.TREE;
                }
                // char c on map.txt means a coin was placed at that tile
                else if(symbol == 'c') {
                    Coin coin = new Coin(pos, 1);
                    rewards.add(coin);
                    this.placeEntity(coin, pos);
                }
                // char s on map.txt means a shield was placed at that tile
                else if(symbol == 's') {
                    Shield shield = new Shield(pos, 200, 10);
                    rewards.add(shield);
                    this.placeEntity(shield, pos);
                }
                // char h on map.txt means a hare was placed at that tile
                else if(symbol == 'h') {
                    Animal hare = new Animal(pos, this, AnimalType.HARE);
                    movingEnemies.add(hare);
                    this.placeEntity(hare, pos);
                }
                // char f on map.txt means a fox was placed at that tile
                else if(symbol == 'f') {
                    Animal fox = new Animal(pos, this, AnimalType.FOX);
                    movingEnemies.add(fox);
                    this.placeEntity(fox, pos);
                }
                // char b on map.txt means a boar was placed at that tile
                else if(symbol == 'b') {
                    Animal boar = new Animal(pos, this, AnimalType.BOAR);
                    movingEnemies.add(boar);
                    this.placeEntity(boar, pos);
                }
                // char p on map.txt means pirate1 was placed at that tile
                else if(symbol == 'p') {
                    Pirate pirate1 = new Pirate(pos, this, PirateType.PIRATE1);
                    movingEnemies.add(pirate1);
                    this.placeEntity(pirate1, pos);
                }
                // char q on map.txt means a pirate2 was placed at that tile
                else if(symbol == 'q') {
                    Pirate pirate2 = new Pirate(pos, this, PirateType.PIRATE2);
                    movingEnemies.add(pirate2);
                    this.placeEntity(pirate2, pos);
                }
                // char y on map.txt means a thorn was placed at that tile
                else if(symbol == 'y') {
                    Thorns thorn = new Thorns(pos , 4);
                    staticEnemies.add(thorn);
                    this.placeEntity(thorn, pos);
                }
                // char j on map.txt means a bear trap was placed at that tile
                else if(symbol == 'j') {
                    BearTrap trap = new BearTrap(pos, 4);
                    staticEnemies.add(trap);
                    this.placeEntity(trap, pos);
                }
                else {
                    type = CellType.EMPTY;
                }
                grid[r][c] = new Cell(new Position(c, r), type);
            
            }
        }

    }

    /**
     * Reads map.txt and fills the grid with all the entities.
     * 
     *  @param file gets the map file to read it.
     *  @param rewards keeps a list of all the rewards set in the game map.
     *  @param movingEnemies keeps a list of all the moving enemies set in the game map.
     *  @param staticEnemies keeps a list of all the static enemies set in the game map.
     *  */
    public void loadMap(String file, List<Reward> rewards, List<MovingEnemy> movingEnemies, List<StaticEnemy> staticEnemies){
        try{
            File mapFile = new File(file);
            Scanner fileScanner = new Scanner(mapFile);
            List<String> lines = new ArrayList<String>();
            while (fileScanner.hasNextLine()) {
                String data = fileScanner.nextLine();
                lines.add(data);
            }
            fileScanner.close();
            this.height = lines.size();
            this.width = lines.get(0).length();
            this.grid = new Cell[this.height][this.width];
            String[] map = lines.toArray(new String[0]);
            fillGrid(map, rewards, movingEnemies, staticEnemies);
        }
        catch(FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    /**
     * Acts as a getter for a specified cell.
     * 
     * @param c the position of the cell that the user would like to get.
     * @return the row and column of the cell that the user would like to get.
     */
    public Cell getCell(Position c){
            //return the cell(position and type) from wherever its standing right nwo
            //just return the cell from the position c
            int x = c.getX(); //check func name
            int y = c.getY();
            if (y < 0 || y >= grid.length || x < 0 || x >= grid[0].length) {
                return null; 
            }
            return grid[y][x]; // y = rows, x = columns
        }

    /**
     * Checks to see if specified position is a valid cell that the explorer can move to,
     *  and returns true if so and false if not.
     * 
     * @param c the position of the cell that the user wants to validate.
     * @return a boolean that's true if the cell is a valid move, and false if it is not.
     */
    public boolean isValidMove(Position c){
            //if i move to position x is it ok? can i move to that position?
            int x = c.getX();
            int y = c.getY();
            // grid boundary check
            if (x < 0 || x >= grid[0].length || y < 0 || y >= grid.length) {
                return false; // It's outside the map, so it's not a valid move!
            }
            Cell pos = grid[y][x]; // y = rows, x = columns
            if(pos.isEmpty() && pos.isTraversable()){ //check if the cell contains restricted things where the explorer cant go
                return true;
            } 
            return false;
        }

    /**
     * Removes a specified entity from the map.
     * 
     * @param c the position of the entity's cell that the user wants removed. 
     */
    public void removeEntity(Position c){
            int x = c.getX();
            int y = c.getY();

            Cell pos = grid[y][x];
            pos.removeEntity();
        }

    /**
     * Places a specified entity on the map.
     * 
     * @param person the entity that the user would like placed on the map.
     * @param f the position that the user would like the specified entity placed on.
     */
    public void placeEntity(Entity person, Position f){
            //at position f place that particular person
            int x = f.getX();
            int y = f.getY();
            Cell pos = grid[y][x];
            pos.setEntity(person);
        }

    /**
     * Gets the height of the map.
     * 
     * @return the height of the game's entire map.
     */
    public int getHeight () {
        return this.height;
    }

    /**
     * Gets the width of the map.
     * 
     * @return the width of the game's entire map.
     */
    public int getWidth () {
        return this.width;
    }

    /**
     * Gets a cell at a specified coordinate.
     * 
     * @param c the column of the desired cell.
     * @param r the row of the desired cell.
     * @return the cell of the corresponding column and row.
     */
    public Cell getCell (int c, int r){
        return grid[r][c];
    }
}
