package lotus.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a linear path of spaces on the Lotus board. Each space is a
 * potential move for a player.
 * 
 * @author Richard
 *
 */
public class Path {
	private char pathID;
	private List<Space> spaces;

	public Path(char pathID, int numberOfSpaces) {
		this.pathID = pathID;
		this.spaces = new ArrayList<Space>();

		for (int spaceIndex = 0; spaceIndex < numberOfSpaces; ++spaceIndex) {
			this.spaces.add(new Space(pathID + "" + spaceIndex));
		}
	}

	public List<Space> getSpaces() {
		return spaces;
	}

}
