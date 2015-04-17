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
	private boolean startingPath;

	public Path(char pathID, boolean startingPath) {
		this.pathID = pathID;
		this.startingPath = startingPath;
		this.spaces = new ArrayList<Space>();

		int numberOfSpaces = startingPath ? 3 : 11;

		for (int spaceIndex = 0; spaceIndex < numberOfSpaces; ++spaceIndex) {
			this.spaces.add(new Space(pathID + "" + spaceIndex));
		}
	}

	public List<Space> getSpaces() {
		return spaces;
	}

	public int getLength() {
		return spaces.size();
	}

	public boolean isStartingPath() {
		return startingPath;
	}
	
	public char getPathID() {
		return pathID;
	}

}
