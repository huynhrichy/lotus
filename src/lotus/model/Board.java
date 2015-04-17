package lotus.model;

/**
 * Represents the board game's layout and spaces.
 * 
 * @author Richard
 *
 */
public class Board {
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
