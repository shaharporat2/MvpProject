package algorithms.search;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.PriorityQueue;

/**
 * description- This class will solve a search problem with BFS 
 * @version 1.0
 * @param non
 * @return non
 * @throws non
 *  
 */

public class BreadthFirstSearch extends CommonSearcher {

	/**
	 * Description: This method will solve search problem with BFS
	 * @version 1.0
	 * @param Searchable
	 * @return Solution
	 */
	
	@Override
	public Solution search(Searchable s) {
		LinkedList<State> queue = new LinkedList<State>();
		queue.add(s.getStartState());
		State state = new State();
		this.setVertexCounter(1);
		while(!queue.isEmpty()){
			state = queue.pollFirst();
			if(state.equals(s.getGoalState())){
				return(backtrace(state));
			}
			state.setVisited(true);
			HashMap<Action, State> actions = s.getAllPossibaleActions(state);
			for(Entry<Action, State> entry : actions.entrySet()){
				State successor = entry.getValue();
				if(successor.isVisited() == false){
					successor.setCameFrom(state);
					queue.addLast(successor);
					this.setVertexCounter(getVertexCounter()+1);
				}
			}
		}
		return null;
	}
}
