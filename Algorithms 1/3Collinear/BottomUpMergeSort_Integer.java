public class BottomUpMergeSort_Integer {
    private static void sort(Integer[] a) {
        int N = a.length;
        Integer[] aux = new Integer[N];
        for(int sz=1;sz<=N;sz=sz+sz) {
            for(int lo=0;lo<N-sz;lo+=sz+sz) {
                merge(a,aux,lo,lo+sz-1, Math.min(lo+sz+sz-1, N-1));
            }
        }
    }
    private static void merge(Integer[] a, Integer[] aux, int lo, int mid, int hi) {
        //assert isSorted(a, lo, mid); //precondition: a[lo..mid] sorted
        //assert isSorted(a, mid+1, hi); //predondition: a[mid+1..hi] sorted
        for(int k=lo;k<=hi;k++) {
            aux[k] = a[k];
        }
        int i = lo, j=mid+1;
        for(int k=lo; k<=hi; k++) {
            if(i>mid) a[k] =aux[j++];
            else if(j>hi) a[k] = aux[i++];
            else if(aux[j]<aux[i]) a[k]=aux[j++];
            else a[k] = aux[i++];
        }
        StdOut.printf("merge(%d, %d, %d): ", lo, mid, hi);
            for(int k=0;k<a.length;k++) {
                StdOut.printf("%s ", a[k]);
            }
            StdOut.println();
    }
    public static void main(String[] args) {
        Integer[] a = new Integer[10];
        a[0] = 47;
        a[1] = 77;
        a[2] = 81;
        a[3] = 80;
        a[4] = 59;
        a[5] = 56;
        a[6] = 62;
        a[7] = 20;
        a[8] = 36;
        a[9] = 40;
        sort(a);
    }
}