package sprites;

import game.Constants;

/**
 * Class to manage the Vacuum sprite that the player will control to
 * play the game.
 * @author g5mazloo
 */
public class Vacuum extends Sprite implements Moveable{
	private int score = Constants.INIT_SCORE;
	private int capacity;
	private int fullness = Constants.EMPTY;
	private Sprite under;
	
	/**
	 * Creates an instance of Vacuum sprite.
	 * @param symbol the character symbol representation of this Vacuum instance
	 * @param row the row location of this Vacuum
	 * @param column the column location of this Vacuum
	 * @param capacity the capacity of this Vacuum to hold dirt and dustball sprite
	 */
	public Vacuum(char symbol, int row, int column, int capacity) {
		super(symbol, row, column);
		this.capacity = capacity;
	}
	
	/**
	 * Cleans the what is under this Vacuum sprite if it has the capacity and 
	 * properly increment this Vacuum.
	 * @param score the score of what is being cleaned by this Vacuum
	 * @return
	 */
	public boolean clean(int score){
		
		// Clean if this Vacuum has the capacity
		if (this.fullness < this.capacity){
			this.score += score;
			this.fullness += Constants.FULLNESS_INC;
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Empty the Vacuum of its contents of dirt and dustball sprite.
	 */
	public void empty(){
		this.fullness = Constants.EMPTY;
	}
	
	/**
	 * Get the score of this Vacuum.
	 * @return the score this instance Vacuum has attained
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Get the sprite that is under this Vacuum instance.
	 * @return the Sprite that is under this Vacuum
	 */
	public Sprite getUnder() {
		return under;
	}
	
	/**
	 * Set the sprite that is under this Vacuum instance.
	 * @param under Sprite that will be set to be "under" this Vacuum instance
	 */
	public void setUnder(Sprite under) {
		this.under = under;
	}
	
	/**
	 * Move this instance of Vacuum to another location.
	 * @param row move the Vacuum to the location of this new row
	 * @param column move the Vacuum to the location of this new column
	 */
	public void moveTo(int row, int column) {
		this.row = row;
		this.column = column;
	}

	
	
	
	
	
}
