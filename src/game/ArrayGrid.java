package game;

import sprites.Sprite;

/**
 * A class that implements the design of a grid of sprite.
 * This class is responsible for the follwing operations:
 * 1. Initiating a 2-dimensional grid filled with sprite
 * 2. Setting and getting cells
 * 3. Returning the number of columns and rows 
 * @author g5mazloo
 */
public class ArrayGrid<T> implements Grid<Sprite>{
	private Sprite[][] grid;
	
	/**
	 * Creates an instance of ArrayGrid based off of the number of rows 
	 * and columns.
	 * @param numRows the number of rows the array grid will be
	 * initialized with
	 * @param numColumns the number of columns the array will be
	 * initialized with
	 */
	public ArrayGrid(int numRows, int numColumns){
		this.grid = new Sprite[numRows][numColumns];
	}
	
	
	
	
	/**
	 * Sets the cell of this grid array to the sprite passed at 
	 * the specified location.
	 * @param row the row of the cell to set as a Sprite
	 * @param column the column of the cell to set as a Sprite
	 * @param item the Sprite object to set in the cell
	 */
	@Override
	public void setCell(int row, int column, Sprite item) {
		this.grid[row][column] = item;
	}

	
	/**
	 * Gets the sprite at the cell specified.
	 * @param row the row of the cell to get the Sprite from
	 * @param column the column of the cell to get the Sprite from
	 * @return returns the Sprite at the specified cell 
	 */
	@Override
	public Sprite getCell(int row, int column) {
		return this.grid[row][column];
	}
	
	/**
	 * Gets the number of rows in this ArrayGrid.
	 * @return number of rows in this ArrayGrid
	 */
	@Override
	public int getNumRows() {
		return this.grid.length;
	}

	
	/**
	 * Get the number of columns from this ArrayGrid.
	 * @return number of columns in this ArrayGrid.
	 */
	@Override
	public int getNumColumns() {
		return this.grid[0].length;
	}
	
	/**
	 * Determines whether this ArrayGrid is equivalent to another object.
	 * @param other object to determine whether it is equivalent to 
	 * @return returns boolean whether this array grid is equivalent or not
	 */
	@Override
	public boolean equals(Object other){
		return other instanceof ArrayGrid && this.toString().equals(other.toString());
	}
	
	/**
	 * Creates the string representation of this array grid.
	 * @return returns the string representation this grid
	 */
	@Override
	public String toString(){
		String rowString;
		String returnString = "";
		
		for(Sprite[] row : grid){
			rowString = "";
			for(Sprite item : row){
				rowString += item.toString();
			}
			returnString += rowString + "\n";
		}
		
		return returnString;
	}
	
}
