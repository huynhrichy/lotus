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
	//private char pathID;
	private List<Space> spaces;
	
	private Path() {
		//this.pathID = '0';
		this.spaces = new ArrayList<Space>();
	}
}