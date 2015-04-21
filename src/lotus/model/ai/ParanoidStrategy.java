package lotus.model.ai;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lotus.model.Game;
import lotus.model.Move;
import lotus.model.Player;

public class ParanoidStrategy extends Strategy implements Serializable {

	public ParanoidStrategy(Game game, Player player) {
		super(game, player);
		// TODO Auto-generated constructor stub
	}



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
			int childValue = getParanoidValue(new Node(game, child, false), 1);

			// child.setValue(childValue);

			if (childValue > bestValue) {
				indexOfBestMove = possibleMoves.indexOf(child);
				bestValue = childValue;
			}
		}

		nextMove = possibleMoves.get(indexOfBestMove);

		return nextMove;
	}

	private int getParanoidValue(Node node, int depth) {
		int value = 0;

		if (isTerminalNodeReached(node) || depth > searchDepth) {
			return getHeuristicValue(node);
		}

		List<Node> childrenNodes = getChildrenNodes(node.getGame());

		if (node.isMaximizer()) {
			value = -99999;
			for (Node child : childrenNodes) {
				value = Math.max(value, getParanoidValue(child, depth + 1));
			}
		} else {
			value = 99999;
			for (Node child : childrenNodes) {
				value = Math.min(value, getParanoidValue(child, depth + 1));
			}
		}

		return value;
	}
	

	private int getHeuristicValue(Node node) {

		//return node.getMove().getSpace().getHeight();

		return node.getGame().getHeightOfHighestStack(
				node.getGame().getPlayerIndex(player.getPlayerID()));
	}
	
	public String toString() {
		return "(Paranoid)";
	}
}
