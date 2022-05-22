import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node {
    
    public String name;
    
    private List<Node> shortestPath = new LinkedList<>();
    
    private Integer distance = Integer.MAX_VALUE;
    
    Map<Node, Integer> adjacentNodes = new HashMap<>();
    
    public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }
 
    public Node(String name) {
        this.name = name;
    }

	public Integer getDistance() {
		return distance;
	}

	public Map<Node, Integer> getAdjacentNodes() {
		// TODO Auto-generated method stub
		return adjacentNodes;
	}

	public List<Node> getShortestPath() {
		// TODO Auto-generated method stub
		return shortestPath;
	}

	public void setShortestPath(LinkedList<Node> shortestPath) {
		// TODO Auto-generated method stub
		this.shortestPath=shortestPath;
	}
}
