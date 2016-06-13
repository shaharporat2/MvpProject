package algorithms.search;

/**
 * Description: This Class  represent the action that the player can in the maze
 * @version 1.0
 * @param description
 * @param cost
 * @return non
 * @throws non
 * @see State
 */

public class Action {
	
	/*Holds the description of the Action - In out Project the Description is the position*/
	private String description;
	
	/*Holds the cost to arrive this position in our case the cost define in MovementCost const*/
	private double cost;
	
	/**
	 * @version 1.0
	 * @param non
	 * @return The description of the Action - in our case Position - Using the toString of the Position Class
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * description - set description to a Action
	 * @version 1.0
	 * @param description
	 * @return non
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * description Getter to cost data member
	 * @version 1.0
	 * @param non
	 * @return the cost of an action
	 */
	public double getCost() {
		return cost;
	}
	
	/**
	 * description: Setter to cost of an action
	 * @version 1.0
	 * @param cost - double
	 * @return non 
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	/**
	 * description: default C'tor
	 * @version 1.0
	 * @param non
	 * @return non
	 */
	public Action() {}
	
	
	/**
	 * description: C'tor for the Action Class
	 * @version 1.0
	 * @param description
	 * @param cost
	 * @return non
	 */
	public Action(String description, double cost) {
		this.description = description;
		this.cost = cost;
	}
/*
	@Override
	public int hashCode(){
		return description.hashCode();
	}
/*
 
 */
	/**
	 * description: override the toString of the object
	 * @version 1.0
	 * @param non
	 * return description in String
	 */
	@Override
	public String toString() {
		return description;
	}
}
