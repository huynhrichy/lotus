package lotus.model.ai;

import lotus.model.Game;
import lotus.model.Move;
import lotus.model.Space;

public class Node {
	private Game game;
	private Move move;
	//private int value; // mainly how many spaces the move can make, ie height
	private boolean maximizer;
	
	public Node(Game game, Move move, boolean maximizer) {
		this.game = game; // make a copy of the game doe
		this.move = move;
		//this.value = 0;
		this.maximizer = maximizer; // ?
		
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Move getMove() {
		return move;
	}

	public void setMove(Move move) {
		this.move = move;
	}
	
	public boolean isMaximizer() {
		return maximizer;
	}

	public void setMaximizer(boolean maximizer) {
		this.maximizer = maximizer;
	}
	
	
}
