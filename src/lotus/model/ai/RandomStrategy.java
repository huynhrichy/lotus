package lotus.model.ai;

import java.util.List;
import java.util.Random;

import lotus.model.Game;
import lotus.model.Move;
import lotus.model.Player;

public class RandomStrategy extends Strategy {

	public RandomStrategy(Game game, Player player) {
		super(game, player);
		// TODO Auto-generated constructor stub
	}

	public Move getNextMove() {
		Random random = new Random();

		List<Move> possibleMoves = game.getPossibleMoves();

		return possibleMoves.get(random.nextInt(possibleMoves.size()));
	}
	
	public String toString() {
		return "(Random)  ";
	}

}
