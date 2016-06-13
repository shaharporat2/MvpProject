package algorithms.search;

import java.util.PriorityQueue;

/**
 * Description: Every Search Algorithm that using Priority Queue will extend this class
 * @version 1.0
 * @param openlist
 * @param closelist
 * @return non
 * @throws non
 * 
 */


public abstract class CommonSearcherWithPQueue extends CommonSearcher {
	
	protected PriorityQueue<State> openList;
	protected PriorityQueue<State> closeList;
	
	public CommonSearcherWithPQueue() {
		openList = new PriorityQueue<State>();
		closeList = new PriorityQueue<State>();
	}
}
