package lotus.model;


/**
 * Represents a move a player can make with a piece on the board.
 * 
 * @author Richard
 *
 */
public class Move {
	private Space space;
	private char pathID;
	
	public Move(Space space, char pathID) {
		this.space = space;
		this.pathID = pathID;
	}

	public Space getSpace() {
		return space;
	}

	public char getPathID() {
		return pathID;
	}
	
	
}
