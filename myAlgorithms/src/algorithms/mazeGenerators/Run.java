package algorithms.mazeGenerators;

import java.io.IOException;

import algorithms.demo.Demo;

public class Run {
	
	private static void testMazeGenerator(Maze3dGenerator mg)
	{
		System.out.println(mg.measureAlgorithmTime(4, 5, 6));
		Maze3d maze =mg.generate(4, 5, 6);
		Position p=maze.getStartPosition();
		System.out.println(p);
		String[] moves=maze.getPossibleMoves(p);
		for (String string : moves) {
			System.out.println(string);
		}
		System.out.println(maze.getGoalPosition());
		System.out.println("Maze3d:");
		maze.print();
		
		try {
			System.out.println("Get Cross Section by x ");
			int [][] maze2dx=maze.getCrossSectionByX(2);
			System.out.println("Get Cross Section by y ");
			int [][] maze2dy=maze.getCrossSectionByY(3);
			System.out.println("Get Cross Section by z ");
			int [][] maze2dz=maze.getCrossSectionByZ(1);
			maze.getCrossSectionByX(-1);
		}catch (IndexOutOfBoundsException e){
			System.out.println("good");
		}
		
		
	}

	public static void main(String[] args) throws IOException {
		/*
		testMazeGenerator(new MyMaze3dGenerator());
		testMazeGenerator(new SimpleMaze3dGenerato());
		*/
		
		Demo demo = new Demo();
		
		demo.Run();
	}	
}
