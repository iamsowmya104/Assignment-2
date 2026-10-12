import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    int u, v, weight;

    public Edge(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        if (this.weight != other.weight) {
            return Integer.compare(this.weight, other.weight);
        }
        int sumThis = this.u + this.v + this.weight;
        int sumOther = other.u + other.v + other.weight;
        return Integer.compare(sumThis, sumOther);
    }
}

class DisjointSet {
    int[] parent;

    public DisjointSet(int n) {
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
    }

    public int find(int i) {
        if (parent[i] == i)
            return i;
        return parent[i] = find(parent[i]);
    }

    public boolean union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);
        if (rootI != rootJ) {
            parent[rootI] = rootJ;
            return true;
        }
        return false;
    }
}

class Result {

    public static int kruskals(int gNodes, List<Integer> gFrom, List<Integer> gTo, List<Integer> gWeight) {
        List<Edge> edges = new ArrayList<Edge>();
        int m = gFrom.size();

        for (int i = 0; i < m; i++) {
            edges.add(new Edge(gFrom.get(i), gTo.get(i), gWeight.get(i)));
        }

        Collections.sort(edges);

        DisjointSet dsu = new DisjointSet(gNodes);
        int totalWeight = 0;
        int edgesCount = 0;

        for (Edge edge : edges) {
            if (dsu.union(edge.u, edge.v)) {
                totalWeight += edge.weight;
                edgesCount++;
                if (edgesCount == gNodes - 1) {
                    break;
                }
            }
        }

        return totalWeight;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] gNodesEdges = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int gNodes = Integer.parseInt(gNodesEdges[0]);
        int gEdges = Integer.parseInt(gNodesEdges[1]);

        List<Integer> gFrom = new ArrayList<Integer>();
        List<Integer> gTo = new ArrayList<Integer>();
        List<Integer> gWeight = new ArrayList<Integer>();

        for (int i = 0; i < gEdges; i++) {
            String[] gFromToWeight = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            gFrom.add(Integer.parseInt(gFromToWeight[0]));
            gTo.add(Integer.parseInt(gFromToWeight[1]));
            gWeight.add(Integer.parseInt(gFromToWeight[2]));
        }

        int res = Result.kruskals(gNodes, gFrom, gTo, gWeight);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
