package lotus;

import lotus.controller.Controller;
import lotus.model.Game;
import lotus.view.View;

public class Lotus implements Runnable {
	private Controller lotus;

	public Lotus() {
		this.lotus = new Controller();
	}

	public void run() {
		new Thread(lotus).start();
	}

	public static void main(String[] args) {
		new Thread(new Lotus()).start();
	}
}
