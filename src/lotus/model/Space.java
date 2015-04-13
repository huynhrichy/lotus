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

	public Space() {
		this.pieces = new ArrayList<Piece>();
		this.trampoline = false;
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
	
	
}
