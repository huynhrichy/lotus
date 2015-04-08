package lotus.model;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;


/**
 * Represents a space on the board.  
 * @author Richard
 *
 */
public class Space {
	private Deque<Piece> pieces;
	private boolean trampoline;
	
	private Space() {
		this.pieces = new ArrayDeque<Piece>();
		this.trampoline = false;
	}
}
