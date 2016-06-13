package algorithms.search;

import java.util.HashMap;

/**
 * description: An interface which every search problem should implement 
 * @version 1.0
 * @param non
 * @return non
 * @throws non
 */

public interface Searchable {
	
	/**
	 * description: The Start position - Holds a start position, in the maze problem is the position which the player start the maze
	 * @version 1.0
	 * @param non
	 * @return non
	 * 
	 */
	State getStartState();
	
	/**
	 * description: The Goal Position- Holds a Goal Position, In the maze is the end of the maze
	 * @version 1.0
	 * @param non
	 * @return non
	 * @see Maze3d
	 * @see State
	 */
	State getGoalState();
	
	/**
	 * description: This method Get a State and return an HashMa of all the possible actions  
	 * @version 1.0
	 * @param state
	 * @return HashMap<State> 
	 */
	HashMap<Action,State> getAllPossibaleActions(State state);
	
}
