package game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.xml.stream.events.Characters;

import sprites.CleanHallway;
import sprites.Dirt;
import sprites.Dumpster;
import sprites.DustBall;
import sprites.Sprite;
import sprites.Vacuum;
import sprites.Wall;

import game.Constants;

/**
 * A class that represents the basic functionality of the vacuum game.
 * This class is responsible for performing the following operations:
 * 1. At creation, it initializes the instance variables used to store the
 *        current state of the game.
 * 2. When a move is specified, it checks if it is a legal move and makes the
 *        move if it is legal.
 * 3. It reports information about the current state of the game when asked.
 */
public class VacuumGame {

    // a random number generator to move the DustBalls
    private Random random;

    // the grid
    private Grid<Sprite> grid;

    // the first player
    private Vacuum vacuum1;

    /// the second player
    private Vacuum vacuum2;

    // the dirt (both static dirt and mobile dust balls)
    private List<Dirt> dirts;

    // the dumpsters
    private List<Dumpster> dumpsters;

    /**
     * Creates a new VacuumGame that corresponds to the given input text file.
     * Assumes that the input file has one or more lines of equal lengths, and
     * that each character in it (other than newline) is a character that 
     * represents one of the sprites in this game.
     * @param layoutFileName path to the input grid file
     */
    public VacuumGame(String layoutFileName) throws IOException {
        this.dirts = new ArrayList<Dirt>();
        this.dumpsters = new ArrayList<Dumpster>(); // Jen: may not need this
        this.random = new Random();

        // open the file, read the contents, and determine 
        // dimensions of the grid
        int[] dimensions = getDimensions(layoutFileName);
        this.grid = new ArrayGrid<Sprite>(dimensions[0], dimensions[1]);

        // open the file again, read the contents, and store them in grid
        Scanner sc = new Scanner(new File(layoutFileName));

        // INITIALIZE THE GRID HERE
        for(int y=0; y < dimensions[0]; y++){
        	if (sc.hasNextLine()){
        		String line = sc.nextLine();
        		for(int x=0; x < line.length(); x++){
        			
        			/* Check all the cases for this character and properly 
        			   add it to the grid */
        			switch(line.charAt(x)){
        				case Constants.CLEAN:
        					this.grid.setCell(y, x, new CleanHallway(Constants.CLEAN, y, x));
        					break;
        				case Constants.DIRT:
        					Dirt newDirt = new Dirt(Constants.DIRT, y, x, Constants.DIRT_SCORE);
        					this.grid.setCell(y, x, newDirt);
        					this.dirts.add(newDirt);
        					break;
        				case Constants.DUMPSTER:
        					Dumpster newDumpster = new Dumpster(Constants.DUMPSTER, y, x);
        					this.grid.setCell(y, x, newDumpster);
        					this.dumpsters.add(newDumpster);
        					break;
        				case Constants.DUST_BALL:
        					Dirt newDustball = new DustBall(Constants.DUST_BALL, y, x, Constants.DIRT_SCORE);
        					this.grid.setCell(y, x, newDustball);
        					this.dirts.add(newDustball);
        					break;
        				case Constants.WALL:
        					this.grid.setCell(y, x, new Wall(Constants.WALL, y, x));
        					break;
        				case Constants.P1:
        					this.vacuum1 = new Vacuum(Constants.P1, y, x, Constants.CAPACITY);
        					CleanHallway hallway = new CleanHallway(Constants.CLEAN, y, x);
        					this.vacuum1.setUnder(hallway);
        					this.grid.setCell(y, x, this.vacuum1);
        					break;
        				case Constants.P2:
        					this.vacuum2 = new Vacuum(Constants.P2, y, x, Constants.CAPACITY);
        					CleanHallway emptyHallway = new CleanHallway(Constants.CLEAN, y, x);
        					this.vacuum2.setUnder(emptyHallway);
        					this.grid.setCell(y, x, this.vacuum2);
        					break;
        			}
        		}
        	}
        }
        sc.close();
    }


