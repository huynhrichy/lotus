package lotus.model;

import java.io.Serializable;

/**
 * Represents the board game's layout and spaces. Implements Serializable for
 * deep cloning.
 * 
 * @author Richard
 *
 */
public class Board implements Serializable {
	private Path pathX, pathY, pathZ;

	public Board() {
		this.pathX = new Path('x', true);
		this.pathY = new Path('y', true);
		this.pathZ = new Path('z', false);

		this.pathZ.getSpaces().get(4).setTrampoline(true);
	}

	public Path getPathX() {
		return pathX;
	}

	public Path getPathY() {
		return pathY;
	}

	public Path getPathZ() {
		return pathZ;
	}

}
