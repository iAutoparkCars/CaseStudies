import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class ProjectDependency {

	final String ORDER_ERR = "Was not able to find project build order.";
	
	public static void main(String[] args) {
		ProjectDependency p = new ProjectDependency();
		
		// dependencies
		List<List<Character>> l = new LinkedList<List<Character>>();
		l.add(new LinkedList(Arrays.asList('a', 'd')));
		l.add(new LinkedList(Arrays.asList('f', 'b')));
		l.add(new LinkedList(Arrays.asList('b', 'd')));
		l.add(new LinkedList(Arrays.asList('f', 'a')));
		l.add(new LinkedList(Arrays.asList('d', 'c')));
		
		// test case to add a cycle, uncomment line below to add cycle
		//l.add(new LinkedList(Arrays.asList('c', 'b')));
		
		// projects
		List<Character> projects = new ArrayList<Character>(
			Arrays.asList('a', 'b', 'c', 'd', 'e', 'f')
		);
		
		p.validOrdering(projects, l);
		
		/* ------------------- ANOTHER TEST CASE ------------------- */
		List<Character> p2 = new ArrayList<Character>(
				Arrays.asList('a', 'b')
		);
		
		List<List<Character>> l2 = new LinkedList<List<Character>>();
		l2.add(new LinkedList(Arrays.asList('a', 'b')));
		l2.add(new LinkedList(Arrays.asList('b', 'a')));
		//l2.add(new LinkedList(Arrays.asList('e', 'c')));
		p.validOrdering(p2, l2);
	}

    /**
    * @param projects, assumes valid, non-null list of projects
    * @param dependencies, assumes valid (exist in projects), non-null list of dependencies
    * @return print possible ordering, or ERR if one doesn't exist
    */
	public List<Character> validOrdering(List<Character> projects, List<List<Character>> dependencies){

		// the possible ordering will be placed here 
		List<Character> ordering = new LinkedList<Character>();
		
		// get the adjacents for each project, and the # of its constraints
		Map<Character, List<Character>> adjacents = new HashMap<Character, List<Character>>();;
		Map<Character, Integer> constraints = new HashMap<Character, Integer>();
		getAdjsAndConstraints(dependencies, adjacents, constraints);
		
		// for projects that have no constraints, add them to the queue first
		Queue<Character> q = new LinkedList<Character>();
		for (char project : projects)
			if (!constraints.containsKey(project)) q.offer(project);
		
		while(!q.isEmpty()){
			char current = q.remove();
			
			ordering.add(current);
			
			// for each child proj, see if its constraint==0, then add to queue
			List<Character> adjList = adjacents.getOrDefault(current, null);
			if (adjList == null) continue;
			
			for (Character adjProject : adjList){
				int numConstraints = constraints.get(adjProject);
				if (--numConstraints == 0) q.add(adjProject);
				
				// update number constraint for this project
				constraints.put(adjProject, numConstraints);
			}
		}
		
		// if project build not finished, an order was not found
		if (ordering.size() != projects.size()){ System.err.println(ORDER_ERR); } 
		
		System.out.println(ordering + "\n");
        return ordering;
    }
	
	 /**
	    * @param dependencies, assumes valid (exist in projects), non-null list of dependencies
	    * @return adjLists, key/dependency and a list of adjacent nodes
	    * @return constraints, map of each node and a count of its constraints
	    */
	public void getAdjsAndConstraints(List<List<Character>> deps, 
								Map<Character, List<Character>> adjLists, 
								Map<Character, Integer> constraints){

		for (List<Character> pair : deps){
			char first = pair.get(0);
			char second = pair.get(1);
			
			// update the adj List for each node
			List<Character> adjList = adjLists.getOrDefault(first, new LinkedList<Character>());
			adjList.add(second);
			adjLists.put(first,  adjList); 
			
			int numConstraints = constraints.getOrDefault(second, 0);
			constraints.put(second, ++numConstraints);
		}
	}
}
