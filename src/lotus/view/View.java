package lotus.view;

import java.util.Deque;
import java.util.List;

import lotus.model.Game;
import lotus.model.Path;
import lotus.model.Piece;
import lotus.model.Player;
import lotus.model.Space;

// mainly for a view of the game; controller will print out user prompts
public class View {
	private Game game;

	public View(Game game) {
		this.game = game;
	}

	public void printGame() {
		// takes in a board object?

		printPath(game.getBoard().getPathX(), 'x', 0);
		// println("");

		printPath(game.getBoard().getPathZ(), 'z', 10);
		// println("");

		printPath(game.getBoard().getPathY(), 'y', 0);

		println("\n---\n");

		printPlayersInfo();

		println("\n---\n");

		println("Player " + game.getActivePlayerID() + "'s turn.");
	}

	private void printPath(Path path, char pathID, int indentAmount) {
		int stackDisplayHeight = 5;

		String indent = createIndent(indentAmount);

		println(indent + "PATH " + Character.toUpperCase(pathID));

		for (int stackLevel = stackDisplayHeight - 1; stackLevel >= 0; --stackLevel) {
			printStackLevel(path, stackLevel, indent);
		}

		print(indent);

		for (int spaceIndex = 0; spaceIndex < path.getSpaces().size(); ++spaceIndex) {

			String spaceString = pathID + "" + spaceIndex;

			spaceString += path.getSpaces().get(spaceIndex).isTrampoline() ? "!"
					: "-";

			print(spaceString);
		}

		println("");
	}

	private String createIndent(int indentSpaces) {
		String indent = "";

		for (int whiteSpace = 0; whiteSpace < indentSpaces; ++whiteSpace) {
			indent += " ";
		}

		return indent;
	}

	private void printStackLevel(Path path, int stackLevel, String indent) {
		for (int pathSpace = 0; pathSpace < path.getSpaces().size(); ++pathSpace) {

			String pieceSymbol = " ";

			if (stackLevel < path.getSpaces().get(pathSpace).getPieces().size()) {

				pieceSymbol = path.getSpaces().get(pathSpace).getPieces()
						.get(stackLevel).toString();
			}

			// print(indent + "[" + pieceSymbol + "]");

			if (pathSpace == 0) {
				// print(indent + "[" + stackLevel + "]"); // debug
				print(indent + "[" + pieceSymbol + "]");
			} else {
				// print("[" + stackLevel + "]"); // debug
				print("[" + pieceSymbol + "]");
			}
		}

		println("");
	}

	private void printPlayersStartingStacksLevel(int stackLevel) {
		int endOfLineIndex = 33;
		// int startingStackIndex = 0;

		for (Player player : game.getPlayers()) {
			for (Space startingStack : player.getStartingStacks()) {

				if (stackLevel < startingStack.getHeight()) {
					print("{" + player.getPlayerID() + "}");
				} else {
					print("   ");
				}

			}

			if (game.getPlayers().indexOf(player) != game.getPlayers().size() - 1) {
				print(" | ");
			}
		}

		println("");
	}

	private void printPlayersInfo() {
		int highestStartingStackSize = 0;

		for (Player player : game.getPlayers()) {
			int playershighestStartingStackSize = 0;

			for (Space startingStack : player.getStartingStacks()) {
				int stackSize = startingStack.getHeight();

				playershighestStartingStackSize = stackSize > playershighestStartingStackSize ? stackSize
						: playershighestStartingStackSize;

			}

			highestStartingStackSize = playershighestStartingStackSize > highestStartingStackSize ? playershighestStartingStackSize
					: highestStartingStackSize;
		}

		for (int stackLevel = highestStartingStackSize - 1; stackLevel >= 0; --stackLevel) {
			printPlayersStartingStacksLevel(stackLevel);
		}

		for (Player player : game.getPlayers()) {
			String startingStackIDs = "-i--j--k-";
			if (game.getPlayers().indexOf(player) != game.getPlayers().size() - 1) {
				startingStackIDs += " | ";
			}
			print(startingStackIDs);
		}

		println("");

		for (int playerNumber = 0; playerNumber < game.getPlayers().size() - 1; ++playerNumber) {
			print("          | ");
		}

		println("");

		for (Player player : game.getPlayers()) {
			String playerDescription = "PLAYER " + player.getPlayerID();
			if (game.getPlayers().indexOf(player) != game.getPlayers().size() - 1) {
				playerDescription += "  | ";
			}
			print(playerDescription);
		}

		println("");

		for (Player player : game.getPlayers()) {

			String piecesRemaining = player.getNumberOfPiecesRemaining()
					+ " pieces";

			if (game.getPlayers().indexOf(player) != game.getPlayers().size() - 1) {
				piecesRemaining += "  | ";
			}
			print(piecesRemaining);
		}

		println("");

	}

	private void println(Object object) {
		System.out.println(object);
	}

	private void print(Object object) {
		System.out.print(object);
	}
}