    /**
     * Returns the dimensions of the grid in the file named layoutFileName.
     * @param layoutFileName path of the input grid file
     * @return an array [numRows, numCols], where numRows is the number
     * of rows and numCols is the number of columns in the grid that
     * corresponds to the given input grid file
     * @throws IOException
     */
    private int[] getDimensions(String layoutFileName) throws IOException {       
    	File x = new File(layoutFileName);
    	System.out.println(x);
        Scanner sc = new Scanner(x);

        // find the number of columns
        String nextLine = sc.nextLine();
        int numCols = nextLine.length();

        int numRows = 1;

        // find the number of rows
        while (sc.hasNext()) {
            numRows++;
            nextLine = sc.nextLine();
        }

        sc.close();
        return new int[]{numRows, numCols};
    }

    /**
     * Get the grid of this Game instance.
     * @return the grid of this game instance
     */
	public Grid<Sprite> getGrid() {
		return grid;
	}

	/**
	 * Get the Vacuum from the first player.
	 * @return return the vacuum from the first player
	 */
	public Vacuum getVacuumOne() {
		return vacuum1;
	}

	/**
	 * Get the vacuum the second player.
	 * @return the Vacuum of the second player
	 */
	public Vacuum getVacuumTwo() {
		return vacuum2;
	}
    
	/**
	 * Get the number of rows from this games grid.
	 * @return the number of rows from this grid
	 */
    public int getNumRows(){
    	return this.grid.getNumRows();
    }
    
    /**
     * Get the number of columns from this games grid.
     * @return the number of columns from this grid
     */
    public int getNumColumns(){
    	return this.grid.getNumColumns();
    }
    
    /**
     * Gets the sprite from the location specified from this games grid. 
     * @param i the row of the Sprite
     * @param j the column of the Sprite
     * @return the Sprite at the specified location
     */
    public Sprite getSprite(int i, int j){
    	return this.grid.getCell(i, j);
    }
    
    /**
     * Determines whether the game is over or not.
     * @return a boolean of whether this instance of the game is over or not
     */
    public boolean gameOver(){
    	return !existsDirt();
    }
    
    /**
     * Get the winner of this instance of the game.
     * Player1 wins if he has a higher score and 1 is returned, if there is 
     * a tie or player2 has a higher score then 2 is returned
     * @return the integer value corresponding to the winning player
     */
    public int getWinner(){
    	if (vacuum1.getScore() > vacuum2.getScore()){
    		return 1;
    	}else{
    		return 2;
    	}
    }
    
