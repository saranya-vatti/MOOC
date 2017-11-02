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
 *                  common ancestor.
 ******************************************************************************/

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public final class Outcast {
    private WordNet wn;
    public Outcast(WordNet wordnet)   {
        // constructor takes a WordNet object
        this.wn = wordnet;
    }

    public String outcast(String[] nouns) {
        // given an array of WordNet nouns, return an outcast
        int maxDist = 0;
        String leastRelated = "";
        for (int i = 0; i < nouns.length; i++) {
            String nounA = nouns[i];
            int dist = 0;
            for (int j = 0; j < nouns.length; j++) {
                String nounB = nouns[j];
                dist += this.wn.distance(nounA, nounB);
            }
            if (dist > maxDist) {
                maxDist = dist;
                leastRelated = nounA;
            }
        }
        return leastRelated;
    }

    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}