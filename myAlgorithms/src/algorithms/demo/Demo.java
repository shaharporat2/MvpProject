package algorithms.demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.BestFirstSearch;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.DFS;
import algorithms.search.Solution;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;
import algorithms.*;


/**
 * Description: Test Class
 * 
 *
 */
public class Demo {
	
	
	public void Run() throws IOException {
		
		/*Initial Test */
		MyMaze3dGenerator generate = new MyMaze3dGenerator();
		Maze3d maze = generate.generate(3,4,5);
	
		System.out.println(maze);
		/*
		MazeAdapter adapter = new MazeAdapter(maze);
		
		
		System.out.println("**********Breadth First Search Test START************\n");
		BreadthFirstSearch BreadthFirstSearch= new BreadthFirstSearch();
		Solution BreadthFirstSearchSolution = BreadthFirstSearch.search(adapter);
		System.out.println(BreadthFirstSearchSolution);	
		System.out.println("number of vertex:" + BreadthFirstSearch.getVertexCounter());
		System.out.println("**********Breadth First Search Test PASS*************\n");
		
		
		System.out.println("**********Depth First Search Test START**************\n");
		DFS DFS = new DFS();
		Solution DFSSolution = DFS.search(adapter);
		System.out.println(DFSSolution);
		System.out.println("number of vertex:" + DFS.getVertexCounter());
		System.out.println("**********Depth First Search Test PASS***************\n");
		
		System.out.println("**********Best First Search Test START***************\n");
		BestFirstSearch BestFirstSearch = new BestFirstSearch();
		Solution BestFirstSearchSolution = new Solution();
		BestFirstSearchSolution = BestFirstSearch.search(adapter);
		System.out.println(BestFirstSearchSolution);
		System.out.println("number of vertex:" + BestFirstSearch.getVertexCounter());
		System.out.println("**********Best First Search Test PASS****************\n");
		
	*/
		OutputStream out= new MyCompressorOutputStream(new FileOutputStream("1.maz"));
		out.write(maze.toByteArray());
		out.flush();
		out.close();
		
		InputStream in= new MyDecompressorInputStream(new FileInputStream("1.maz"));
				byte b[]=new byte[maze.toByteArray().length];
				in.read(b);
				in.close();
				
		Maze3d loaded=new Maze3d(b);
		System.out.println(loaded.toString());
	}

}
