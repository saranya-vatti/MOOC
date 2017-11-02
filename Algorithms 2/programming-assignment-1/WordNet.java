/******************************************************************************
 *  Name:    Saranya Vatti
 *  NetID:   N/A
 *  Precept: P01
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Description:  Implement a WordNet graph with operations like shortest common
 *  ancestor.
 ******************************************************************************/

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdOut;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Arrays;

public final class WordNet {

    // Each synset has the list of nouns
    private final HashMap<Integer, String[]> synsetMap = new HashMap<Integer,
            String[]>();

    // nouns: Each noun belongs to the list of synsetIds
    private final HashMap<String, LinkedList<Integer>> nouns =
            new HashMap<String, LinkedList<Integer>>();
    private final SAP sap;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null) {
            throw new java.lang.IllegalArgumentException();
        }
        // "check":
        // Map containing all the roots. Initially all the sunsets.
        // Everytime child->parent relation is created,
        // child is deleted from this list. Ideally, after all the initializations,
        // only one parent, the root, must be left.
        Set<Integer> check = new LinkedHashSet<Integer>();
        In synsetsInput = new In(synsets);
        int synsetId = 0;
        while (!synsetsInput.isEmpty()) {
            String[] arr = synsetsInput.readLine().split(",");
            synsetId = Integer.parseInt(arr[0]);
            String[] synsetArr = arr[1].split(" ");
            for (int i = 0; i < synsetArr.length; i++) {
                if (!nouns.containsKey(synsetArr[i])) {
                    nouns.put(synsetArr[i], new LinkedList<Integer>());
                }
                nouns.get(synsetArr[i]).push(synsetId);
            }
            synsetMap.put(synsetId, synsetArr);
            check.add(synsetId);
        }
        Digraph dig = new Digraph(synsetId+1);
        In hypernymsInput = new In(hypernyms);
        while (!hypernymsInput.isEmpty()) {
            String[] arr = hypernymsInput.readLine().split(",");
            int fromSynsetId = Integer.parseInt(arr[0]);
            for (int i = 1; i < arr.length; i++) {
                int toSynsetId = Integer.parseInt(arr[i]);
                check.remove(fromSynsetId);
                dig.addEdge(fromSynsetId, toSynsetId);
            }
        }
        this.sap = new SAP(dig);

        // The constructor should throw a java.lang.IllegalArgumentException if the
        // input does not correspond to a rooted DAG
        if (check.size() != 1) {
            throw new java.lang.IllegalArgumentException();
        }
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        Iterable<String> iterable = nouns.keySet();
        return iterable;
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        //  All methods and the constructor should throw a
        // java.lang.IllegalArgumentException if any argument is null
        if (word == null) {
            throw new java.lang.IllegalArgumentException();
        }
        return nouns.containsKey(word);
    }

    public int distance(String nounA, String nounB) {
        // The distance() and sap() methods should throw a
        // java.lang.IllegalArgumentException unless both of the noun arguments are
        // WordNet nouns.
        if (nounA == null || nounB == null || !isNoun(nounA) || !isNoun(nounB)) {
            throw new java.lang.IllegalArgumentException();
        }
        return sap.length(nouns.get(nounA), nouns.get(nounB));
    }

    // a synset (second field of synsets.txt) that is the common ancestor
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        // The distance() and sap() methods should throw a
        // java.lang.IllegalArgumentException unless both of the noun arguments are
        // WordNet nouns.
        if (nounA == null || nounB == null || !isNoun(nounA) || !isNoun(nounB)) {
            throw new java.lang.IllegalArgumentException();
        }
        int ancId = sap.ancestor(nouns.get(nounA), nouns.get(nounB));
        String[] arr = synsetMap.get(ancId);
        String synsetString = Arrays.toString(arr);
        synsetString = synsetString.replace(", ", " ");
        synsetString = synsetString.replace("[", "");
        return synsetString.replace("]", "");
    }

    // do unit testing of this class
    public static void main(String[] args) {
        In in = new In(args[0]);
        WordNet wn = new WordNet(in.readString(), in.readString());
        int testcases = in.readInt();
        while (testcases > 0) {
            String nounA = in.readString();
            String nounB = in.readString();
            StdOut.printf("dist, sap between %s and %s is %d and %s\n", nounA, nounB,
                    wn.distance(nounA, nounB), wn.sap(nounA, nounB));
            testcases = testcases - 1;
        }
    }
}