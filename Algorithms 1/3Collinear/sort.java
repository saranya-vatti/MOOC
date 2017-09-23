public class sort {
    public static void sort (Comparable[] a) {
        int N = a.length;
        for(int i = 0; i < N; i++) {
            if (a[j].compareTo(a[j-1]) < 0)
                exch(a, j, j-1);
            else break;
        }
    }
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i ++) {
            if(less(a[i], a[i-1])) return false;
            return true;
        }
    }
}