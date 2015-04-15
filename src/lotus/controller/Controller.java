package lotus.controller;

import java.util.Scanner;

import lotus.model.Game;
import lotus.model.Path;
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
		System.out
				.println("Welcome to LOTUS. Will there be 2, 3, or 4 players?");

		int numberOfPlayers = -1;

		while (numberOfPlayers < 2 || numberOfPlayers > 4) {
			numberOfPlayers = scanner.nextInt();
		}

		game.addPlayers(numberOfPlayers);

		game.setActivePlayerID(game.getPlayers().get(0).getPlayerID());
	}

	public void run() {
		initializeGame();

		view.printGame();

		while (!game.isGameWon()) {

			promptPlayerMove();

			game.switchActivePlayerID();

			view.printGame();
		}
	}

	public void promptPlayerMove() {
		String input = "";

		// boolean validInput = false;

		Space spaceOfMovingPiece = null;

		System.out.print("Enter the space ID of the piece you wish to move: ");

		while (spaceOfMovingPiece == null) {

			input = scanner.nextLine();

			if (input.length() == 0) {
				continue;
			}

			System.out.println();

			Path selectedPath = null;

			char selectedPathID = input.charAt(0);

			switch (selectedPathID) {
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
				continue;
			}

			for (Space space : selectedPath.getSpaces()) {
				if (input.equals(space.getSpaceID())
						&& !space.getPieces().isEmpty()
						&& space.getTopPiece().getOwnerID() == game
								.getActivePlayerID()) {

					spaceOfMovingPiece = space;
					continue;
				}
			}
		}

		game.doMove(spaceOfMovingPiece);

	}

}
