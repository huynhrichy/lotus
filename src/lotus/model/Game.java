package lotus.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Game {
	private Board board;
	private List<Player> players;
	private boolean gameWon;
	private char activePlayerID;

	public Game() {
		this.board = new Board();

		this.players = new ArrayList<Player>();

		this.gameWon = false;

		this.activePlayerID = ' ';
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

	public Board getBoard() {
		return board;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public boolean isGameWon() {
		return gameWon;
	}

	public void setGameWon(boolean gameWon) {
		this.gameWon = gameWon;
	}

	public char getActivePlayerID() {
		return activePlayerID;
	}

	public void setActivePlayerID(char activePlayerID) {
		this.activePlayerID = activePlayerID;
	}

	// public boolean isTop

	public void doMove(Space spaceOfMovingPiece) {
		System.out.println("Valid move!");
	}

	public void switchActivePlayerID() {

		if (activePlayerID == players.get(players.size() - 1).getPlayerID()) {
			activePlayerID = players.get(0).getPlayerID();
		} else {
			for (int playerNumber = 0; playerNumber < players.size(); ++playerNumber) {
				
				if (players.get(playerNumber).getPlayerID() == activePlayerID) {

					activePlayerID = players.get(playerNumber + 1)
							.getPlayerID();

					return;
				}

			}

		}
	}

}
