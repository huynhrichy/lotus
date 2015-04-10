package lotus.controller;

import java.util.Scanner;

import lotus.model.Game;
import lotus.view.View;

public class Controller implements Runnable {
	private Game game;
	private View view;
	private Scanner scanner;
	private char currentPlayer;

	// private String input;

	public Controller() {
		this.game = new Game();
		this.view = new View();
		this.scanner = new Scanner(System.in);
		this.currentPlayer = ' ';
		// this.input = "";
	}

	private void initializeGame() {
		System.out.println("Welcome to LOTUS. Will there be 2, 3, or 4 players?");

		int numberOfPlayers = scanner.nextInt();

		game.addPlayers(numberOfPlayers);
		
		currentPlayer = 'A';
	}

	public void run() {
		initializeGame();
		
		
	}
}
