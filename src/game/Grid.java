package game;

/**
 * Interface to be implemented when creating a grid.
 * Abstractly sets methods to be implemented when implementing a Grid.
 * @author g5mazloo
 */
public interface Grid<T> {
	
	/**
	 * Sets the cell of this grid in the location specified.
	 * @param row the row to set the cell of 
	 * @param column the column to set the cell of 
	 * @param item the item that will be set in the cell
	 */
	public void setCell(int row, int column, T item);
	
	/**
	 * Gets the cell of this grid that is specified. 
	 * @param row the row of the cell 
	 * @param column
	 * @return
	 */
	public T getCell(int row, int column);
	
	/**
	 * Get the number of rows on this grid.
	 * @return number of rows this grid has
	 */
	public int getNumRows();
	
	/**
	 * Get the number of columns on this grid
	 * @return the number of columns this grid has
	 */
	public int getNumColumns();
	
	/**
	 * Checks whether this grid is equal to the object passed in.
	 * @param other another object to be compared with this grid
	 * @return whether this grid is equal to the object passed in
	 */
	public boolean equals(Object other);
	
	/**
	 * Creates the string representation of this grid.
	 * @return the string representation of this grid
	 */
	public String toString();
}
