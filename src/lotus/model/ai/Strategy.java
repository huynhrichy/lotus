package lotus.model.ai;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Random;

import lotus.model.Game;
import lotus.model.Move;
import lotus.model.Player;

/**
 * Game and Player information are kept as attributes for the AI to make
 * informed decisions for the next move.
 * 
 * @author Richard
 *
 */
public abstract class Strategy implements Serializable {
	protected int searchDepth;
	protected Game game;
	protected Player player;

	public Strategy(Game game, Player player) {
		this.searchDepth = 3;
		this.game = game;
		this.player = player;
	}

	public abstract Move getNextMove();

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * This method makes a "deep clone" of any Java object it is given. By Alvin
	 * Alexander.
	 * 
	 * http://alvinalexander.com/java/java-deep-clone-example-source-code
	 */
	protected Object deepClone(Object object) {
		try {

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(object);

			ByteArrayInputStream bais = new ByteArrayInputStream(
					baos.toByteArray());

			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	protected boolean isTerminalNodeReached(Node node) {
		// maybe just check all the piece counts and see if one of them is zero?
		return node.getGame().didActivePlayerWin();
	}
	
	public Move getNextMoveRandomly() {
		Random random = new Random();

		List<Move> possibleMoves = game.getPossibleMoves();

		return possibleMoves.get(random.nextInt(possibleMoves.size()));
	}

	protected int getHeuristicValue(Node node) {

		return node.getMove().getSpace().getHeight();
	}
}
