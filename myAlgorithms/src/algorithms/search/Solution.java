package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * This Class will hold the solution of a search problem
 * @version 1.0
 * @param Array list of states
 * @return non
 * @throws non
 * @see state
 */

public class Solution implements Serializable {


	private static final long serialVersionUID = 1L;

	private ArrayList<State> states;
	
	public Solution(ArrayList<State> states) {
		this.states = states;
	}

	public Solution() {
		super();
		ArrayList<State> states = new ArrayList<>();
	}

	public ArrayList<State> getStates() {
		return states;
	}

	public void setStates(ArrayList<State> states) {
		this.states = states;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(State s : states ){
			sb.append(s).append("\n");
		}
		return sb.toString();
	}	
	
	
}
