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

	// set maximizer hurr
	private List<Node> getChildrenNodes(Game game) {
		List<Node> childrenNodes = new ArrayList<Node>();

		List<Move> possibleMoves = game.getPossibleMoves();

		for (Move move : possibleMoves) {
			Game newGameState = (Game) deepClone(game);

			// switch aktiv playa


			//Move newMove = new Move(move.getSpace(), move.getPathID()); // ?? EH?!!??! i got movez...

			// set maximizer hurr
			

			//newGameState.doMove(move); // ?!?!?
			
			
			// EH?!?!?!
			//if (move.getSpace().getHeight() <= 0) {
			//	continue;
			//}

			boolean maximizer = newGameState.getActivePlayerID() == player
					.getPlayerID() ? true : false;
			
			newGameState.switchActivePlayerID();
			
			Node node = new Node(newGameState, move, maximizer);

			childrenNodes.add(node);
		}

		return childrenNodes;
	}

	public Move getNextMove() {
		int bestValue = -99999;

		Move nextMove = null;

		// List<Space> possibleSpaces = game.get

		// le first node?
		 //Node rootNode = new Node(game, null, true);

		List<Node> childrenNodes = getChildrenNodes(game); // !!!
		
		//List<Node> childrenNodes = new ArrayList<Node>(); // ...

		// !!! chekkk
		for (Node child : childrenNodes) {
			int childValue = getParanoidValue(child, 1);

			// child.setValue(childValue);

			if (childValue > bestValue) {
				nextMove = child.getMove();
				bestValue = childValue;
			}
		}

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
}
