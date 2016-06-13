package algorithms.search;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Description - This class will solve a search problem with DFS 
 * @version 1.0
 * @param solution
 * @param HashSet<State>
 * @return non
 * @throws non
 *  
 */

public class DFS extends CommonSearcher {
	
	private Solution solution;
	private HashSet<State> visitedStates = new HashSet<State>();

	/**
	 * Description - This Method will solve a search problem with DFS 
	 * @version 1.0
	 * @param Searchable
	 * @return non
	 * @throws non
	 *  
	 */

	
	@Override
	public Solution search(Searchable s) {
		this.setVertexCounter(1);
		dfs(s,s.getStartState());
		return solution;
	}
	
	/**
	 * Description : DFS Algorithm
	 * @param s
	 * @param state
	 */
	private void dfs(Searchable s, State state){
		if(state.equals(s.getGoalState())){
			solution = backtrace(state);
			return;
		}
		
		visitedStates.add(state);
		
		HashMap<Action, State> actions = s.getAllPossibaleActions(state);
		for(State neighbor : actions.values())
		{
			if(!visitedStates.contains(neighbor))
			{
				neighbor.setCameFrom(state);
				this.setVertexCounter(this.getVertexCounter()+1);
				dfs(s, neighbor);
			}
		}
		return;
	}
}
