public class MergeSort_Integer {
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
        a[0] = 22;
        a[1] = 48;
        a[2] = 32;
        a[3] = 95;
        a[4] = 15;
        a[5] = 25;
        a[6] = 82;
        a[7] = 89;
        a[8] = 71;
        a[9] = 90;
        a[10] =68;
        a[11] =34;
        for(int i=0;i<aux.length;i++) {
            aux[i] = a[i];
        }
        sort(a,aux,0,a.length-1);
    }
}