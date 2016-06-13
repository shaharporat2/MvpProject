package algorithms.search;

import java.util.ArrayList;

/**
 * Description: This is an abstract class every Search Algorithm will extend it
 * @version 1.0
 * @param VertexCounter
 * @return non
 * @throws non 
 * @see Searcher
 */


public abstract class CommonSearcher implements Searcher {
	
	
	/**
	 * Description: Data member holding number of vertex the Algorithm Develop
	 */
	
	private int VertexCounter;
	
	public CommonSearcher() {
	
	}
	
	/**
	 * Description : This method doing backtrace from the GoalPoint to the StartPoint
	 * @param state
	 * @return Solution
	 * @see Solution
	 */
	
	protected Solution backtrace(State state){
		State s = state;
		ArrayList<State> states = new ArrayList<State>();
		while(s != null){
			states.add(0,s);
			s = s.getCameFrom();
		}
		Solution solution = new Solution();
		solution.setStates(states);
		return solution;
	}
	
	/**
	 * Getter to data member VertexCoubter
	 * @return VertexCounter
	 */

	public int getVertexCounter() {
		return VertexCounter;
	}
	
	/**
	 * Description: Setter for Vertex Counter
	 * @param VertexCounter
	 */

	public void setVertexCounter(int VertexCounter) {
		this.VertexCounter = VertexCounter;
	}
}
