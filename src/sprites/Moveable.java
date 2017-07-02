package sprites;

/**
 * Interface class to be used to make sprites move able.
 * @author g5mazloo
 *
 */
public interface Moveable {
	
	/**
	 * Moves the sprite to a new location.
	 * @param row the row of the new location to move the Sprite
	 * @param column the column of the new location to move the Sprite
	 */
	public void moveTo(int row, int column);
}
