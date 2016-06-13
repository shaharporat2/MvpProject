package algorithms.demo;

import java.util.ArrayList;
import java.util.HashMap;

import algorithms.mazeGenerators.Direction;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Action;
import algorithms.search.Searchable;
import algorithms.search.State;

/**
 * Description: custom the Maze3d to a Searchable Problem
 * @param Maze3d 
 * @param Movment_Cost
 * @see Maze3d
 *
 */

public class MazeAdapter implements Searchable {

	private Maze3d maze;
	private static final int MOVEMENT_COST  = 1;
	
	public MazeAdapter(Maze3d maze) {
		this.maze = maze;
	}
	
	@Override
	public State getStartState() {
			MazeState startState = new MazeState(maze.getStartPosition());
			return startState;
	}

	@Override
	public State getGoalState() {
		MazeState goalState = new MazeState(maze.getGoalPosition());
		return goalState;
	}
	
	
	/**
	 * Description Check the next position of the player   
	 * @param currPos
	 * @param dir	
	 * @return
	 */
	
	private Position getNextPosition(Position currPos, Direction dir){
		switch(dir){
		case UP:
			return new Position(currPos.getFloor() + 1,currPos.getRows(),currPos.getCols());
		case DOWN:
			return new Position(currPos.getFloor() - 1,currPos.getRows(),currPos.getCols());
		case FORWARD:
			return new Position(currPos.getFloor(),currPos.getRows(),currPos.getCols()+1);
		case BACKWARD:
			return new Position(currPos.getFloor(),currPos.getRows(),currPos.getCols()-1);
		case LEFT:
			return new Position(currPos.getFloor(),currPos.getRows() - 1,currPos.getCols());
		case RIGHT:
			return new Position(currPos.getFloor(),currPos.getRows()+ 1,currPos.getCols());	
		}
		return null;
	}

	/**
	 * Description: Get all actions the player able to do from state 
	 * @param state
	 * @return HashMap<Action, State>
	 */
	@Override
	public HashMap<Action, State> getAllPossibaleActions(State state) {
		MazeState mazeState = (MazeState)state;
		Position pos = mazeState.getPlayerPosition();
		ArrayList<Direction> directions = maze.getPossibleDirections(pos);
		
		HashMap<Action, State> actions = new HashMap<Action, State>();
		
		for (Direction d : directions) {
			Action action = new Action(d.toString(),MOVEMENT_COST);
			MazeState newState = new MazeState(getNextPosition(pos,d));
			
			actions.put(action, newState);
		}
		return actions;
	}
}
