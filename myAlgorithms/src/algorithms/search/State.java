package algorithms.search;

import java.io.Serializable;

/**
 * description: This Class it's the basic class of algorithm search, it define the information of every state
 * @version 1.0
 * @param String description - This parameter holds the description of the state (Like Goal state, start state etc)
 * @param double cost - This parameter holds the cost from Begin state to the current state 
 * @param State Camefrom - This parameter holds the state father
 * @param isVisited - This parameter indicate whether the Algorithm visit the vertex or not 
 * @return  non
 * @throws non 
 *   
 */

public class State implements Comparable<State>, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String description;
	private double cost;
	private State cameFrom;
	private boolean visited;
	
	public State(String description, double cost, State cameFrom, boolean visited) {
		super();
		this.description = description;
		this.cost = cost;
		this.cameFrom = cameFrom;
		this.visited = visited;
	}

	public State() {
		super();
	}

	/**
	 * @version 1.0
	 * @param non
	 * @return the Description of the state
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * description: Getter
	 * @version 1.0
	 * @param non
	 * @return Cost of a state
	 */
	public double getCost() {
		return cost;
	}
	
	/**
	 * description: Setter
	 * @version 1.0
	 * @param non
	 * @return non
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	/**
	 * description: Getter
	 * @version 1.0
	 * @param non
	 * @return State - the state Father
	 */
	public State getCameFrom() {
		return cameFrom;
	}
	
	/**
	 * description:Setter - Set the state father
	 * @version 1.0
	 * @param non
	 * @return non
	 */
	public void setCameFrom(State cameFrom) {
		this.cameFrom = cameFrom;
	}
	
	/**
	 * description: Setter: Set a state description in maze problam Using the toString if the position Class 
	 * @version 1.0
	 * @param non
	 * @return non
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public int compareTo(State s) {
		return (int)(this.cost - s.cost);
	}
	@Override
	public boolean equals(Object arg0){
		State state = (State)arg0;
		return description.equals(state.description);
	}
	@Override
	public String toString(){
		return description;
	}
	public boolean isVisited() {
		return visited;
	}
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	@Override
	public int hashCode(){
		return description.hashCode();
	}
}
