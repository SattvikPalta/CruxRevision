package Dec21;

import java.util.ArrayList;
import java.util.HashMap;

public class BellmanFord {

	public static void main(String[] args) {
		ArrayList<String> vces = new ArrayList<>();
		ArrayList<Edge> edges = new ArrayList<>();

		vces.add("A");
		vces.add("B");
		vces.add("C");
		vces.add("D");

		edges.add(new Edge("A", "B", 3));
		edges.add(new Edge("B", "C", 1));
		edges.add(new Edge("C", "A", -1));
		edges.add(new Edge("C", "D", 2));
		edges.add(new Edge("A", "D", 8));

		HashMap<String, BFpair> result = new HashMap<>();
		
		String src = "A";

		for (String vtx : vces) {
			BFpair bp = new BFpair();
			if (vtx.equals(src)) {
				bp.psf = vtx;
				bp.csf = 0;
			} else {
				bp.psf = null;
				bp.csf = Integer.MAX_VALUE;
			}
			
			result.put(vtx, bp);
		}

		for (int i = 1; i < vces.size(); i++) {
			for (Edge edge : edges) {
				BFpair srcPair = result.get(edge.v1Name);
				BFpair destPair = result.get(edge.v2Name);
				int weight = edge.weight;

				if (srcPair.psf == null)
					continue;

				else if (srcPair.csf + weight < destPair.csf) {
					destPair.csf = srcPair.csf + weight;
					destPair.psf = srcPair.psf + edge.v2Name;
				}
			}
		}

		for (Edge edge : edges) {
			BFpair srcPair = result.get(edge.v1Name);
			BFpair destPair = result.get(edge.v2Name);
			int weight = edge.weight;

			if (srcPair.psf == null)
				continue;

			else if (srcPair.csf + weight < destPair.csf) {
				System.out.println("Cycle");
				break;
			}
		}

		System.out.println(result);
	}

	private static class Edge {
		String v1Name;
		String v2Name;
		int weight;

		Edge(String v1Name, String v2Name, int weight) {
			this.v1Name = v1Name;
			this.v2Name = v2Name;
			this.weight = weight;
		}
	}

	private static class BFpair {
		String psf;
		int csf;

		public String toString() {
			return "[" + this.csf + " via " + this.psf + "]\n";
		}
	}
}