    /**
     * Moves vacuum1 and vacuum2 corresponding the move given. 
     * Vacuum1 and Vacuum2 will both clean the current spot that they are on
     * and all the dustballs in this game will be moved in a valid direction.
     * @param nextMove is the character of the move command for either 
     * player1 or player2
     * @return returns the boolean value of whether a vacuum has moved 
     */
    public boolean move(char nextMove){
    	boolean hasMoved = false;
    	
    	//Check whether the move is valid for either vacuum and make the move
    	switch (nextMove){
    		case Constants.P1_DOWN:
    			hasMoved = moveVacuum(this.vacuum1, this.vacuum1.getRow() + Constants.DOWN, this.vacuum1.getColumn());
    			break;
    		case Constants.P1_LEFT:
    			hasMoved = moveVacuum(this.vacuum1, this.vacuum1.getRow(), this.vacuum1.getColumn() + Constants.LEFT);
    			break;
    		case Constants.P1_RIGHT:
    			hasMoved = moveVacuum(this.vacuum1, this.vacuum1.getRow(), this.vacuum1.getColumn() + Constants.RIGHT);
    			break;
    		case Constants.P1_UP:
    			hasMoved = moveVacuum(this.vacuum1, this.vacuum1.getRow() + Constants.UP, this.vacuum1.getColumn());
    			break;
        	case Constants.P2_DOWN:
        		hasMoved = moveVacuum(this.vacuum2, this.vacuum2.getRow() + Constants.DOWN, this.vacuum2.getColumn());
        		break;
        	case Constants.P2_LEFT:
        		hasMoved = moveVacuum(this.vacuum2, this.vacuum2.getRow(), this.vacuum2.getColumn() + Constants.LEFT);
        		break;
        	case Constants.P2_RIGHT:
        		hasMoved = moveVacuum(this.vacuum2, this.vacuum2.getRow(), this.vacuum2.getColumn() + Constants.RIGHT);
        		break;
        	case Constants.P2_UP:
        		hasMoved = moveVacuum(this.vacuum2, this.vacuum2.getRow() + Constants.UP, this.vacuum2.getColumn());
        		break;
    	}
    	
    	// Clean what is under both vacuum
    	cleanBothVacuum();
    	
    	// Create the list of dustballs from the arraylist of dirt
    	ArrayList<DustBall> dustballs = new ArrayList<DustBall>();
    	for (Dirt dirt : this.dirts){
    		if (dirt.toString().equals(String.valueOf(Constants.DUST_BALL))){
    			dustballs.add((DustBall)dirt); 
    		}
    	}
    	
    	// Move all the dustballs
    	for (DustBall dustball : dustballs){
    		// Make a list of valid moves for this dustball
    		ArrayList<String> validMoves = new ArrayList<String>();
    		if (validDustMove(dustball.getRow() + Constants.UP, dustball.getColumn())){
    			validMoves.add("U");
    		}
    		if (validDustMove(dustball.getRow() + Constants.DOWN, dustball.getColumn())){
    			validMoves.add("D");
    		}
    		if (validDustMove(dustball.getRow(), dustball.getColumn() + Constants.RIGHT)){
    			validMoves.add("R");
    		}
    		if (validDustMove(dustball.getRow(), dustball.getColumn() + Constants.LEFT)){
   				validMoves.add("L");
   			}
    		
    		// Choose a random direction and move the dustball
    		if (validMoves.size() != 0){
				String randDirection = validMoves.get(this.random.nextInt(validMoves.size()));
				switch (randDirection){
					case "U":
						if (!vacuum1.getUnder().equals(dustball) && !vacuum2.getUnder().equals(dustball)){
							this.dirts.remove(this.grid.getCell(dustball.getRow() + Constants.UP, dustball.getColumn()));
							Dirt newUpDirt = new Dirt(Constants.DIRT, dustball.getRow(), dustball.getColumn(), Constants.DIRT_SCORE);
							this.grid.setCell(dustball.getRow(), dustball.getColumn(), newUpDirt);
							this.dirts.add(newUpDirt);
						}else{
							this.vacuum1.setUnder(new CleanHallway(Constants.CLEAN, vacuum1.getRow(), vacuum1.getColumn()));
							this.vacuum2.setUnder(new CleanHallway(Constants.CLEAN, vacuum2.getRow(), vacuum2.getColumn()));
						}
						dustball.moveTo(dustball.getRow() + Constants.UP, dustball.getColumn());
						this.grid.setCell(dustball.getRow(), dustball.getColumn(), dustball);
						break;
					case "D":
						if (!vacuum1.getUnder().equals(dustball) && !vacuum2.getUnder().equals(dustball)){
							this.dirts.remove(this.grid.getCell(dustball.getRow() + Constants.DOWN, dustball.getColumn()));
							Dirt newDownDirt = new Dirt(Constants.DIRT, dustball.getRow(), dustball.getColumn(), Constants.DIRT_SCORE);
							this.grid.setCell(dustball.getRow(), dustball.getColumn(), newDownDirt);
							this.dirts.add(newDownDirt);
						}else{
							this.vacuum1.setUnder(new CleanHallway(Constants.CLEAN, vacuum1.getRow(), vacuum1.getColumn()));
							this.vacuum2.setUnder(new CleanHallway(Constants.CLEAN, vacuum2.getRow(), vacuum2.getColumn()));
						}
						dustball.moveTo(dustball.getRow() + Constants.DOWN, dustball.getColumn());
						this.grid.setCell(dustball.getRow(), dustball.getColumn(), dustball);
						break;
					case "R":
						if (!vacuum1.getUnder().equals(dustball) && !vacuum2.getUnder().equals(dustball)){
							this.dirts.remove(this.grid.getCell(dustball.getRow(), dustball.getColumn() + Constants.RIGHT));
							Dirt newRightDirt = new Dirt(Constants.DIRT, dustball.getRow(), dustball.getColumn(), Constants.DIRT_SCORE);
							this.grid.setCell(dustball.getRow(), dustball.getColumn(), newRightDirt);
							this.dirts.add(newRightDirt);
						}else{
							this.vacuum1.setUnder(new CleanHallway(Constants.CLEAN, vacuum1.getRow(), vacuum1.getColumn()));
							this.vacuum2.setUnder(new CleanHallway(Constants.CLEAN, vacuum2.getRow(), vacuum2.getColumn()));
						}
						dustball.moveTo(dustball.getRow(), dustball.getColumn() + Constants.RIGHT);
						this.grid.setCell(dustball.getRow(), dustball.getColumn(), dustball);
						break;
					case "L":
						if (!vacuum1.getUnder().equals(dustball) && !vacuum2.getUnder().equals(dustball)){
							this.dirts.remove(this.grid.getCell(dustball.getRow(), dustball.getColumn() + Constants.LEFT));
							Dirt newLeftDirt = new Dirt(Constants.DIRT, dustball.getRow(), dustball.getColumn(), Constants.DIRT_SCORE);
							this.grid.setCell(dustball.getRow(), dustball.getColumn(), newLeftDirt);
							this.dirts.add(newLeftDirt);
						}else{
							this.vacuum1.setUnder(new CleanHallway(Constants.CLEAN, vacuum1.getRow(), vacuum1.getColumn()));
							this.vacuum2.setUnder(new CleanHallway(Constants.CLEAN, vacuum2.getRow(), vacuum2.getColumn()));
						}
						dustball.moveTo(dustball.getRow(), dustball.getColumn() + Constants.LEFT);
						this.grid.setCell(dustball.getRow(), dustball.getColumn(), dustball);
						break;
	   			}
    		}
    	}
    	// Return whether the vacuum has moved or not
    	return hasMoved;
    }
    
