package lotus.model;

import java.io.Serializable;

public class Piece implements Serializable {
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
