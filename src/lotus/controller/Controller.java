package lotus.controller;

import java.util.Scanner;

import lotus.model.Game;
import lotus.model.Move;
import lotus.model.Path;
import lotus.model.Player;
import lotus.model.Space;
import lotus.model.ai.ComputerPlayer;
import lotus.view.View;

public class Controller implements Runnable {
	private Game game;
	private View view;
	private Scanner scanner;

	// private String input;

	public Controller() {
		this.game = new Game();
		this.view = new View(game);
		this.scanner = new Scanner(System.in);
		// this.input = "";
	}

	private void decidePlayers() {
		System.out.print("Welcome to LOTUS. ");

		boolean humanPlaying = isHumanPlaying();

		int minimumNumberOfComputerPlayers = humanPlaying ? 2 : 3;

		int numberOfComputerPlayers = decideNumberOfComputerPlayers(minimumNumberOfComputerPlayers);

		game.addPlayers(humanPlaying, numberOfComputerPlayers);

		game.setActivePlayerID(game.getPlayers().get(0).getPlayerID());

	}

	private int decideNumberOfComputerPlayers(int minimumNumberOfComputerPlayers) {
		System.out.println("How many computer players will there be: "
				+ minimumNumberOfComputerPlayers + " or "
				+ (minimumNumberOfComputerPlayers + 1) + "?");

		int numberOfComputerPlayers = -1;

		while (numberOfComputerPlayers < minimumNumberOfComputerPlayers
				|| numberOfComputerPlayers > minimumNumberOfComputerPlayers + 1) {

			numberOfComputerPlayers = scanner.nextInt();

		}

		return numberOfComputerPlayers;
	}

	private boolean isHumanPlaying() {
		System.out.println("Will you be playing? [Y/N]");

		System.out
				.println("(You alone can either play against computers, or watch them play against each other.)");

		boolean validInput = false;
		boolean humanPlaying = false;
		String input = "";

		while (!validInput) {
			input = scanner.nextLine();

			if (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")) {
				validInput = true;
				humanPlaying = true;

			} else if (input.equalsIgnoreCase("no")
					|| input.equalsIgnoreCase("n")) {

				validInput = true;

			}
		}

		return humanPlaying;
	}

	// for humanz
	private int decideNumberOfPlayers() {
		System.out.println("Welcome to LOTUS. Will there be 3 or 4 players?");

		int numberOfPlayers = -1;

		while (numberOfPlayers < 3 || numberOfPlayers > 4) {
			numberOfPlayers = scanner.nextInt();
		}

		return numberOfPlayers;
	}

	private void initializeGame() {
		// for human only game
		// game.addPlayers(decideNumberOfPlayers());

		// game.addPlayers(humanPlaying, numberOfComputerPlayers);

		decidePlayers();
	}

	public void run() {
		initializeGame();

		view.printGame();

		while (!game.isGameWon()) {
			doPlayerTurn();

			update();
		}
	}

	private void doPlayerTurn() {
		if (game.isAMoveAvailable()) {

			if (game.getActivePlayer() instanceof ComputerPlayer) {
				// do computer move
				// atm just do a random 1? or the first available 1? or skip
				// turn?

				Move move = ((ComputerPlayer) game.getActivePlayer())
						.chooseMove(game.getPossibleMoves());

				game.doMove(move);
			} else {
				promptPlayerMove();
			}

		} else {

			System.out.println("No moves available for Player "
					+ game.getActivePlayerID() + "!");

		}
	}

	private void update() {
		if (game.didActivePlayerWin()) {
			finishGame();
		}

		game.switchActivePlayerID();

		view.printGame();
	}

	private void finishGame() {
		view.printGame();

		System.out.println("\nPlayer " + game.getActivePlayerID()
				+ " has won the game!");

		System.exit(0);
	}

	private void promptPlayerMove() {
		String input = "";

		// Move move = new

		// boolean validInput = false;

		Space spaceOfMovingPiece = null;
		char firstCharacter = ' ';
		char pathID = ' ';

		System.out
				.print("Make a move by entering a valid space ID, or a starting stack ID and a path X or Y. ");

		while (spaceOfMovingPiece == null) {

			input = scanner.nextLine();

			if (input.length() < 2) {
				continue;
			}

			System.out.println();

			firstCharacter = input.charAt(0);

			if (firstCharacter == 'i' || firstCharacter == 'j'
					|| firstCharacter == 'k') {

				spaceOfMovingPiece = getMoveFromStartingStack(input);
				pathID = input.charAt(1);

			} else if (firstCharacter == 'x' || firstCharacter == 'y'
					|| firstCharacter == 'z') {

				spaceOfMovingPiece = getMoveFromPath(input);
				pathID = input.charAt(0);
			} else {
				continue;
			}

		}

		// Move move = new Move(spaceOfMovingPiece, pathID)

		game.doMove(new Move(spaceOfMovingPiece, pathID));

	}

	private Space getMoveFromPath(String userInput) {
		Space spaceOfMovingPiece = null;

		Path selectedPath = null;

		switch (userInput.charAt(0)) {
		case 'x':
			selectedPath = game.getBoard().getPathX();
			break;
		case 'y':
			selectedPath = game.getBoard().getPathY();
			break;
		case 'z':
			selectedPath = game.getBoard().getPathZ();
			break;
		default:
			return null;
		}

		for (Space space : selectedPath.getSpaces()) {
			if (userInput.equalsIgnoreCase(space.getSpaceID())
					&& !space.getPieces().isEmpty()
					&& space.getTopPiece().getOwnerID() == game
							.getActivePlayerID()) {

				spaceOfMovingPiece = space;
				break;
			}
		}

		return spaceOfMovingPiece;
	}

	private Space getMoveFromStartingStack(String userInput) {
		// Space spaceOfMovingPiece = null;

		Player activePlayer = game.getPlayers().get(
				((int) game.getActivePlayerID()) - 65);

		Space spaceOfMovingPiece = activePlayer.getStartingStacks().get(
				(int) (userInput.charAt(0)) - 105);

		if (userInput.charAt(1) != 'x' && userInput.charAt(1) != 'y') {
			return null;
		}

		if (spaceOfMovingPiece.getHeight() == 0) {
			return null;
		}

		return spaceOfMovingPiece;
	}

}