    /**
     * Determines whether this location on the grid is a valid move for a vacuum.
     * @param row the row of this location to check
     * @param column the column of this location to check
     * @return returns a boolean whether the location is a valid move for a vacuum
     */
    private boolean validMove(int row, int column){
    	return !(this.grid.getCell(row, column).toString().equals(String.valueOf(Constants.WALL)) 
    			|| this.grid.getCell(row, column).toString().equals(String.valueOf(Constants.P1)) 
    			|| this.grid.getCell(row, column).toString().equals(String.valueOf(Constants.P2)));
    }
    
    /**
     * Determines whether this location on this grid is a valid move for a DustBall.
     * @param row the row of this location to check
     * @param column the row of this location to check
     * @return returns the boolean whether the location is a valid move for a DustBall
     */
    private boolean validDustMove(int row, int column){
    	return !(this.grid.getCell(row, column).toString().equals(String.valueOf(Constants.WALL)) 
    			|| this.grid.getCell(row, column).toString().equals(String.valueOf(Constants.P1)) 
    			|| this.grid.getCell(row, column).toString().equals(String.valueOf(Constants.P2))
    			|| this.grid.getCell(row, column).toString().equals(String.valueOf(Constants.DUST_BALL))
    			|| this.grid.getCell(row, column).toString().equals(String.valueOf(Constants.DUMPSTER)));
    }
    
