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

	private Path getSelectedPath(char pathID) {
		Path selectedPath = null;

		switch (pathID) {
		case 'x':
			selectedPath = board.getPathX();
			break;
		case 'y':
			selectedPath = board.getPathY();
			break;
		case 'z':
			selectedPath = board.getPathZ();
			break;
		default:
			break;
		}

		return selectedPath;
	}

	public boolean isAMoveAvailable() {
		Player activePlayer = players.get(getActivePlayerIndex());

		for (Space stack : activePlayer.getStartingStacks()) {
			if (!stack.isEmpty()) {
				return true;
			}
		}

		if (isAMoveAvailableOnThisPath(board.getPathX())
				|| isAMoveAvailableOnThisPath(board.getPathY())
				|| isAMoveAvailableOnThisPath(board.getPathZ())) {

			return true;

		}

		return false;
	}

	private boolean isAMoveAvailableOnThisPath(Path path) {

		for (Space space : path.getSpaces()) {
			if (space.getTopPiece().getOwnerID() == activePlayerID) {
				return true;
			}
		}

		return false;
	}

	private int getIndexOfSpace(Space spaceOfMovingPiece, Path selectedPath) {
		int spaceIndex = -1;

		for (Space space : selectedPath.getSpaces()) {
			if (space.getSpaceID().equals(spaceOfMovingPiece.getSpaceID())) {
				spaceIndex = selectedPath.getSpaces().indexOf(space);
				break;
			}
		}

		return spaceIndex;
	}

	public void doMove(Space spaceOfMovingPiece, char pathID) {
		// System.out.println("Valid move!");

		// get the height of the stack on the space
		// move based on which path or if from starting stack
		// default, just go forward the size of the stack
		// 1. if from starting stack, go on chosen path (x or y) the size of the
		// stack
		// 2. if finish path x or y, go on z with any remaining moves
		// 3. if past path z, reduce number of player's pieces in the game
		// 4. also, trampoline

		Path selectedPath = getSelectedPath(pathID);

		int spacesToMove = spaceOfMovingPiece.getHeight();

		Piece movingPiece = spaceOfMovingPiece.popPiece();

		int spaceIndex = getIndexOfSpace(spaceOfMovingPiece, selectedPath);

		if (spaceIndex + spacesToMove > selectedPath.getLength() - 1) {
			if (selectedPath.isStartingPath()) {
				// go to z - change the path to it
				// change the number of steps to move

				//spacesToMove = spacesToMove
				//		- (selectedPath.getLength() - 1 - spaceIndex);
				
				// actually
				spacesToMove = spacesToMove - 1 - (selectedPath.getLength() - 1 - spaceIndex);

				spaceIndex = 0;

				selectedPath = board.getPathZ();

				// move doe
			} else {
				// decrement num of pieces for player

				players.get(getActivePlayerIndex())
						.decrementNumberOfPiecesRemaining();

				return;

			}
		}

		Space newSpace = selectedPath.getSpaces()
				.get(spaceIndex + spacesToMove);

		newSpace.pushPiece(movingPiece);

		// trampoline?!`2 do da whole ish AGAIN?!?! recurse sorta?!
		// if the gotten space wuz trampoline, recurse da ish using new data...

		if (newSpace.isTrampoline()) {
			doMove(newSpace, 'z');
		}

	}

	public boolean didActivePlayerWin() {

		return players.get(getActivePlayerIndex()).getNumberOfPiecesRemaining() == 0 ? true
				: false;

	}

	private int getActivePlayerIndex() {
		return ((int) activePlayerID - 65);
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
