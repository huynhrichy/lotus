package lotus.model.ai;

import java.util.List;
import java.util.Random;

import lotus.model.Game;
import lotus.model.Move;
import lotus.model.Player;
import lotus.model.Space;

public class ComputerPlayer extends Player {
	private Strategy strategy;

	public ComputerPlayer(char playerID, int numberOfPlayers) {
		super(playerID, numberOfPlayers);
		this.strategy = null;
	}

	public Move chooseMove(List<Move> possibleMoves) {
		return strategy.getNextMove();
		//return strategy.getNextMoveRandomly();
	}

	public Strategy getStrategy() {
		return strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

}
