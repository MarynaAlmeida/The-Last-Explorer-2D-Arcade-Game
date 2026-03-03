package game.main;
public class GameMap{
    private int height;
    private int width;
    private Cell[][] grid;
    private Position start;
    private Position end;
    public GameMap(int height, int width){
            this.height = height;
            this.width = width;
            this.start = 0;
            this.end = 0;
            this.grid = new Cell[height][width]; //figure this out
            this.start = null;
            this.end = null;
        }

    public void loadMap(String file){
        //idk what this day

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

            Cell pos = grid[x][y];
            pos.removeEntity();
        }

    public void placeEntity(Entity person, Position f){
            //at position f place that particular person
            int x = f.getX();
            int y = f.getY();

            Cell pos = grid[x][y];
            pos.setEntity(person);
        }

    public Cell manualGetCell(int a, int b){
            //i dont think i get this one 
            return grid[a][b];
        }
}
