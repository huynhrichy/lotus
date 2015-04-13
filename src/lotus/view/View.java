package lotus.view;

import lotus.model.Game;
import lotus.model.Path;

// mainly for a view of the game; controller will print out user prompts
public class View {
	private Game game;

	public View(Game game) {
		this.game = game;
	}

	public void printGame() {
		// takes in a board object?

		printPath(game.getBoard().getPathX(), 'x', 0);
		println("");

		printPath(game.getBoard().getPathZ(), 'z', 10);
		println("");

		printPath(game.getBoard().getPathX(), 'y', 0);
		println("");
	}

	private void printPath(Path path, char pathID, int indentAmount) {
		int stackDisplayHeight = 5;

		String indent = createIndent(indentAmount);

		for (int stackLevel = 0; stackLevel < stackDisplayHeight + 1; ++stackLevel) {

			for (int spaceIndex = 0; spaceIndex < path.getSpaces().size(); ++spaceIndex) {

				printStackLevel(path, stackLevel, indent);

				if (stackLevel == stackDisplayHeight + 1) {
					println(pathID + "" + spaceIndex + "-");
				}

			}

		}
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

			if (stackLevel <= path.getSpaces().get(pathSpace).getPieces()
					.size()) {

				print(indent
						+ "["
						+ path.getSpaces().get(pathSpace).getPieces()
								.get(stackLevel) + "]");
			}
		}

		println("");
	}

	private void println(Object object) {
		System.out.println(object);
	}

	private void print(Object object) {
		System.out.print(object);
	}

	public static void main(String[] args) {
		new View(new Game()).printGame();
	}
}
