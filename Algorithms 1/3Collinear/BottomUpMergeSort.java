public class BottomUpMergeSort {
    private staic void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        assert isSorted(a, lo, mid); //precondition: a[lo..mid] sorted
        assert isSorted(a, mid+1, hi); //predondition: a[mid+1..hi] sorted
        for(int k=lo;k<=hi;k++) {
            aux[k] = a[k];
        }
        int i = lo, j=mid+1;
        for(int k=lo; k<=hi; k++) {
            if(i>mid) a[k] =aux[j++];
            else if(j>hi) a[k] = aux[i++];
            else if(less(aux[j], aux[i])) a[k]=aux[j++];
            else a[k] = aux[i++];
        }
    }
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        int N = a.length;
        auz = new Comparable[N];
        for(int sz=1;sz<=N;sz=sz+sz) {
            for(int lo=0;lo<N-sz;lo+=sz+sz) {
                merge(a,lo,lo+sz-1, Math.min(lo+sz+sz-1, N-1));
            }
        }
    }
    
}