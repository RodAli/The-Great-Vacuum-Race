package ui;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import game.VacuumGame;
import game.Constants;
import java.util.Scanner;

/**
 * Class to manage the text user interface of this game.
 * @author g5mazloo
 */
public class TextUI implements UI{
	private VacuumGame game;
	
	/**
	 * Initializes an instance of the text user interface.
	 * @param game instance of a VacuumGame
	 */
	public TextUI(VacuumGame game) {
		super();
		this.game = game;
	}
	
	/**
	 * Launch the text user interface for this vacuum game.
	 */
	@Override
	public void launchGame() {
		while (!this.game.gameOver()){
			System.out.println(this.game.getGrid().toString());
			Scanner sc = new Scanner(System.in);
			String key = sc.nextLine();
			if (key.length() == 1 && validKey(key)){
				this.game.move(key.charAt(0));
			}
		}
	}
	
	/**
	 * Display the winner and end the game of the text user interface
	 * by displaying a message declaring the winner.
	 */
	@Override
	public void displayWinner() {
	    if (!this.game.gameOver()) {
	        return;
	    }
	    
	    int won = this.game.getWinner();
		String message;

		if (won == 1) {
			message = "Congratulations Player 1! You won the game with a score of " + 
					this.game.getVacuumOne().getScore() + ".";
		} else { 
			message = "Congratulations Player 2! You won the game with a score of " + 
					this.game.getVacuumTwo().getScore() + ".";
		}
		System.out.println(message);
	}
	
	/**
	 * Private method to assist in determine whether a valid key move is pressed.
	 * @param key the string of the key pressed.
	 * @return
	 */
	private boolean validKey(String key){
		char character = key.charAt(0);
		return (character == Constants.P1_UP
				|| character == Constants.P1_DOWN
				|| character == Constants.P1_RIGHT
				|| character == Constants.P1_LEFT
				|| character == Constants.P2_UP
				|| character == Constants.P2_LEFT
				|| character == Constants.P2_RIGHT
				|| character == Constants.P2_DOWN);
	}
	
	
}
