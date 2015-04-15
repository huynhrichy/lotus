package lotus.model;

public class Piece {
	private char ownerID;

	public Piece(char playerID) {
		this.ownerID = playerID;
	}

	public String toString() {
		return ownerID + "";
	}

	public char getOwnerID() {
		return ownerID;
	}
}
