package algorithms.demo;

import algorithms.mazeGenerators.Position;
import algorithms.search.State;


/**
 * Description - A class adapter for The maze class to state
 * @param playerPosititon - Player position
 * @version 1.0
 * @see State
 * 
 *
 */
public class MazeState extends State {
	
	private Position playerPosition; 
	
	public MazeState(Position playerPosition) {
		
		this.setPlayerPosition(playerPosition);
		this.setDescription(playerPosition.toString());
		
	}

	public Position getPlayerPosition() {
		return playerPosition;
	}

	public void setPlayerPosition(Position playerPosition) {
		this.playerPosition = playerPosition;
	}
}
