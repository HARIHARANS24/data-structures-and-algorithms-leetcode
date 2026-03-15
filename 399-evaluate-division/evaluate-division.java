import java.util.*;
class Solution {
 public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
 Map<String, Map<String, Double>> graph = new HashMap<>();
 for (int i = 0; i < equations.size(); i++) {
 String a = equations.get(i).get(0);
 String b = equations.get(i).get(1);
 double val = values[i];
 graph.putIfAbsent(a, new HashMap<>());
 graph.putIfAbsent(b, new HashMap<>());
 graph.get(a).put(b, val);
 graph.get(b).put(a, 1.0 / val);
 }
 double[] res = new double[queries.size()];
 for (int i = 0; i < queries.size(); i++) {
 String src = queries.get(i).get(0);
 String dst = queries.get(i).get(1);
 if (!graph.containsKey(src) || !graph.containsKey(dst)) {
 res[i] = -1.0;
 } else if (src.equals(dst)) {
 res[i] = 1.0;
 } else {
 res[i] = dfs(src, dst, 1.0, new HashSet<>(), graph);
 }
 }
 return res;
 }
 private double dfs(String cur, String target, double prod, Set<String> vis, Map<String, Map<String, Double>> graph) {
 vis.add(cur);
 Map<String, Double> neighbors = graph.get(cur);
 if (neighbors.containsKey(target)) return prod * neighbors.get(target);
 for (String next : neighbors.keySet()) {
 if (!vis.contains(next)) {
 double res = dfs(next, target, prod * neighbors.get(next), vis, graph);
 if (res != -1.0) return res;
 }
 }
 return -1.0;
 }
}