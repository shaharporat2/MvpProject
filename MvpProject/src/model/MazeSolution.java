package model;

import java.io.Serializable;
import java.util.HashMap;

import algorithms.search.Solution;

public class MazeSolution implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HashMap<String, Solution> mazeSol;

	public MazeSolution(HashMap<String, Solution> mazeSol) {
		super();
		this.mazeSol = mazeSol;
	}	
	
	public MazeSolution() {
		super();
	}

	public HashMap<String, Solution> getMazeSol() {
		return mazeSol;
	}

	public void setMazeSol(HashMap<String, Solution> mazeSol) {
		this.mazeSol = mazeSol;
	}
	
	
}
