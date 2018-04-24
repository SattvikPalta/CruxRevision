package Dec22;

import java.util.Scanner;

public class floodFill {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int m = scn.nextInt();
		int[][] arr = new int[n][m];

		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				arr[i][j] = scn.nextInt();

		boolean visited[][] = new boolean[n][m];
		if (floodFill(0, 0, n - 1, m - 1, arr, visited))
			System.out.println("Yes");
		else
			System.out.println("No");
		
		scn.close();
	}

	private static boolean floodFill(int cr, int cc, int er, int ec, int[][] arr, boolean[][] visited) {
		if (cc > ec || cr > er || cr < 0 || cc < 0)
			return false;

		if (cc == ec && cr == er)
			return true;

		if (arr[cr][cc] == 0)
			return false;

		if (visited[cr][cc] == true)
			return false;

		visited[cr][cc] = true;

		if (floodFill(cr + 1, cc, er, ec, arr, visited))
			return true;

		if (floodFill(cr, cc + 1, er, ec, arr, visited))
			return true;

		if (floodFill(cr - 1, cc, er, ec, arr, visited))
			return true;

		if (floodFill(cr, cc - 1, er, ec, arr, visited))
			return true;

		return false;
	}

}
