package lotus.model;

import java.util.Set;
import java.util.TreeSet;

/**
 * Represents the overall Lotus game.
 * 
 * @author Richard
 *
 */
public class Game implements Runnable {
	private Board board;
	private Set<Player> players;

	/**
	 * Constructs a default instance of Game.
	 */
	private Game() {
		this.board = new Board();
		this.players = new TreeSet<Player>();
	}

	/**
	 * Resets the game to the beginning.
	 */
	private void resetGame() {

	}

	/**
	 * Moves a player's piece.
	 * 
	 * @param playerNumber
	 *            The number of the player whose piece will be moved.
	 * @param pathID
	 *            The ID of the path containing the piece to be moved.
	 * @param pathIndex
	 *            The index of the moving piece on its path.
	 */
	private void movePiece(int playerNumber, char pathID, int pathIndex) {
	}

	/**
	 * Checks if the given move is possible.
	 * 
	 * @param playerNumber
	 *            The number of the player whose piece will be moved.
	 * @param pathID
	 *            The ID of the path containing the piece to be moved.
	 * @param pathIndex
	 *            The index of the moving piece on its path.
	 * @return Whether or not the move is possible.
	 */
	private boolean isMovePossible(int playerNumber, char pathID, int pathIndex) {
		return false;
	}

	/**
	 * Calculates and returns how many a spaces a given piece can move based on
	 * the number of pieces it is stacked upon.
	 * 
	 * @param move
	 *            The move being made.
	 * @return The number of spaces this move can yield.
	 */
	private int getNumberOfSpacesToMove(Move move) {
		return 0;
	}

	/**
	 * Runs the game.
	 */
	public void run() {

	}
}
