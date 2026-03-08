
package main.java.game.main;

import main.java.game.entities.*;
import main.java.game.entities.enemies.*;
import main.java.game.entities.enemies.rewards.*;
import main.java.game.enums.AnimalType;
import main.java.game.enums.CellType;
import main.java.game.enums.PirateType;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.*;
import java.io.*;

public class GameMap{
    private int height;
    private int width;
    private Cell[][] grid;
    private Position start;
    private Position end;

    /** took out parameters from GameMap since loadmap reads the mapFile
     * and gets its size depending on the size of the mapFile */
    public GameMap(){
            // removed this.height and this.width since Game Map will be based on the size of the map file
            //added
            this.start = new Position(0,0);
            //added
            this.end = new Position(0, 14);
    }

    public Position getStart(){
        return this.start;
    }

    /**
     * Sets objects in the grid based on the type of char that is on the map file
     * @param blueprint gets each char at the specific row and column
     * @param rewards keeps a list of all the rewards set in the game map
     * @param movingEnemies keeps a list of all the rewards set in the game map
     * @param staticEnemies keeps a list of all the rewards set in the game map
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
                    Coin coin = new Coin(pos, 3);
                    rewards.add(coin);
                    this.placeEntity(coin, pos);
                }
                // char s on map.txt means a shield was placed at that tile
                else if(symbol == 's') {
                    Shield shield = new Shield(pos, 3, 10);
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
     * Reads map.txt and fills the grid with all the entities
     * @param file gets the map file to read it
     *  @param rewards keeps a list of all the rewards set in the game map
     *  @param movingEnemies keeps a list of all the rewards set in the game map
     *  @param staticEnemies keeps a list of all the rewards set in the game map
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

    public Cell getCell(Position c){
            //return the cell(position and type) from wherever its standing right nwo
            //just return the cell from the position c
            int x = c.getX(); //check func name
            int y = c.getY();
            return grid[x][y]; // return the cell
        }


    public boolean isValidMove(Position c){
            //if i move to position x is it ok? can i move to that position?
            int x = c.getX();
            int y = c.getY();

            Cell pos = grid[x][y];
            if(pos.isEmpty()){ //check if the cell contains restricted things where the explorer cant go
                return true;
            } 
            return false;
        }

    public void removeEntity(Position c){
            //remove the person from that aka i think pirates and moving enemies
            int x = c.getX();
            int y = c.getY();

            Cell pos = grid[y][x];
            pos.removeEntity();
        }

    public void placeEntity(Entity person, Position f){
            //at position f place that particular person
            int x = f.getX();
            int y = f.getY();
            Cell pos = grid[y][x];
            pos.setEntity(person);
        }

    public Cell manualGetCell(int a, int b){
            //i dont think i get this one 
            return grid[a][b];
        }
    // added getters for height and width
    public int getHeight () {

        return this.height;

    }
    public int getWidth () {

        return this.width;
    }
    public Cell getCell ( int c, int r){

        return grid[r][c];

    }
}
