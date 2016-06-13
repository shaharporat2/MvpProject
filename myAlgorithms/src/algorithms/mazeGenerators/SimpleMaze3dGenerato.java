package algorithms.mazeGenerators;

import java.util.Random;

import org.omg.CORBA.FREE_MEM;

public class SimpleMaze3dGenerato extends AbstractMaze3dGenerator {

	private Maze3d myMaze;
	private Random rand=new Random();
	
	@Override
	public Maze3d generate(int floor, int rows, int cols) {
		myMaze = new Maze3d(floor, rows, cols);
		int [][][] m =myMaze.getMaze();
		int x1 =rand.nextInt(rows-2)+1;
		int y1 =rand.nextInt(cols-2)+1;
		int x2 =rand.nextInt(rows-2)+1;
		int y2 =rand.nextInt(cols-2)+1;
		m[0][x1][y1] = myMaze.FREE;
		m[floor-1][x2][y2] = myMaze.FREE;
		m[1][x1][y1] = myMaze.FREE;
		m[floor-2][x2][y2] = myMaze.FREE;
		
		myMaze.setStartPosition(new Position(0, x1, y1));
		myMaze.setGoalPosition(new Position(floor-1,x2,y2));
		
		for (int i = 1; i < floor-1; i++) {
			for (int j = 1; j < rows-1; j=j+2) {
				for (int k = 1; k < cols-1; k++) {
					
					m[i][j][k]=myMaze.FREE;
				}
				
			}
			
		}
			
		for (int i = 1; i < floor-1; i++) {
			for (int j = 2; j < rows-1; j=j+2) {	
					int z =rand.nextInt(cols-2)+1;
					m[i][j][z]=myMaze.FREE;
			}
			
		}
		
		
		return 	myMaze;
	}

}
