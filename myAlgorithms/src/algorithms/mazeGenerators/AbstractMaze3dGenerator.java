package algorithms.mazeGenerators;

/**
 * This is an Abstract class that implements Maze3d Generator and define measureAlgorithmTime method.
 * @version 1.0
 * @param non
 * @return non
 * @throws non
 * 
 *  
 */


public abstract class AbstractMaze3dGenerator implements Maze3dGenerator {
	
	/**
	 * Description: This is an Enum Class for the possible moves in the maze.
	 * @version 1.0
	 * @param floor - This parameter defines number of floors in the maze
	 * @param rows - this parameter defines number of rows in each floor of the maze
	 * @param cols - this parameter defines number of cols in each floor of the maze
	 * @return time to generate the maze in string format
	 * @throws non
	 * 
	 *  
	 */

	
	 @Override
	public String measureAlgorithmTime(int floor, int rows, int cols) {
		 
		 long StartTime=System.currentTimeMillis();
		 generate(floor, rows, cols);
		 long EndTime=System.currentTimeMillis();
		 long x =EndTime-StartTime;
		 String sx=Long.toString(x);
		 return sx;
	}
}
