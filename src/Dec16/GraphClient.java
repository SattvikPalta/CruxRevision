package Dec16;

public class GraphClient {

	public static void main(String[] args) {
		Graph g = new Graph();

		g.addVertex("A");
		g.addVertex("B");
		g.addVertex("C");
		g.addVertex("D");
		g.addVertex("E");
		g.addVertex("F");
		g.addVertex("G");

		g.addEdge("A", "B", 10);
		g.addEdge("A", "D", 40);
		g.addEdge("B", "C", 10);
		g.addEdge("C", "D", 10);
		g.addEdge("D", "E", 2);
		g.addEdge("E", "F", 3);
		g.addEdge("E", "G", 8);
		g.addEdge("F", "G", 3);

//		g.display();
//
//		System.out.println(g.hasPath("A", "G"));
//		g.removeEdge("D", "E");
//		System.out.println(g.hasPath("A", "G"));
//
//		g.printAllPaths("A", "G");
//		
//		g.preOrderConnectedGraph();
//		
//		g.postOrderConnectedGraph();
//		
//		System.out.println(g.BFS("A", "G"));
//
//		System.out.println(g.DFS("A", "G"));
//
//		g.BFT();
//
//		g.DFT();
//
//		System.out.println(g.isConnected());
//
//		System.out.println(g.isAcyclic());
//
//		System.out.println(g.isTree());
//
//		System.out.println(g.isBipartite());
		
//		System.out.println(g.GCC());
//		
//		System.out.println(g.GCC());
//		
//		System.out.println(g.Dijkstra("A"));
//		
//		g.PrimsMST().display();

		g.Kruskal().display();
	}

}
