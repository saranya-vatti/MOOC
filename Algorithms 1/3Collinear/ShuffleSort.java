public class ShuffleSort {
    public static void sort((Comparable[] a) {
        int N = a.length;
        for(int i=0;i<N;i++) {
            exch(a[i], a[StdOut.Random(i)]);
        }
    }
}