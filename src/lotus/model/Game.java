package lotus.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import lotus.model.ai.BestReplyStrategy;
import lotus.model.ai.ComputerPlayer;
import lotus.model.ai.MaxNStrategy;
import lotus.model.ai.ParanoidStrategy;
import lotus.model.ai.RandomStrategy;
import lotus.model.ai.Strategy;

/**
 * Implements Serializable for deep copying.
 * 
 * @author Richard
 *
 */
public class Game implements Serializable {
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

	public void addPlayers(boolean humanPlaying, int numberOfComputerPlayers) {
		int startingASCIIValueOfPlayerID = 65;

		int numberOfPlayers = humanPlaying ? 1 : 0;

		numberOfPlayers += numberOfComputerPlayers;

		if (humanPlaying) {
			players.add(new Player((char) startingASCIIValueOfPlayerID,
					numberOfPlayers));
			++startingASCIIValueOfPlayerID;
		}

		for (int playerNumber = startingASCIIValueOfPlayerID/* + 1 */; playerNumber < numberOfPlayers
				+ startingASCIIValueOfPlayerID; ++playerNumber) {

			players.add(createComputerPlayer(playerNumber, numberOfPlayers));
		}
	}

	// for putting the strategy
	private ComputerPlayer createComputerPlayer(int playerNumber,
			int numberOfPlayers) {
		ComputerPlayer computerPlayer = new ComputerPlayer((char) playerNumber,
				numberOfPlayers);

		Random random = new Random();

		int strategyChoice = random.nextInt(3);

		Strategy strategy = null;

		switch (strategyChoice) {
		case 0:
			strategy = new ParanoidStrategy(this, computerPlayer);
			break;
		case 1:
			strategy = new MaxNStrategy(this, computerPlayer);
			break;
		case 2:
			strategy = new BestReplyStrategy(this, computerPlayer);
			break;
		default:
			strategy = new MaxNStrategy(this, computerPlayer);
			break;
		}

		computerPlayer.setStrategy(strategy);

		return computerPlayer;
	}

	// for an all human game
	public void addPlayersForHumanOnlyGame(int numberOfPlayers) {
		int startingASCIIValue = 65;

		for (int playerNumber = startingASCIIValue; playerNumber < numberOfPlayers
				+ startingASCIIValue; ++playerNumber) {
			players.add(new Player((char) playerNumber, numberOfPlayers));
		}
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

	public Player getActivePlayer() {
		return players.get(getActivePlayerIndex());
	}

	// deprecate cuz of getactiveplayer now? meh
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
			if (!space.isEmpty()
					&& space.getTopPiece().getOwnerID() == activePlayerID) {
				return true;
			}
		}

		return false;
	}

	// hurrr
	private int getIndexOfSpace(Space spaceOfMovingPiece, Path selectedPath) {
		int spaceIndex = -1; // instead of -1, 0? -1 good for starting!!!

		for (Space space : selectedPath.getSpaces()) {
			if (space.getSpaceID().equalsIgnoreCase(
					spaceOfMovingPiece.getSpaceID())) {
				spaceIndex = selectedPath.getSpaces().indexOf(space);
				break;
			}
		}

		return spaceIndex;
	}

	public void doMove(Move move) {
		// System.out.println("Valid move!");

		// get the height of the stack on the space
		// move based on which path or if from starting stack
		// default, just go forward the size of the stack
		// 1. if from starting stack, go on chosen path (x or y) the size of the
		// stack
		// 2. if finish path x or y, go on z with any remaining moves
		// 3. if past path z, reduce number of player's pieces in the game
		// 4. also, trampoline

		Space spaceOfMovingPiece = move.getSpace();
		char pathID = move.getPathID();

		Path selectedPath = getSelectedPath(pathID);

		int spacesToMove = spaceOfMovingPiece.getHeight();

		Piece movingPiece = spaceOfMovingPiece.popPiece();

		int spaceIndex = getIndexOfSpace(spaceOfMovingPiece, selectedPath);

		if (spaceIndex + spacesToMove > selectedPath.getLength() - 1) {
			if (selectedPath.isStartingPath()) {
				// go to z - change the path to it
				// change the number of steps to move

				// spacesToMove = spacesToMove
				// - (selectedPath.getLength() - 1 - spaceIndex);

				// actually
				spacesToMove = spacesToMove - 1
						- (selectedPath.getLength() - 1 - spaceIndex);

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
			doMove(new Move(newSpace, 'z'));
		}

	}

	public boolean didActivePlayerWin() {

		return players.get(getActivePlayerIndex()).getNumberOfPiecesRemaining() == 0 ? true
				: false;

	}

	// could prob change this to just get the active player... later
	public int getActivePlayerIndex() {
		return getPlayerIndex(activePlayerID);
	}

	public int getPlayerIndex(char playerID) {
		return ((int) playerID - 65);
	}

	
	public int getOpponentIndex() {
		return new Random().nextInt(players.size());
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

	private List<Move> getPossibleMoves(Path path) {
		List<Move> possibleMoves = new ArrayList<Move>();

		for (Space space : path.getSpaces()) {
			if (!space.getPieces().isEmpty()
					&& space.getTopPiece().getOwnerID() == activePlayerID) {
				possibleMoves.add(new Move(space, path.getPathID()));
			}
		}

		return possibleMoves;
	}

	public List<Move> getPossibleMoves() {
		Player activePlayer = players.get(getActivePlayerIndex());

		List<Move> possibleMoves = new ArrayList<Move>();

		for (Space space : activePlayer.getStartingStacks()) {
			if (!space.isEmpty()) {
				possibleMoves.add(new Move(space, 'x'));
				possibleMoves.add(new Move(space, 'y'));
			}
		}

		possibleMoves.addAll(getPossibleMoves(board.getPathX()));
		possibleMoves.addAll(getPossibleMoves(board.getPathY()));
		possibleMoves.addAll(getPossibleMoves(board.getPathZ()));

		return possibleMoves;
	}

	public int getHeightOfHighestStack(int playerIndex) {
		int bestHeight = 0;

		for (Space stack : players.get(playerIndex).getStartingStacks()) {

			if (stack.getHeight() > bestHeight
					&& !stack.isEmpty()
					&& ((int) stack.getTopPiece().getOwnerID() - 65) == playerIndex) {

				bestHeight = stack.getHeight();

			}
		}

		for (Space stack : board.getPathX().getSpaces()) {

			if (stack.getHeight() > bestHeight
					&& !stack.isEmpty()
					&& ((int) stack.getTopPiece().getOwnerID() - 65) == playerIndex) {

				bestHeight = stack.getHeight();

			}
		}

		for (Space stack : board.getPathY().getSpaces()) {

			if (stack.getHeight() > bestHeight
					&& !stack.isEmpty()
					&& ((int) stack.getTopPiece().getOwnerID() - 65) == playerIndex) {

				bestHeight = stack.getHeight();

			}
		}

		for (Space stack : board.getPathZ().getSpaces()) {

			if (stack.getHeight() > bestHeight
					&& !stack.isEmpty()
					&& ((int) stack.getTopPiece().getOwnerID() - 65) == playerIndex) {

				bestHeight = stack.getHeight();

			}
		}

		return bestHeight;
	}
}