    /**
     * Determine whether this game grid contains anymore dirt on it.
     * @return returns boolean true or false whether there exists dirt
     */
    private boolean existsDirt(){
    	boolean existsDirtFlag = false;
    	
    	// Loop through entire grid to check for dirt or dustballs
    	for (int y = 0;y < this.grid.getNumRows(); y++){
    		for (int x = 0; x < this.grid.getNumColumns(); x++){
    			if (this.grid.getCell(y, x).toString().equals(String.valueOf(Constants.DIRT))
    					|| this.grid.getCell(y, x).toString().equals(String.valueOf(Constants.DUST_BALL))){
    				existsDirtFlag = true;
    			}
    		}	
    	}
    	return existsDirtFlag;
    }
    
    /**
     * Moves the Vacuum in correct order by modifying the grid and the vacuum,
     * returns whether the vacuum has moved if it was a valid move or not.
     * @param vacuum the vacuum that will be moved to a location
     * @param row the row of the location desired to move this vacuum in
     * @param column the column of the location desired to movet his vacuum in
     * @return return bool representing whether vacuum has moved or not
     */
    private boolean moveVacuum(Vacuum vacuum, int row, int column){
		if (validMove(row, column)){
			this.grid.setCell(vacuum.getRow(), vacuum.getColumn(), vacuum.getUnder());
			vacuum.setUnder(this.grid.getCell(row, column));
			vacuum.moveTo(row, column);
			this.grid.setCell(vacuum.getRow(), vacuum.getColumn(), vacuum);
			return true;
		}else{
			return false;
		}
    }
    
    /**
     * Clean what is under both Vacuums by modifying the vacuums and 
     * the grid of this game.
     */
    private void cleanBothVacuum(){
    	//if player 1 or player 2 is over a dirt or dustball: clean up
    	if (vacuum1.getUnder().toString().equals(String.valueOf(Constants.DIRT))){
    		if (this.vacuum1.clean(Constants.DIRT_SCORE)){
        		this.dirts.remove(vacuum1.getUnder());
        		this.vacuum1.setUnder(new CleanHallway(Constants.CLEAN, this.vacuum1.getRow(), this.vacuum1.getColumn()));
        		this.grid.setCell(vacuum1.getRow(), vacuum1.getColumn(), this.vacuum1);
    		}
    	}else if (vacuum1.getUnder().toString().equals(String.valueOf(Constants.DUST_BALL))){
    		if (this.vacuum1.clean(Constants.DUST_BALL_SCORE)){
        		this.dirts.remove(vacuum1.getUnder());
        		this.vacuum1.setUnder(new CleanHallway(Constants.CLEAN, this.vacuum1.getRow(), this.vacuum1.getColumn()));
        		this.grid.setCell(vacuum1.getRow(), vacuum1.getColumn(), this.vacuum1);
    		}
    	}else if (vacuum1.getUnder().toString().equals(String.valueOf(Constants.DUMPSTER))){
    		// If over the dumpster empty the vacuum
    		vacuum1.empty();
    	}else if (vacuum2.getUnder().toString().equals(String.valueOf(Constants.DIRT))){
    		if (vacuum2.clean(Constants.DIRT_SCORE)){
        		this.dirts.remove(vacuum2.getUnder());
        		this.vacuum2.setUnder(new CleanHallway(Constants.CLEAN, this.vacuum2.getRow(), this.vacuum2.getColumn()));
        		this.grid.setCell(vacuum2.getRow(), vacuum2.getColumn(), this.vacuum2);
    		}
    	}else if (vacuum2.getUnder().toString().equals(String.valueOf(Constants.DUST_BALL))){
    		if (this.vacuum2.clean(Constants.DUST_BALL_SCORE)){
        		this.dirts.remove(vacuum2.getUnder());
        		this.vacuum2.setUnder(new CleanHallway(Constants.CLEAN, this.vacuum2.getRow(), this.vacuum2.getColumn()));
        		this.grid.setCell(vacuum2.getRow(), vacuum2.getColumn(), this.vacuum2);
    		}
    	}else if (vacuum2.getUnder().toString().equals(String.valueOf(Constants.DUMPSTER))){
    		// If over a dumpster empty the vacuum
    		vacuum2.empty();
    	}
    }
    
    
    
    
    
    
}
