public class ShuffleSortKnuth {
    public static void sort((Comparable[] a) {
        int N = a.length;
        for(int i=0;i<N;i++) {
            exch(a, i, StdOut.Random(i+1));
        }
    }
}