package Dec23;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ArticulationPoints {

	public static void main(String[] args) throws Exception {
		ArtPoint();
	}

	public static ArrayList<Integer> points;
	public static ArrayList<Edge> bridges;
	public static atpair[] result;
	public static int[][] edge;
	public static Scanner sc = new Scanner(System.in);

	// *SOLUTION*
	public static void ArtPoint() throws Exception {
		points = new ArrayList<>();
		bridges = new ArrayList<>();
		int n = sc.nextInt();
		int m = sc.nextInt();
		edge = new int[n][n];
		for (int i = 0; i < m; i++)
			addEdge(sc.nextInt(), sc.nextInt());
		result = new atpair[n];

		for (int i = 0; i < n; i++)
			result[i] = new atpair(i);

		for (int i = 0; i < n; i++) {
			if (result[i].processed) {
				continue;
			}
			DFT(0, i);

		}

		for (int i = 0; i < n; i++)
			result[i] = new atpair(i);

		for (int i = 0; i < n; i++) {
			if (result[i].processed) {
				continue;
			}
			DFTBridges(0, i);
		}

		Collections.sort(bridges);
		Collections.sort(points);

		System.out.println(points.size());
		for (int i = 0; i < points.size(); i++)
			System.out.print(points.get(i) + " ");
		System.out.println();
		System.out.println(bridges.size());
		for (int i = 0; i < bridges.size(); i++)
			System.out.println(bridges.get(i).u + " " + bridges.get(i).v);

	}

	private static void DFT(int time, int vtx) {
		result[vtx].processed = true;
		result[vtx].disc = time;
		result[vtx].low = time;
		int rootChildCounter = 0;
		for (int i = 0; i < edge.length; i++) {
			if (edge[vtx][i] == 1) {
				// fresh nbr
				if (result[i].processed == false) {
					rootChildCounter++;
					result[i].parent = vtx;
					DFT(time + 1, i);
					result[vtx].low = Math.min(result[vtx].low, result[i].low);
					if (result[vtx].parent != -1) {
						if (result[i].low >= result[vtx].disc) {
							points.add(vtx);
							result[vtx].isAtPt = true;
						}
					} else {
						// means its root
						if (rootChildCounter > 1) {
							result[vtx].isAtPt = true;
							points.add(vtx);
						}
					}
				}
				// processed nbr
				else if (result[vtx].parent != i) {
					result[vtx].low = Math.min(result[vtx].low, result[i].disc);
				}

				// parent
			}
		}
	}

	private static void DFTBridges(int time, int vtx) {
		result[vtx].processed = true;
		result[vtx].disc = time;
		result[vtx].low = time;
		for (int i = 0; i < edge.length; i++) {
			if (edge[vtx][i] == 1) {
				// fresh nbr
				if (result[i].processed == false) {
					result[i].parent = vtx;
					DFTBridges(time + 1, i);
					result[vtx].low = Math.min(result[vtx].low, result[i].low);
					if (result[i].low > result[vtx].disc) {
						bridges.add(new Edge(i, vtx));
					}
				}
				// processed nbr
				else if (result[vtx].parent != i) {
					result[vtx].low = Math.min(result[vtx].low, result[i].disc);
				}
				// parent
			}
		}
	}

	public static class atpair {
		int disc;
		int vtx;
		int low;
		boolean processed;
		boolean isAtPt;
		int parent = -1;

		atpair(int vtx) {
			this.vtx = vtx;
		}
	}

	public static class Edge implements Comparable<Edge> {
		int u;
		int v;

		public Edge(int u, int v) {
			if (u < v) {
				this.u = u;
				this.v = v;
			} else {
				this.v = u;
				this.u = v;
			}
		}

		@Override
		public int compareTo(Edge o) {
			return this.u * 10 + this.v - o.u * 10 - o.v;
		}
	}

	private static void addEdge(int i, int j) {
		edge[i][j] = 1;
		edge[j][i] = 1;
	}
}