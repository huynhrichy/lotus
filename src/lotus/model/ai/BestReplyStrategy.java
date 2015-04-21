package lotus.model.ai;

import java.util.List;

import lotus.model.Game;
import lotus.model.Move;
import lotus.model.Player;

public class BestReplyStrategy extends Strategy {

	public BestReplyStrategy(Game game, Player player) {
		super(game, player);
	}

	@Override
	public Move getNextMove() {
		int bestValue = -99999;

		Move nextMove = null;

		int indexOfBestMove = 0;

		List<Move> possibleMoves = game.getPossibleMoves();

		// List<Space> possibleSpaces = game.get

		// le first node?
		// Node rootNode = new Node(game, null, true);

		List<Node> childrenNodes = getChildrenNodes(game); // !!!

		// List<Node> childrenNodes = new ArrayList<Node>(); // ...

		// !!! chekkk
		for (Move child : possibleMoves) {
			int childValue = getBestReplyValue(new Node(game, child, false), 1);

			// child.setValue(childValue);

			if (childValue > bestValue) {
				indexOfBestMove = possibleMoves.indexOf(child);
				bestValue = childValue;
			}
		}

		nextMove = possibleMoves.get(indexOfBestMove);

		return nextMove;
	}

	private int getBestReplyValue(Node node, int depth) {
		int value = 0;

		if (isTerminalNodeReached(node) || depth > searchDepth) {
			return getHeuristicValue(node);
		}

		List<Node> childrenNodes = getChildrenNodes(node.getGame());

		if (node.isMaximizer()) {
			value = -99999;
			for (Node child : childrenNodes) {
				value = Math.max(value, getBestReplyValue(child, depth + 1));
			}
		} else {
			value = 99999;

			for (Player opponent : node.getGame().getPlayers()) {
				if (!opponent.equals(player)) {
					for (Node child : childrenNodes) {

						value = Math.min(value,
								getBestReplyValue(child, depth + 1));

					}
				}
			}
		}

		return value;
	}

	public String toString() {
		return "(BReply)  ";
	}

	private int getHeuristicValue(Node node) {
		return node.getGame().getHeightOfHighestStack(
				node.getGame().getPlayerIndex(player.getPlayerID()));
	}

}
