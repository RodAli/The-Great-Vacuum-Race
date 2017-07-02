package sprites;

import game.Constants;

/**
 * Abstract class to manage all sprites.
 */
public abstract class Sprite {
	
	protected char symbol;
	protected int row;
	protected int column;
	
	/**
	 * Creates an instance of a general Sprite
	 * @param symbol the symbol of this sprite
	 * @param row the row of the location of this Sprite
	 * @param column the column of the location of this Sprite
	 */
	public Sprite(char symbol, int row, int column) {
		this.symbol = symbol;
		this.row = row;
		this.column = column;
	}
	
	/**
	 * Get the symbol of this Sprite.
	 * @return returns the character symbol of this Sprite
	 */
	public char getSymbol() {
		return symbol;
	}
	
	/**
	 * Get the row location of this Sprite.
	 * @return returns the row of this Sprite
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * Get the column location of this Sprite.
	 * @return returns the column of this Sprite
	 */
	public int getColumn() {
		return column;
	}
	
	/**
	 * Returns the string representation of this Sprite.
	 * @return the string representation of this Sprite.
	 */
	@Override
	public String toString(){
		return Character.toString(symbol);
	}
	

}
