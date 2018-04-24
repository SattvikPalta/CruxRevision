package Dec21;

import java.util.ArrayList;
import java.util.HashMap;

public class FloydWarshall {

	public static void main(String[] args) {
		HashMap<String, HashMap<String, Integer>> graph = new HashMap<>();

		graph.put("A", new HashMap<>());
		graph.put("B", new HashMap<>());
		graph.put("C", new HashMap<>());
		graph.put("D", new HashMap<>());

		graph.get("A").put("B", 5);
		graph.get("A").put("D", 10);
		graph.get("B").put("C", 3);
		graph.get("C").put("D", 1);

		HashMap<String, HashMap<String, FWpair>> result = new HashMap<>();
		
		ArrayList<String> vNames = new ArrayList<>(graph.keySet());
		for (String srcVname : vNames) {
			result.put(srcVname, new HashMap<>());
			for (String destVname : vNames) {
				FWpair fp = new FWpair();
				if (srcVname.equals(destVname)) {
					fp.psf = srcVname;
					fp.csf = 0;
				} else if (graph.get(srcVname).containsKey(destVname)) {
					fp.psf = srcVname + destVname;
					fp.csf = graph.get(srcVname).get(destVname);
				} else {
					fp.psf = null;
					fp.csf = Integer.MAX_VALUE;
				}
				result.get(srcVname).put(destVname, fp);
			}
		}

		for (String intVname : vNames) {
			for (String srcVname : vNames) {
				for (String desVname : vNames) {
					FWpair s2d = result.get(srcVname).get(desVname);
					FWpair s2i = result.get(srcVname).get(intVname);
					FWpair i2d = result.get(intVname).get(desVname);

					if (intVname.equals(srcVname) || intVname.equals(desVname))
						continue;

					if (i2d.psf == null || s2i.psf == null)
						continue;

					if (s2i.csf + i2d.csf < s2d.csf) {
						s2d.csf = s2i.csf + i2d.csf;
						s2d.psf = s2i.psf + " => " + i2d.psf;
					}
				}
			}
		}

		System.out.println(result);
	}

	private static class FWpair {
		String psf;
		int csf;

		public String toString() {
			return "[" + psf + "@" + csf + "]\n";
		}
	}
}
