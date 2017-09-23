public class MergeSort {
    private static void sort(Integer[] a, Integer[] aux, int lo, int hi) {
        if(hi<=lo) return;
        int mid = lo + (hi-lo)/2;
        sort(a,aux,lo,mid);
        sort(a,aux,mid+1,hi);
        //if(a[mid+1] >= a[mid]) return;
        merge(a,aux, lo, mid, hi);
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
                StdOut.printf("%d ", a[k]);
            }
            StdOut.println();
        //}
    }
    public static void main(String[] args) {
        Integer[] a = new Integer[12];
        Integer[] aux = new Integer[12];
        a[0] = 79;
        a[1] = 25;
        a[2] = 14;
        a[3] = 42;
        a[4] = 36;
        a[5] = 73;
        a[6] = 89;
        a[7] = 56;
        a[8] = 78;
        a[9] = 92;
        a[10] = 31;
        a[11] = 51;
        for(int i=0;i<aux.length;i++) {
            aux[i] = a[i];
        }
        sort(a,aux,0,a.length-1);
    }
}