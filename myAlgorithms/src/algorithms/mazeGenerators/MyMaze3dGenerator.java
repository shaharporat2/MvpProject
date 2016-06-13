package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;

public class MyMaze3dGenerator extends AbstractMaze3dGenerator {

	/**
	 * @version 1.0
	 * @param floor - This parameter defines number of floors in the maze
	 * @param rows - this parameter defines number of rows in each floor of the maze
	 * @param cols - this parameter defines number of cols in each floor of the maze
	 * @return time to generate the maze in string format
	 * @throws non
	 * 
	 * Description: This is a class that extends Abstract maze3d Generator and generate maze using DFS Algorithm
	 */
	
	private Maze3d myMaze;
	private Random rand =new Random();
	
	@Override
	public Maze3d generate(int floor, int rows, int cols) {
		myMaze=new Maze3d(floor, rows, cols);
		chooseStartPosition();
		DFS(myMaze.getStartPosition());
		goalPosion();
		return myMaze;
	}
	
	/**
	 * @version 1.0
	 * @return non
	 * @throws non
	 * @see Maze3d - setStartPosition
	 * Description: Choose randomly start position and set the start position using setStartPosition - Maze3d method
	 */
	
	
	
	public void chooseStartPosition(){
		int x = rand.nextInt(myMaze.getRows());
		while (x % 2 == 1){
			x = rand.nextInt(myMaze.getRows());
		}
		int z = rand.nextInt(myMaze.getCols());
		while (z % 2 == 1){
			z = rand.nextInt(myMaze.getCols());
		}
		int y = rand.nextInt(myMaze.getFloor());
		while (y == 0){
			y = rand.nextInt(myMaze.getFloor());
		}
		myMaze.setStartPosition(new Position(y, x, z));
	}


	/**
	 * @version 1.0
	 * @param pos - The method gets Start position in Position Utility format
	 * @return Array list of possible Directions
	 * @throws non
	 * @see Position
	 * @see Direction - Enum Class
	 * Description: This method generate Array list of possible directions from current position
	 */
	public ArrayList<Direction> getPossibleDirections(Position pos){
		ArrayList<Direction> dirs = new ArrayList<Direction>();
		int [][][] m = myMaze.getMaze();
		int x=pos.getRows();
		int y=pos.getFloor();
		int z=pos.getCols();
		
		/*Check left neighbor*/
		if(x - 2 >= 0 && m[y][x-2][z] == myMaze.WALL){
			dirs.add(Direction.LEFT);
		}
		/*Check right neighbor*/
		if(x + 2 < myMaze.getRows() && m[y][x+2][z] == myMaze.WALL){
			dirs.add(Direction.RIGHT);
		}
		/*Check BackWord neighbor*/
		if(z -2  >= 0 && m[y][x][z-2] == myMaze.WALL){
			dirs.add(Direction.BACKWARD);
		}
		if(z + 2 < myMaze.getCols() && m[y][x][z+2] == myMaze.WALL){
			dirs.add(Direction.FORWARD);
		}
		if(y - 2 >= 0 && m[y -2][x][z] == myMaze.WALL){
			dirs.add(Direction.DOWN);
		}
		if(y + 2 < myMaze.getFloor() && m[y +2][x][z] == myMaze.WALL){
			dirs.add(Direction.UP);
		}
		return dirs;
	}



	/**
	 * @version 1.0
	 * @return non
	 * @throws non
	 * @see MyMaze3dGenerator - getPossibleDirection
	 * Description: build maze using DFS algorithm.
	 */

private void DFS(Position pos){
	ArrayList<Direction> dirs = getPossibleDirections(pos);
	if(dirs.size() == 0 ){
		return;
	}
	
	int x=pos.getRows();
	int y=pos.getFloor();
	int z=pos.getCols();
	
	for (int i = 0 ; i < dirs.size(); i++){
		/*Choose random direction*/
		int idx = rand.nextInt(dirs.size());
		Direction dir = dirs.get(idx);
		dirs.remove(idx);
		int [][][] m = myMaze.getMaze();
		switch(dir){
		case LEFT:
			m[y][x-1][z] = myMaze.FREE;
			m[y][x-2][z] = myMaze.FREE;
			DFS(new Position(y,x-2,z));
			break;
		case RIGHT:
			m[y][x+1][z] = myMaze.FREE;
			m[y][x+2][z] = myMaze.FREE;
			DFS(new Position(y,x+2,z));
			break;
		case BACKWARD:
			m[y][x][z -1] = myMaze.FREE;
			m[y][x][z -2] = myMaze.FREE;
			DFS(new Position(y,x,z-2));
			break;
		case FORWARD:
			m[y][x][z +1] = myMaze.FREE;
			m[y][x][z +2] = myMaze.FREE;
			DFS(new Position(y,x,z+2));
			break;
		case DOWN:
			m[y-1][x][z] = myMaze.FREE;
			m[y-2][x][z] = myMaze.FREE;
			DFS(new Position(y-2,x,z));
			break;
		case UP:
			m[y+1][x][z] = myMaze.FREE;
			m[y+2][x][z] = myMaze.FREE;
			DFS(new Position(y+2,x,z));
			break;
			}
		}
	}

	/**
	 * @version 1.0
	 * @return non
	 * @throws non
	 * @see SetGoalPosition 
	 * Description: Set randomly goal position where the cell {y,x,z} is "FREE". Using SetGoalPosition in Maze3d.
	 */


	public void goalPosion(){
		int x = 0 ;
		int y = 0;
		int z = 0;
		boolean validExit = false;
		int [][][] m = myMaze.getMaze();
		while (!validExit){
			x = rand.nextInt(myMaze.getRows());
			z = rand.nextInt(myMaze.getCols());
			y = rand.nextInt(myMaze.getFloor());
			if(m[y][x][z] == myMaze.FREE){
				validExit = true;	
			}
		}
		Position p = new Position(y,x,z);
		myMaze.setGoalPosition(p);
	}
}	