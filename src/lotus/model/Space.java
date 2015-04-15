package lotus.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

/**
 * Represents a space on the board.
 * 
 * @author Richard
 *
 */
public class Space {
	private List<Piece> pieces;
	private boolean trampoline;
	private String spaceID;

	public Space(String spaceID) {
		this.pieces = new ArrayList<Piece>();
		this.trampoline = false;
		this.spaceID = spaceID;
	}

	public List<Piece> getPieces() {
		return pieces;
	}

	public boolean isTrampoline() {
		return trampoline;
	}

	public void setTrampoline(boolean trampoline) {
		this.trampoline = trampoline;
	}

	public String getSpaceID() {
		return spaceID;
	}

	public void setSpaceID(String spaceID) {
		this.spaceID = spaceID;
	}

	public String toString() {
		return spaceID;
	}

	public Piece getTopPiece() {
		return pieces.get(pieces.size() - 1);
	}

}
