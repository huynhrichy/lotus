package lotus.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Player {
	private char playerID;
	private List<Deque<Piece>> startingStacks;

	public Player(char playerID, int numberOfPlayers) {
		this.playerID = playerID;
		this.startingStacks = new ArrayList<Deque<Piece>>();

		initializeStartingStacks(numberOfPlayers);
		
		System.exit(0);

	}

	private void initializeStartingStacks(int numberOfPlayers) {
		int numberOfStacks = numberOfPlayers < 3 ? 4 : 3;
		
		int stackHeight = numberOfPlayers < 3 ? 4 : 3;

		for (int stackCount = 0; stackCount < numberOfStacks; ++stackCount) {
			Deque<Piece> stack = new ArrayDeque<Piece>();

			for (int stackHeightCount = 0; stackHeightCount < stackHeight; ++stackHeightCount) {
				stack.add(new Piece(playerID));
			}

			--stackHeight;
			
			startingStacks.add(stack);
		}
	}

}
