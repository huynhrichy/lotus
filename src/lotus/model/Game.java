package lotus.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Game {
	private Board board;
	private List<Player> players;

	public Game() {
		this.board = new Board();

		this.players = new ArrayList<Player>();
	}

	public void addPlayers(int numberOfPlayers) {
		int startingASCIIValue = 65;

		for (int playerNumber = startingASCIIValue; playerNumber < numberOfPlayers
				+ startingASCIIValue; ++playerNumber) {
			this.players.add(new Player((char) playerNumber, numberOfPlayers));
		}
	}

	private void resetGame() {

	}

	private void movePiece(int playerNumber, char pathID, int pathIndex) {
	}

	private boolean isMovePossible(int playerNumber, char pathID, int pathIndex) {
		return false;
	}

	private int getNumberOfSpacesToMove(Move move) {
		return 0;
	}
}
