import java.util.HashSet;
import java.util.Set;

public class Graph {
	public String name;
    public Set<Node> nodes = new HashSet<>();
    
    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }
}