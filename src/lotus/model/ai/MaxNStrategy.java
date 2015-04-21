package lotus.model.ai;

import java.util.List;

import lotus.model.Game;
import lotus.model.Move;
import lotus.model.Player;

public class MaxNStrategy extends Strategy {

	public MaxNStrategy(Game game, Player player) {
		super(game, player);
	}

	public Move getNextMove() {
		int bestValue = -99999;
		int indexOfAlternateMove = 0;
		List<Node> childrenNodes = getChildrenNodes(game); // !!
		Move nextMove = null;
		int indexOfChosenMove = 0;

		List<Move> possibleMoves = game.getPossibleMoves();
		for (Move child : possibleMoves) {
			
			Node newNode = new Node(game, child, false);
			int[] childValues = getMaxNValues(newNode, 1);
			indexOfAlternateMove = possibleMoves.size() - 1;
			
			int childValue = childValues[game.getOpponentIndex()];

			if (childValue > bestValue) { //

				indexOfChosenMove = possibleMoves.indexOf(child);
				bestValue = childValues[game.getOpponentIndex()];
			} else {
				//indexOfChosenMove = possibleMoves.indexOf(indexOfAlternateMove);
			}

		}

		nextMove = possibleMoves.get(indexOfChosenMove);

		return nextMove;
	}

	private int[] getHeuristicValues(Node node) {
		int[] values = new int[game.getPlayers().size()];
		
		for (int playerIndex = 0; playerIndex < game.getPlayers().size(); ++playerIndex) {
			values[playerIndex] = game.getHeightOfHighestStack(playerIndex);
		}

		return values;
	}

	private int[] getMaxNValues(Node node, int depth) {

		if (isTerminalNodeReached(node) || depth > searchDepth) {
			return getHeuristicValues(node);
		}

		int value = -99999;

		int[] values = new int[game.getPlayers().size()];

		List<Node> childrenNodes = getChildrenNodes(node.getGame());

		for (Node child : childrenNodes) {
			int temporaryValue = value;

			value = Math
					.max(value, getMaxNValues(child, depth + 1)[game.getOpponentIndex()]);

			if (temporaryValue != value) {
				values = getMaxNValues(child, depth + 1);
			}

		}

		return values;
	}

	public String toString() {
		return "(Max-N)   ";
	}
}
