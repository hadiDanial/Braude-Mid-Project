import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TraceAnalyser {
	static Connection conn;
		
	public static void main(String[] args) 
	{
	connect2DB();
//	Graph g1=buildGraph("G1");
//	Graph g2=buildGraph("G2");
		
		Node nodeA = new Node("A");
		Node nodeB = new Node("B");
		Node nodeC = new Node("C");
		Node nodeD = new Node("D"); 
		Node nodeE = new Node("E");
		Node nodeF = new Node("F");

		nodeA.addDestination(nodeB, 10);
		nodeA.addDestination(nodeC, 15);
		nodeB.addDestination(nodeD, 12);
		nodeB.addDestination(nodeF, 15);
		nodeC.addDestination(nodeE, 10);
		nodeD.addDestination(nodeE, 2);
		nodeD.addDestination(nodeF, 1);
		nodeF.addDestination(nodeE, 5);

		Graph g1 = new Graph();
		g1.name="G1";		
		g1.addNode(nodeA);
		g1.addNode(nodeB);
		g1.addNode(nodeC);
		g1.addNode(nodeD);
		g1.addNode(nodeE);
		g1.addNode(nodeF);
		
		Graph g2 = new Graph();
		g2.name="G2";
		g2.addNode(nodeD);
		g2.addNode(nodeE);
		g2.addNode(nodeF);
		 
		if (firstGreater(g1, g2))
		{System.out.println("G1 is greater than G2");}
	}
	
	/*
	 * calculates diameter of the graph: the maximal length of shortest paths between two nodes in the graph
	 */
	private static int calculateDiameter(Graph g) {
		int maxDistance=0;
		int nodeDistance=Integer.MAX_VALUE;
		for (Node node: g.nodes) {
			Graph g1 = Dijkstra.calculateShortestPathFromSource(g, node);
			for (Node node2: g1.nodes) {
				nodeDistance = node2.getDistance();
				if (nodeDistance!=Integer.MAX_VALUE && nodeDistance > maxDistance) {
	            maxDistance = nodeDistance;
	        }
		}
	    }
		return maxDistance;
	}	
	
	/**
	 * calculates alpha characteristic of the graph
	 * 
	 */
	private static int calculateAlphaCharacteristic(Graph g) {
		int N=g.nodes.size();
		int D=calculateDiameter(g);
		int L=0;
		
		//searching of self loops 
		for (Node node: g.nodes) {
			if (node.adjacentNodes.containsValue(node))
			{L++;}
		}
		
		if (N-D+L < 0) 
			{return 0;}
		else
			if (L > 0)
			{return N-D-1;}
			else
			{return N-D+L;}
	}		
	

	/**
	 * compare two graphs by their alpha characteristics
	 *
	 */
	private static boolean firstGreater(Graph g1, Graph g2) {
		int edgeNumber1=0;
		int edgeNumber2=0;
		int alpha_g1=calculateAlphaCharacteristic(g1);
		int alpha_g2=calculateAlphaCharacteristic(g2);
		if (alpha_g1==alpha_g2)
		{ 
			for (Node node: g1.nodes) {
				edgeNumber1=edgeNumber1+node.adjacentNodes.size();			
			}
			for (Node node: g2.nodes) {
				edgeNumber2=edgeNumber2+node.adjacentNodes.size();			
			}	
			if (edgeNumber1 > edgeNumber2) {return true;}
		}	
		else	if (alpha_g1>alpha_g2)
		{return true;
		}
		return false;
	}	


	public static void connect2DB() 
	{
		try 
		{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            System.out.println("Driver definition succeed");
        } catch (Exception ex) {
        	/* handle the error*/
        	 System.out.println("Driver definition failed");
        	 }        
        try 
        {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/test?serverTimezone=IST","root","Aa123456");
            System.out.println("SQL connection succeed");            
     	} catch (SQLException ex) 
     	    {/* handle any errors*/
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            }
   	}
	
	/**
	 * build a graph from the graph information saved in DB
	 */
	public static Graph buildGraph(String name)
	{
		Statement stmt;
		Graph graph = new Graph();
		graph.name=name;
		try 
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM test.graphs WHERE graphName=\""+name+"\";");
	 		while(rs.next())
	 		{
	 			Node initialNode = new Node(rs.getString(2));
	 			Node destinationNode = new Node(rs.getString(3));
		
	 			initialNode.addDestination(destinationNode, rs.getInt(4));
	 			if (!graph.nodes.contains(initialNode))
	 				{graph.addNode(initialNode);}
	 			if (!graph.nodes.contains(destinationNode))
 				{graph.addNode(destinationNode);}
			} 
			rs.close();
		} catch (SQLException e) {e.printStackTrace();}
		return graph;
	}
	public boolean isClose(sourceNode sn, evaluationNode en, Threshold t)
	{
		
	}
}
