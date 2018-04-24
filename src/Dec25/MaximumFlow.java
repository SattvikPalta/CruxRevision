package Dec25;

import java.util.ArrayList;
import java.util.HashMap;

public class MaximumFlow {
	public static HashMap<String, HashMap<String, Integer>> graph = new HashMap<>();
	public static int flow = 0;
	public static HashMap<String, Boolean> processed = new HashMap<>();

	public static void main(String[] args) {
		graph.put("A", new HashMap<>());
		graph.put("B", new HashMap<>());
		graph.put("C", new HashMap<>());
		graph.put("D", new HashMap<>());
		graph.put("S", new HashMap<>());
		graph.put("T", new HashMap<>());

		graph.get("S").put("A", 10);
		graph.get("A").put("S", 0);

		graph.get("S").put("C", 8);
		graph.get("C").put("S", 0);

		graph.get("A").put("B", 5);
		graph.get("B").put("A", 0);

		graph.get("A").put("C", 2);
		graph.get("C").put("A", 0);

		graph.get("B").put("T", 7);
		graph.get("T").put("B", 0);

		graph.get("C").put("D", 10);
		graph.get("D").put("C", 0);

		graph.get("D").put("T", 10);
		graph.get("T").put("D", 0);

		graph.get("D").put("B", 8);
		graph.get("B").put("D", 0);

		while (true) {
			processed = new HashMap<>();
			int localMinCap = hasPath("S", "T", Integer.MAX_VALUE);
			if (localMinCap == -1)
				break;
		}

		System.out.println(flow);
	}

	public static int hasPath(String v1Name, String v2Name, int minCapacity) {
		processed.put(v1Name, true);

		if (v1Name.equals(v2Name)) {
			flow += minCapacity;
			return minCapacity;
		}

		ArrayList<String> nbrNames = new ArrayList<>(graph.get(v1Name).keySet());
		for (String nbrName : nbrNames) {
			if (!processed.containsKey(nbrName) && graph.get(v1Name).get(nbrName) > 0) {
				int capacity = graph.get(v1Name).get(nbrName);
				int revCapacity = graph.get(nbrName).get(v1Name);

				int localMinCap = hasPath(nbrName, v2Name, Math.min(minCapacity, capacity));

				if (localMinCap != -1) {
					graph.get(v1Name).put(nbrName, capacity - localMinCap);
					graph.get(nbrName).put(v1Name, revCapacity + localMinCap);
					return localMinCap;
				}
			}
		}

		return -1;
	}
}
