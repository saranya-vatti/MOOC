public class MergeSort_String {
    private static void sort(String[] a, String[] aux, int lo, int hi) {
        if(hi<=lo) return;
        int mid = lo + (hi-lo)/2;
        sort(a,aux,lo,mid);
        sort(a,aux,mid+1,hi);
        //if(a[mid+1] >= a[mid]) return;
        merge(a,aux, lo, mid, hi);
    }
    private static void merge(String[] a, String[] aux, int lo, int mid, int hi) {
        //assert isSorted(a, lo, mid); //precondition: a[lo..mid] sorted
        //assert isSorted(a, mid+1, hi); //predondition: a[mid+1..hi] sorted
        for(int k=lo;k<=hi;k++) {
            aux[k] = a[k];
        }
        int i = lo, j=mid+1;
        for(int k=lo; k<=hi; k++) {
            if(i>mid) a[k] =aux[j++];
            else if(j>hi) a[k] = aux[i++];
            else if(aux[j].compareTo(aux[i])<0) a[k]=aux[j++];
            else a[k] = aux[i++];
        }
        StdOut.printf("merge(%d, %d, %d): ", lo, mid, hi);
            for(int k=0;k<a.length;k++) {
                StdOut.printf("%s ", a[k]);
            }
            StdOut.println();
        //}
    }
    public static void main(String[] args) {
        String[] a = new String[12];
        String[] aux = new String[12];
        a[0] = "WEEN";
        a[1] = "FIXX";
        a[2] = "VAIN";
        a[3] = "LIVE";
        a[4] = "DOOM";
        a[5] = "NENA";
        a[6] = "EVE6";
        a[7] = "SADE";
        a[8] = "STYX";
        a[9] = "ACDC";
        a[10] = "BECK";
        a[11] = "INXS";
        for(int i=0;i<aux.length;i++) {
            aux[i] = a[i];
        }
        sort(a,aux,0,a.length-1);
    }
}