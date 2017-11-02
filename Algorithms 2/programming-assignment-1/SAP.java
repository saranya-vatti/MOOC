/******************************************************************************
 *  Name:    Saranya Vatti
 *  NetID:   N/A
 *  Precept: P01
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Description:  Implement a WordNet graph with operations such as shortest
 *  common ancestor.
 ******************************************************************************/
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdOut;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
public final class SAP {
    private final Digraph graph;
    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        this.graph = G;
    }

    private HashMap<Integer, Integer> bfs(int start) {
        HashMap<Integer, Integer> distanceFromStart = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        HashMap<Integer, Boolean> visited = new HashMap<>();
        queue.add(start);
        distanceFromStart.put(start, 0);
        visited.put(start, true);
        while (!queue.isEmpty()) {
            int parent = queue.remove();
            int distance = distanceFromStart.get(parent) + 1;
            for (int adj:graph.adj(parent)) {
                if (!visited.containsKey(adj)) {
                    visited.put(adj, true);
                    queue.add(adj);
                    if (distanceFromStart.containsKey(adj)) {
                        distanceFromStart.put(adj, Math.min(distance,
                                distanceFromStart.get(adj)));
                    } else {
                        distanceFromStart.put(adj, distance);
                    }
                }
            }
        }
        return distanceFromStart;
    }

    private int[] bfs(int start, int end) {
        HashMap<Integer, Integer> distanceFromStart = bfs(start);
        HashMap<Integer, Integer> distanceFromEnd = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        HashMap<Integer, Boolean> visited = new HashMap<>();
        distanceFromEnd.put(end, 0);
        visited.put(end, true);
        int[] currAnc = new int[2];
        currAnc[0] = -1;
        currAnc[1] = -1;
        if (distanceFromStart.containsKey(end)) {
            currAnc[0] = end;
            currAnc[1] = distanceFromStart.get(end);
        }
        queue.add(end);
        while (!queue.isEmpty()) {
            int parent = queue.remove();
            int distance = distanceFromEnd.get(parent) + 1;
            for (int adj:graph.adj(parent)) {
                if (!visited.containsKey(adj)) {
                    visited.put(adj, true);
                    queue.add(adj);
                    if (distanceFromEnd.containsKey(adj)) {
                        distance = Math.min(distance, distanceFromEnd.get(adj));
                    }
                    distanceFromEnd.put(adj, distance);
                    if (distanceFromStart.containsKey(adj)) {
                        int dist = distance + distanceFromStart.get(adj);
                        if (currAnc[0] != -1) {
                            if (dist <= currAnc[1]) {
                                currAnc[0] = adj;
                                currAnc[1] = dist;
                            }
                        } else {
                            currAnc[0] = adj;
                            currAnc[1] = dist;
                        }
                    }
                }
            }
        }
        return currAnc;
    }

    private int[] ancestorAndLength(int v, int w) {
        if (v < 0 || v > graph.V()-1 || w < 0 || w > graph.V()-1) {
            throw new java.lang.IllegalArgumentException();
        }
        if (v == w) {
            int[] arr = new int[2];
            arr[0] = v;
            arr[1] = 0;
            return arr;
        }
        return bfs(v, w);
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        int[] anc = ancestorAndLength(v, w);
        return anc[1];
    }

    // a common ancestor of v and w that participates in a shortest ancestral path;
    // -1 if no such path
    public int ancestor(int v, int w) {
        int[] anc = ancestorAndLength(v, w);
        return anc[0];
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w;
    // -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null) {
            throw new java.lang.IllegalArgumentException();
        }
        int minLength = -1;
        for (int v1:v) {
            for (int w1:w) {
                int len = length(v1, w1);
                if (minLength > len || minLength == -1) {
                    minLength = len;
                }
            }
        }
        return minLength;
    }

    // a common ancestor that participates in shortest ancestral path;
    // -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null) {
            throw new java.lang.IllegalArgumentException();
        }
        int minLength = -1;
        int anc = -1;
        for (int v1:v) {
            for (int w1:w) {
                int len = length(v1, w1);
                if (minLength > len || minLength == -1) {
                    minLength = len;
                    anc = ancestor(v1, w1);
                }
            }
        }
        return anc;
    }

    // do unit testing of this class
    public static void main(String[] args) {
        In in = new In(args[0]);
        int numOfV = in.readInt();
        int numOfE = in.readInt();
        Digraph G = new Digraph(numOfV);
        while (numOfE > 0) {
            int v = in.readInt();
            int w = in.readInt();
            G.addEdge(v, w);
            numOfE = numOfE - 1;
        }
        StdOut.println("Graph : " + G.toString());
        SAP sap = new SAP(G);
        int testcases = in.readInt();
        while (testcases > 0) {
            int v = in.readInt();
            int w = in.readInt();
            int[] anc = sap.ancestorAndLength(v, w);
            StdOut.printf("length, ancestor between %d and %d is %d, %d\n",
                        v, w, anc[0], anc[1]);
            testcases = testcases - 1;
        }
    }
}