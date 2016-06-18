package model;

import java.io.Serializable;
import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.search.Solution;

public class MazeSolution implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HashMap<Maze3d, Solution> mazeSol;
	
	private HashMap<String, Maze3d> mazes;

	public HashMap<String, Maze3d> getMazes() {
		return mazes;
	}

	public void setMazes(HashMap<String, Maze3d> mazes) {
		this.mazes = mazes;
	}

	public MazeSolution(HashMap<Maze3d, Solution> mazeSol,HashMap<String, Maze3d> mazes ) {
		super();
		this.mazeSol = mazeSol;
		this.mazes = mazes;
	}	
	
	public MazeSolution() {
		super();
	}

	public HashMap<Maze3d, Solution> getMazeSol() {
		return mazeSol;
	}

	public void setMazeSol(HashMap<Maze3d, Solution> mazeSol) {
		this.mazeSol = mazeSol;
	}
	
	
}
