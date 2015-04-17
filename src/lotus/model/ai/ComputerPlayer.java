package lotus.model.ai;

import java.util.List;
import java.util.Random;

import lotus.model.Move;
import lotus.model.Player;
import lotus.model.Space;

public class ComputerPlayer extends Player {

	public ComputerPlayer(char playerID, int numberOfPlayers) {
		super(playerID, numberOfPlayers);
	}

	public Move chooseMove(List<Move> possibleMoves) {
		Random random = new Random();

		return possibleMoves.get(random.nextInt(possibleMoves.size()));
	}

}
