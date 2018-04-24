package Dec22;

import java.util.LinkedList;
import java.util.Scanner;

public class HackerEarthTSQ1 {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int m = scn.nextInt();

		int[][] arr = new int[n + 1][n + 1];
		for (int i = 0; i < m; i++) {
			int a = scn.nextInt();
			int b = scn.nextInt();
			arr[a][b] = 1;
		}

		boolean[] processed = new boolean[n + 1];

		LinkedList<Integer> stack = new LinkedList<>();

		for (int i = n; i > 0; i--) {
			if (processed[i] == true)
				continue;

			topologicalSort(i, processed, stack, arr);
		}

		while (stack.size() != 0)
			System.out.print(stack.removeFirst() + " ");
	}

	public static void topologicalSort(int n, boolean[] processed, LinkedList<Integer> stack, int[][] arr) {
		if (processed[n] == true)
			return;

		processed[n] = true;

		for (int nv = processed.length - 1; nv >= 0; nv--)
			if (arr[n][nv] == 1)
				topologicalSort(nv, processed, stack, arr);

		stack.addFirst(n);
	}

}
