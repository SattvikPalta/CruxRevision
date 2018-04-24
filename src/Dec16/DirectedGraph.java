package Dec16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class DirectedGraph extends Graph {

	@Override
	public void removeVertex(String vName) {
		ArrayList<String> keys = new ArrayList<>(vces.keySet());
		for (String key : keys) 
			vces.get(key).nbrs.remove(vName);

		vces.remove(vName);
	}

	@Override
	public int numEdges() {
		int count = 0;

		ArrayList<String> vNames = new ArrayList<>(vces.keySet());
		for (String vName : vNames) {
			Vertex vtx = vces.get(vName);
			count += vtx.nbrs.size();
		}

		return count;
	}
	
	@Override
	public void addEdge(String v1Name, String v2Name, int weight) {
		Vertex vtx1 = vces.get(v1Name);
		Vertex vtx2 = vces.get(v2Name);

		if (vtx1 == null || vtx2 == null)
			return;

		vtx1.nbrs.put(v2Name, weight);
	}

	@Override
	public void removeEdge(String v1Name, String v2Name) {
		Vertex vtx1 = vces.get(v1Name);
		Vertex vtx2 = vces.get(v2Name);

		if (vtx1 == null || vtx2 == null)
			return;

		vtx1.nbrs.remove(v2Name);
	}

}
