package sprites;

/**
 * The class to manage the Dirt Sprite. 
 * @author g5mazloo
 */
public class Dirt extends Sprite{
	protected int value;
	
	/**
	 * Initialize a new Dirt sprite.
	 * @param symbol the symbol of the Dirt sprite
	 * @param row the row of the position of the Dirt sprite.
	 * @param column the column of the position of the Dirt sprite.
	 * @param value the score value of a Dirt sprite.
	 */
	public Dirt(char symbol, int row, int column, int value) {
		super(symbol, row, column);
		this.value = value;
	}
	
	/**
	 * Gets the value of this sprite.
	 * @return the value of this Dirt instance.
	 */
	public int getValue() {
		return value;
	}
	
	
	
}
