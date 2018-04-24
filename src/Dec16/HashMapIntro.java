package Dec16;

import java.util.ArrayList;
import java.util.HashMap;

public class HashMapIntro {

	public static void main(String[] args) {
		ArrayList<Integer> one = new ArrayList<>();
		one.add(1);
		one.add(1);
		one.add(2);
		one.add(2);
		one.add(2);
		one.add(3);
		one.add(5);

		ArrayList<Integer> two = new ArrayList<>();
		two.add(1);
		two.add(1);
		two.add(1);
		two.add(2);
		two.add(2);
		two.add(4);
		two.add(5);

		ArrayList<Integer> arr = new ArrayList<>();
		arr.add(2);
		arr.add(12);
		arr.add(9);
		arr.add(16);
		arr.add(10);
		arr.add(5);
		arr.add(3);
		arr.add(20);
		arr.add(25);
		arr.add(11);
		arr.add(1);
		arr.add(8);
		arr.add(6);

		System.out.println(GCE1(one, two));
		System.out.println(GCE2(one, two));
		longestCommonSequence(arr);
	}

	public static ArrayList<Integer> GCE1(ArrayList<Integer> one, ArrayList<Integer> two) {
		HashMap<Integer, Boolean> hm = new HashMap<>();

		for (int i = 0; i < one.size(); i++) {
			if (two.contains(one.get(i))) {
				hm.put(one.get(i), true);
			}
		}

		ArrayList<Integer> arr = new ArrayList<>(hm.keySet());
		return arr;
	}

	public static ArrayList<Integer> GCE2(ArrayList<Integer> one, ArrayList<Integer> two) {
		HashMap<Integer, Integer> fm = new HashMap<>();
		ArrayList<Integer> arr = new ArrayList<>();

		for (int val : one) {
			if (fm.containsKey(val)) {
				fm.put(val, fm.get(val) + 1);
			} else {
				fm.put(val, 1);
			}
		}

		for (int val : two) {
			if (fm.containsKey(val) && fm.get(val) > 0) {
				arr.add(val);
				fm.put(val, fm.get(val) - 1);
			}
		}

		return arr;
	}

	public static void longestCommonSequence(ArrayList<Integer> arr) {
		HashMap<Integer, Boolean> hm = new HashMap<>();

		for (int val : arr) {
			hm.put(val, false);
		}

		for (int val : arr) {
			if (hm.containsKey(val - 1) == false) {
				hm.put(val, true);
			}
		}

		int finalL = 0;
		int finalSP = 0;
		for (int val : arr) {
			int seqL = 0;
			if (hm.get(val)) {
				while (hm.containsKey(val + seqL)) {
					seqL++;
				}
			}

			if (seqL > finalL) {
				finalL = seqL;
				finalSP = val;
			}
		}

		for (int i = 0; i < finalL; i++)
			System.out.println(finalSP + i);

	}
}
