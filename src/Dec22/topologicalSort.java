package Dec22;

import java.util.ArrayList;
import java.util.HashMap;

public class topologicalSort {

	public static void main(String[] args) {
		HashMap<String, HashMap<String, Integer>> graph = new HashMap<>();

		graph.put("A", new HashMap<>());
		graph.put("B", new HashMap<>());
		graph.put("C", new HashMap<>());
		graph.put("D", new HashMap<>());
		graph.put("E", new HashMap<>());
		graph.put("F", new HashMap<>());

		graph.get("A").put("C", 0);
		graph.get("A").put("F", 0);
		graph.get("C").put("D", 0);
		graph.get("D").put("E", 0);
		graph.get("B").put("E", 0);
		graph.get("B").put("F", 0);

		ArrayList<String> result = new ArrayList();
		HashMap<String, Boolean> processed = new HashMap<>();

		ArrayList<String> vNames = new ArrayList<>(graph.keySet());
		for (String vName : vNames) {
			if (processed.containsKey(vName))
				continue;

			rec(vName, processed, result, graph);
		}

		System.out.println(result);
	}

	public static void rec(String vName, HashMap<String, Boolean> processed, ArrayList<String> stack,
			HashMap<String, HashMap<String, Integer>> graph) {
		if (processed.containsKey(vName))
			return;

		processed.put(vName, true);

		ArrayList<String> nbrNames = new ArrayList<>(graph.get(vName).keySet());
		for (String nbrName : nbrNames)
			rec(nbrName, processed, stack, graph);

		stack.add(vName);
	}

}
