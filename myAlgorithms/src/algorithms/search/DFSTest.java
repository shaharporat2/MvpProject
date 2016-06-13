package algorithms.search;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;

import algorithms.demo.MazeAdapter;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MyMaze3dGenerator;

public class DFSTest {

	private DFS dfs ;
	private Maze3d maze;
	private MazeAdapter adapter;
	private Solution solution;
	MyMaze3dGenerator generate;
	
	
	@org.junit.Before
	public void SetUp() throws Exception{
	try	{
		generate = new MyMaze3dGenerator();
		maze = generate.generate(6, 6, 6);
		adapter = new MazeAdapter(maze);
		solution = new Solution();
	}catch( Exception e){
		e.printStackTrace();
	}
}
	

	@org.junit.After
	public void tearDown() throws Exception{
		dfs = null;
	}
	
	@org.junit.Test
	public void test() {
		try{
			dfs = new DFS();
			solution = dfs.search(adapter);
			System.out.println(solution);
		}catch(Exception e){
			System.out.println(e.getStackTrace());
		}
	}

}
