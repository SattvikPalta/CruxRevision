package Dec16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Graph {
	protected class Vertex {
		HashMap<String, Integer> nbrs = new HashMap<>();
	}

	HashMap<String, Vertex> vces = new HashMap<>();

	public int numVertices() {
		return vces.size();
	}

	public boolean containsVertex(String vName) {
		return vces.containsKey(vName);
	}

	public void addVertex(String vName) {
		if (vces.containsKey(vName)) {
			return;
		} else {
			Vertex vtx = new Vertex();
			vces.put(vName, vtx);
		}
	}

	public void removeVertex(String vName) {
		Vertex vtx = vces.get(vName);

		ArrayList<String> nbrNames = new ArrayList<>(vtx.nbrs.keySet());
		for (String nbrName : nbrNames) {
			Vertex nbrVertex = vces.get(nbrName);
			nbrVertex.nbrs.remove(vName);
		}

		vces.remove(vName);
	}

	public int numEdges() {
		int count = 0;

		ArrayList<String> vNames = new ArrayList<>(vces.keySet());
		for (String vName : vNames) {
			Vertex vtx = vces.get(vName);
			count += vtx.nbrs.size();
		}

		return count / 2;
	}

	public boolean containsEdge(String v1Name, String v2Name) {
		Vertex vtx1 = vces.get(v1Name);
		Vertex vtx2 = vces.get(v2Name);

		if (vtx1 == null || vtx2 == null)
			return false;

		if (vtx1.nbrs.containsKey(v2Name))
			return true;

		return false;

	}

	public void addEdge(String v1Name, String v2Name, int weight) {
		Vertex vtx1 = vces.get(v1Name);
		Vertex vtx2 = vces.get(v2Name);

		if (vtx1 == null || vtx2 == null)
			return;

		vtx1.nbrs.put(v2Name, weight);
		vtx2.nbrs.put(v1Name, weight);
	}

	public void removeEdge(String v1Name, String v2Name) {
		Vertex vtx1 = vces.get(v1Name);
		Vertex vtx2 = vces.get(v2Name);

		if (vtx1 == null || vtx2 == null)
			return;

		vtx1.nbrs.remove(v2Name);
		vtx2.nbrs.remove(v1Name);
	}

	public void display() {
		ArrayList<String> vNames = new ArrayList<>(vces.keySet());
		for (String vName : vNames)
			System.out.println(vName + " => " + vces.get(vName).nbrs);

		System.out.println("-------------------------------");
	}

	public boolean hasPath(String v1Name, String v2Name) {
		HashMap<String, Boolean> processed = new HashMap<>();
		return hasPathHelper(v1Name, v2Name, processed, "");
	}

	private boolean hasPathHelper(String v1Name, String v2Name, HashMap<String, Boolean> processed, String psf) {
		if (processed.containsKey(v1Name))
			return false;

		processed.put(v1Name, true);

		if (containsEdge(v1Name, v2Name)) {
			System.out.println(psf + v2Name);
			return true;
		}

		Vertex vtx1 = vces.get(v1Name);
		ArrayList<String> nbrNames = new ArrayList<>(vtx1.nbrs.keySet());
		for (String nbrName : nbrNames) {
			Boolean path = hasPathHelper(nbrName, v2Name, processed, psf + nbrName);
			if (path)
				return true;
		}

		return false;
	}

	public void printAllPaths(String v1Name, String v2Name) {
		HashMap<String, Boolean> processed = new HashMap<>();
		printAllPaths(v1Name, v2Name, processed, "A");
	}

	private void printAllPaths(String v1Name, String v2Name, HashMap<String, Boolean> processed, String psf) {
		if (processed.containsKey(v1Name))
			return;

		processed.put(v1Name, true);

		if (v1Name.equals(v2Name)) {
			System.out.println(psf);
			processed.remove(v1Name);
			return;
		}

		Vertex vtx1 = vces.get(v1Name);
		ArrayList<String> nbrNames = new ArrayList<>(vtx1.nbrs.keySet());
		for (String nbrName : nbrNames) {
			printAllPaths(nbrName, v2Name, processed, psf + nbrName);
		}

		processed.remove(v1Name);
	}

	public void preOrderConnectedGraph() {
		ArrayList<String> vNames = new ArrayList<>(vces.keySet());
		String vName = vNames.get(0);
		HashMap<String, Boolean> processed = new HashMap<>();
		preOrderConnectedGraph(vName, "A", processed);
	}

	private void preOrderConnectedGraph(String vName, String psf, HashMap<String, Boolean> processed) {
		if (processed.containsKey(vName))
			return;

		processed.put(vName, true);

		System.out.println(vName + " via " + psf);

		Vertex vtx = vces.get(vName);
		ArrayList<String> nbrNames = new ArrayList<>(vtx.nbrs.keySet());
		for (String nbrName : nbrNames) {
			preOrderConnectedGraph(nbrName, psf + nbrName, processed);
		}

		processed.remove(vName);
	}

	public void postOrderConnectedGraph() {
		ArrayList<String> vNames = new ArrayList<>(vces.keySet());
		String vName = vNames.get(0);
		HashMap<String, Boolean> processed = new HashMap<>();
		postOrderConnectedGraph(vName, "A", processed);
	}

	private void postOrderConnectedGraph(String vName, String psf, HashMap<String, Boolean> processed) {
		if (processed.containsKey(vName))
			return;

		processed.put(vName, true);

		Vertex vtx = vces.get(vName);
		ArrayList<String> nbrNames = new ArrayList<>(vtx.nbrs.keySet());
		for (String nbrName : nbrNames) {
			postOrderConnectedGraph(nbrName, psf + nbrName, processed);
		}

		System.out.println(vName + " via " + psf);

		processed.remove(vName);
	}

	private class Pair {
		String vName;
		String psf;
		String color;
	}

	public boolean BFS(String v1Name, String v2Name) {
		HashMap<String, Boolean> processed = new HashMap<>();
		LinkedList<Pair> queue = new LinkedList<>();

		Pair rootPair = new Pair();
		rootPair.vName = v1Name;
		rootPair.psf = v1Name;
		queue.addLast(rootPair);

		while (queue.size() != 0) {
			Pair rp = queue.removeFirst();

			processed.put(rp.vName, true);

			System.out.println(rp.vName + " via " + rp.psf);

			if (containsEdge(rp.vName, v2Name))
				return true;

			ArrayList<String> nbrNames = new ArrayList<>(vces.get(rp.vName).nbrs.keySet());
			for (String nbrName : nbrNames) {
				if (!processed.containsKey(nbrName)) {
					Pair np = new Pair();
					np.vName = nbrName;
					np.psf = rp.psf + nbrName;
					queue.add(np);
				}
			}
		}

		return false;
	}

	public boolean DFS(String v1Name, String v2Name) {
		HashMap<String, Boolean> processed = new HashMap<>();
		LinkedList<Pair> stack = new LinkedList<>();

		Pair rootPair = new Pair();
		rootPair.vName = v1Name;
		rootPair.psf = v1Name;
		stack.addFirst(rootPair);

		while (stack.size() != 0) {
			Pair rp = stack.removeFirst();

			processed.put(rp.vName, true);

			System.out.println(rp.vName + " via " + rp.psf);

			if (containsEdge(rp.vName, v2Name))
				return true;

			ArrayList<String> nbrNames = new ArrayList<>(vces.get(rp.vName).nbrs.keySet());
			for (String nbrName : nbrNames) {
				if (!processed.containsKey(nbrName)) {
					Pair np = new Pair();
					np.vName = nbrName;
					np.psf = rp.psf + nbrName;
					stack.addFirst(np);
				}
			}
		}

		return false;
	}

	public boolean isConnected() {
		HashMap<String, Boolean> processed = new HashMap<>();
		LinkedList<Pair> queue = new LinkedList<>();

		ArrayList<String> vNames = new ArrayList<>(vces.keySet());
		String vName = vNames.get(0);

		Pair rootPair = new Pair();
		rootPair.vName = vName;
		rootPair.psf = vName;
		queue.addLast(rootPair);

		while (queue.size() != 0) {
			Pair rp = queue.removeFirst();

			processed.put(rp.vName, true);

			System.out.println(rp.vName + " via " + rp.psf);

			ArrayList<String> nbrNames = new ArrayList<>(vces.get(rp.vName).nbrs.keySet());
			for (String nbrName : nbrNames) {
				if (!processed.containsKey(nbrName)) {
					Pair np = new Pair();
					np.vName = nbrName;
					np.psf = rp.psf + nbrName;
					queue.add(np);
				}
			}
		}

		return processed.size() == this.numVertices();
	}

	public void BFT() {
		HashMap<String, Boolean> processed = new HashMap<>();
		LinkedList<Pair> queue = new LinkedList<>();

		ArrayList<String> vNames = new ArrayList<>(vces.keySet());

		for (String vName : vNames) {
			if (processed.containsKey(vName))
				continue;

			Pair rootPair = new Pair();
			rootPair.vName = vName;
			rootPair.psf = vName;
			queue.addLast(rootPair);

			while (queue.size() != 0) {
				Pair rp = queue.removeFirst();

				processed.put(rp.vName, true);

				System.out.println(rp.vName + " via " + rp.psf);

				ArrayList<String> nbrNames = new ArrayList<>(vces.get(rp.vName).nbrs.keySet());
				for (String nbrName : nbrNames) {
					if (!processed.containsKey(nbrName)) {
						Pair np = new Pair();
						np.vName = nbrName;
						np.psf = rp.psf + nbrName;
						queue.add(np);
					}
				}
			}
		}
	}

	public void DFT() {
		HashMap<String, Boolean> processed = new HashMap<>();
		LinkedList<Pair> stack = new LinkedList<>();

		ArrayList<String> vNames = new ArrayList<>(vces.keySet());

		for (String vName : vNames) {
			if (processed.containsKey(vName))
				continue;

			Pair rootPair = new Pair();
			rootPair.vName = vName;
			rootPair.psf = vName;
			stack.addFirst(rootPair);

			while (stack.size() != 0) {
				Pair rp = stack.removeFirst();

				processed.put(rp.vName, true);

				System.out.println(rp.vName + " via " + rp.psf);

				ArrayList<String> nbrNames = new ArrayList<>(vces.get(rp.vName).nbrs.keySet());
				for (String nbrName : nbrNames) {
					if (!processed.containsKey(nbrName)) {
						Pair np = new Pair();
						np.vName = nbrName;
						np.psf = rp.psf + nbrName;
						stack.addFirst(np);
					}
				}
			}
		}
	}

	public boolean isAcyclic() {
		HashMap<String, Boolean> processed = new HashMap<>();
		LinkedList<Pair> queue = new LinkedList<>();

		ArrayList<String> vNames = new ArrayList<>(vces.keySet());

		for (String vName : vNames) {
			if (processed.containsKey(vName))
				continue;

			Pair rootPair = new Pair();
			rootPair.vName = vName;
			rootPair.psf = vName;
			queue.addLast(rootPair);

			while (queue.size() != 0) {
				Pair rp = queue.removeFirst();

				if (processed.containsKey(rp.vName))
					return false;

				processed.put(rp.vName, true);

				ArrayList<String> nbrNames = new ArrayList<>(vces.get(rp.vName).nbrs.keySet());
				for (String nbrName : nbrNames) {
					if (!processed.containsKey(nbrName)) {
						Pair np = new Pair();
						np.vName = nbrName;
						np.psf = rp.psf + nbrName;
						queue.add(np);
					}
				}
			}
		}

		return true;
	}

	public boolean isTree() {
		return this.isConnected() && this.isAcyclic();
	}

	public boolean isBipartite() {
		HashMap<String, String> processed = new HashMap<>();
		LinkedList<Pair> queue = new LinkedList<>();

		ArrayList<String> vNames = new ArrayList<>(vces.keySet());

		for (String vName : vNames) {
			if (processed.containsKey(vName))
				continue;

			Pair rootPair = new Pair();
			rootPair.vName = vName;
			rootPair.psf = vName;
			rootPair.color = "Red";

			queue.addLast(rootPair);

			while (queue.size() != 0) {
				Pair rp = queue.removeFirst();

				String oColor = processed.get(rp.vName);
				String nColor = rp.color;

				if (processed.containsKey(rp.vName))
					if (!oColor.equals(nColor))
						return false;

				processed.put(rp.vName, rp.color);

				ArrayList<String> nbrNames = new ArrayList<>(vces.get(rp.vName).nbrs.keySet());
				for (String nbrName : nbrNames) {
					if (!processed.containsKey(nbrName)) {
						Pair np = new Pair();
						np.vName = nbrName;
						np.psf = rp.psf + nbrName;

						if (rp.color.equals("Red"))
							np.color = "Green";
						else
							np.color = "Red";

						queue.add(np);
					}
				}
			}
		}

		return true;
	}

	public ArrayList<String> GCC() {
		ArrayList<String> result = new ArrayList<>();

		HashMap<String, Boolean> processed = new HashMap<>();
		LinkedList<Pair> queue = new LinkedList<>();

		ArrayList<String> vNames = new ArrayList<>(vces.keySet());

		for (String vName : vNames) {
			if (processed.containsKey(vName))
				continue;

			Pair rootPair = new Pair();
			rootPair.vName = vName;
			rootPair.psf = vName;
			queue.addLast(rootPair);

			String comp = "";
			while (queue.size() != 0) {
				Pair rp = queue.removeFirst();

				if (processed.containsKey(rp.vName))
					continue;

				processed.put(rp.vName, true);
				comp += rp.vName;

				ArrayList<String> nbrNames = new ArrayList<>(vces.get(rp.vName).nbrs.keySet());
				for (String nbrName : nbrNames) {
					if (!processed.containsKey(nbrName)) {
						Pair np = new Pair();
						np.vName = nbrName;
						np.psf = rp.psf + nbrName;
						queue.add(np);
					}
				}
			}
			result.add(comp);
		}
		return result;
	}

	private class DijkstraPair implements Comparable<DijkstraPair> {
		String vName;
		String psf;
		int csf;

		public DijkstraPair(String vName, String psf, int csf) {
			this.vName = vName;
			this.psf = psf;
			this.csf = csf;
		}

		@Override
		public int compareTo(DijkstraPair o) {
			return this.csf - o.csf;
		}

		@Override
		public String toString() {
			return "[" + psf + "@" + csf + "]";
		}
	}

	public HashMap<String, DijkstraPair> Dijkstra(String src) {
		HashMap<String, DijkstraPair> hm = new HashMap<>();
		PriorityQueue<DijkstraPair> heap = new PriorityQueue<>();

		ArrayList<String> vNames = new ArrayList<>(vces.keySet());
		for (String vName : vNames) {
			DijkstraPair dp = new DijkstraPair(vName, "", Integer.MAX_VALUE);

			if (src.equals(vName)) {
				dp.psf = vName;
				dp.csf = 0;
			}

			heap.add(dp);
			hm.put(vName, dp);
		}

		while (!heap.isEmpty()) {
			DijkstraPair rp = heap.remove();
			ArrayList<String> nbrNames = new ArrayList<>(vces.get(rp.vName).nbrs.keySet());
			for (String nbrName : nbrNames) {
				DijkstraPair np = hm.get(nbrName);
				int oldCost = np.csf;
				int newCost = rp.csf + vces.get(rp.vName).nbrs.get(nbrName);

				if (newCost < oldCost) {
					np.psf = rp.psf + nbrName;
					np.csf = newCost;

					heap.remove(np);
					heap.add(np);
				}
			}
		}

		return hm;
	}

	private class PrimsPair implements Comparable<PrimsPair> {
		String vName;
		String acqVtx;
		int csf;

		public PrimsPair(String vName, String acqVtx, int csf) {
			this.vName = vName;
			this.acqVtx = acqVtx;
			this.csf = csf;
		}

		@Override
		public int compareTo(PrimsPair o) {
			// TODO Auto-generated method stub
			return this.csf - o.csf;
		}
	}

	public Graph PrimsMST() {
		Graph mst = new Graph();

		HashMap<String, PrimsPair> hm = new HashMap<>();
		PriorityQueue<PrimsPair> heap = new PriorityQueue<>();

		ArrayList<String> vNames = new ArrayList<>(vces.keySet());
		for (String vName : vNames) {
			PrimsPair pp = new PrimsPair(vName, null, Integer.MAX_VALUE);

			heap.add(pp);
			hm.put(vName, pp);
		}

		while (!heap.isEmpty()) {
			PrimsPair rp = heap.remove();

			mst.addVertex(rp.vName);
			if (rp.acqVtx != null)
				mst.addEdge(rp.vName, rp.acqVtx, rp.csf);

			ArrayList<String> nbrNames = new ArrayList<>(vces.get(rp.vName).nbrs.keySet());
			for (String nbrName : nbrNames) {
				PrimsPair np = hm.get(nbrName);
				int oldCost = np.csf;
				int newCost = vces.get(rp.vName).nbrs.get(nbrName);

				if (newCost < oldCost) {
					np.vName = nbrName;
					np.acqVtx = rp.vName;
					np.csf = newCost;

					heap.remove(np);
					heap.add(np);
				}
			}
		}

		return mst;
	}

	private class KruskalPair implements Comparable<KruskalPair> {
		String v1Name;
		String v2Name;
		int weight;

		@Override
		public int compareTo(KruskalPair o) {
			// TODO Auto-generated method stub
			return this.weight - o.weight;
		}
	}

	private class Cluster {
		String data;
		Cluster parent;
		int size;

		public Cluster(String data) {
			this.data = data;
			this.parent = this;
			this.size = 1;
		}

		public Cluster find() {
			if (this.parent == this)
				return this;
			else
				return this.parent.find();
		}

		public void merge(Cluster o) {
			if (this.size > o.size) {
				o.parent = this;
				this.size += o.size;
			} else {
				this.parent = o;
				o.size += this.size;
			}
		}
	}

	public Graph Kruskal() {
		Graph mst = new Graph();

		PriorityQueue<KruskalPair> heap = new PriorityQueue<>();
		HashMap<String, Cluster> partition = new HashMap<>();

		ArrayList<String> vNames = new ArrayList<>(vces.keySet());
		for (String vName : vNames) {
			Cluster cluster = new Cluster(vName);
			partition.put(vName, cluster);

			ArrayList<String> nbrNames = new ArrayList<>(vces.get(vName).nbrs.keySet());
			for (String nbrName : nbrNames) {
				if (partition.containsKey(nbrName))
					continue;

				KruskalPair np = new KruskalPair();
				np.v1Name = vName;
				np.v2Name = nbrName;
				np.weight = vces.get(vName).nbrs.get(nbrName);
				heap.add(np);
			}
		}

		while (mst.numEdges() != this.numVertices() - 1) {
			KruskalPair rp = heap.remove();
			Cluster c1 = partition.get(rp.v1Name);
			Cluster c2 = partition.get(rp.v2Name);

			Cluster c3 = c1.find();
			Cluster c4 = c2.find();
			if (c3 != c4) {
				c3.merge(c4);
				mst.addVertex(rp.v1Name);
				mst.addVertex(rp.v2Name);
				mst.addEdge(rp.v1Name, rp.v2Name, rp.weight);
			}
		}

		return mst;
	}
}
