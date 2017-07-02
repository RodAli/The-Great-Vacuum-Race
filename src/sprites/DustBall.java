package sprites;

/**
 * Class to manage a DustBall sprite.
 * @author g5mazloo
 */
public class DustBall extends Dirt implements Moveable{
	
	/**
	 * Creates an instance of a DustBall
	 * @param symbol character representation of the DustBall sprite.
	 * @param row the row of the position of this DustBall sprite.
	 * @param column the column of the position of this DustBall sprite. 
	 * @param value the score value of a DustBall sprite.
	 */
	public DustBall(char symbol, int row, int column, int value) {
		super(symbol, row, column, value);
	}
	
	/**
	 * Move method to move this instance of dustball to a new location.
	 * @param row the row to move this DustBall
	 * @param column the column to move this DustBall
	 */
	public void moveTo(int row, int column){
		this.row = row;
		this.column = column;
	}
	
}
