package lotus.model;

import java.util.HashSet;
import java.util.Set;

/**
 * A Lotus player. Owns a set of pieces intended to move to the end of the board
 * game.
 * 
 * @author Richard
 *
 */
public class Player {
	//private int playerNumber;
	private Set<Piece> pieces;
	
	private Player() {
		//this.playerNumber = 0;
		this.pieces = new HashSet<Piece>();
	}
}
