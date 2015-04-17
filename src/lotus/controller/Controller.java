package lotus.controller;

import java.util.Scanner;

import lotus.model.Game;
import lotus.model.Path;
import lotus.model.Player;
import lotus.model.Space;
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

	private void initializeGame() {
		System.out.println("Welcome to LOTUS. Will there be 3 or 4 players?");

		int numberOfPlayers = -1;

		while (numberOfPlayers < 3 || numberOfPlayers > 4) {
			numberOfPlayers = scanner.nextInt();
		}

		game.addPlayers(numberOfPlayers);

		game.setActivePlayerID(game.getPlayers().get(0).getPlayerID());
	}

	public void run() {
		initializeGame();

		view.printGame();

		while (!game.isGameWon()) {

			if (game.isAMoveAvailable()) {
				promptPlayerMove();
			} else {
				
				System.out.println("No moves available for Player "
						+ game.getActivePlayerID() + "!");
				
			}

			if (game.didActivePlayerWin()) {
				finishGame();
			}

			game.switchActivePlayerID();

			view.printGame();
		}
	}

	private void finishGame() {
		view.printGame();

		System.out.println("\nPlayer " + game.getActivePlayerID()
				+ " has won the game!");

		System.exit(0);
	}

	private void promptPlayerMove() {
		String input = "";

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

		game.doMove(spaceOfMovingPiece, pathID);

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
			if (userInput.equals(space.getSpaceID())
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

		/*
		 * switch (userInput.charAt(0)) { case 'i':
		 * 
		 * spaceOfMovingPiece = activePlayer.getStartingStacks().get(0);
		 * 
		 * break; case 'j':
		 * 
		 * spaceOfMovingPiece = activePlayer.getStartingStacks().get(1);
		 * 
		 * break; case 'k':
		 * 
		 * spaceOfMovingPiece = activePlayer.getStartingStacks().get(2);
		 * 
		 * break; default:
		 * 
		 * return null; }
		 */

		if (userInput.charAt(1) != 'x' && userInput.charAt(1) != 'y') {
			return null;
		}

		if (spaceOfMovingPiece.getHeight() == 0) {
			return null;
		}

		return spaceOfMovingPiece;
	}

}
