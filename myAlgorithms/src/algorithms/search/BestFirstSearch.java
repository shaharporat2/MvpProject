package algorithms.search;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * Description - This class will solve a search problem with BFS 
 * @version 1.0
 * @param non
 * @return non
 * @throws non
 *  
 */

public class BestFirstSearch extends CommonSearcherWithPQueue {

	/**
	 * Description: this Method will Solve a search problem with DFS Algorithm 
	 * @version 1.0
	 * @param Searchable
	 * @return Solution
	 * @see Searchable
	 */
	
	
	@Override
	public Solution search(Searchable s) {
		
		openList.add(s.getStartState());
		this.setVertexCounter(1);
		
		while(!openList.isEmpty()){
			State state = openList.poll();
			closeList.add(state);
			
			if(state.equals(s.getGoalState())){
				return(backtrace(state));
			}
			
			HashMap<Action, State> actions = s.getAllPossibaleActions(state);
			
			for(Entry<Action, State> entry : actions.entrySet()){
				Action action = entry.getKey();
				State successor = entry.getValue();
			
				if(!openList.contains(successor) && !closeList.contains(successor)){
					successor.setCameFrom(state);
					successor.setCost(state.getCost() + action.getCost());
					openList.add(successor);
					this.setVertexCounter(this.getVertexCounter()+1);
				}
				else if(state.getCost() + action.getCost() < successor.getCost()){
					successor.setCameFrom(state);
					successor.setCost(state.getCost() + action.getCost());
					
					openList.remove(successor);
					openList.add(successor);
				}
			}
		}
		return null;
	}
}
