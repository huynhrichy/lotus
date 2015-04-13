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
		this.pathX = new Path(3);
		this.pathY = new Path(3);
		this.pathZ = new Path(11);

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
