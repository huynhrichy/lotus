package lotus.model;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implements Serializable for deep copying.
 * 
 * @author Richard
 *
 */
public class Player implements Serializable {
	private char playerID;
	// private List<Deque<Piece>> startingStacks;
	private List<Space> startingStacks;
	private int numberOfPiecesRemaining;
	//private boolean computerPlayer;

	public Player(char playerID, int numberOfPlayers/*, boolean computerPlayer*/) {
		this.playerID = playerID;
		// this.startingStacks = new ArrayList<Deque<Piece>>();
		this.startingStacks = new ArrayList<Space>();
		this.numberOfPiecesRemaining = 0;
		//this.computerPlayer = computerPlayer;

		initializeStartingStacks(numberOfPlayers);

		for (Space stack : startingStacks) {
			numberOfPiecesRemaining += stack.getHeight();
		}

	}

	private void initializeStartingStacks(int numberOfPlayers) {
		int numberOfStacks = numberOfPlayers < 3 ? 4 : 3;

		int stackHeight = numberOfStacks;

		for (int stackCount = 0; stackCount < numberOfStacks; ++stackCount) {
			Space stack = new Space((char) (105 + stackCount) + "");

			for (int stackHeightCount = 0; stackHeightCount < stackHeight; ++stackHeightCount) {
				stack.pushPiece(new Piece(playerID));
			}

			--stackHeight;

			startingStacks.add(stack);
		}
	}

	public char getPlayerID() {
		return playerID;
	}

	public List<Space> getStartingStacks() {
		return startingStacks;
	}

	public int getNumberOfPiecesRemaining() {
		return numberOfPiecesRemaining;
	}

	public void decrementNumberOfPiecesRemaining() {
		if (numberOfPiecesRemaining > 0) {
			--numberOfPiecesRemaining;
		}
	}

/*	public boolean isComputerPlayer() {
		return computerPlayer;
	}*/

}
