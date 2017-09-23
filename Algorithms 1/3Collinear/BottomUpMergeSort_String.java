public class BottomUpMergeSort_String {
    private static void sort(String[] a) {
        int N = a.length;
        String[] aux = new String[N];
        for(int sz=1;sz<=N;sz=sz+sz) {
            for(int lo=0;lo<N-sz;lo+=sz+sz) {
                merge(a,aux,lo,lo+sz-1, Math.min(lo+sz+sz-1, N-1));
            }
        }
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
            else if(aux[j].compareTo(aux[i])<1) a[k]=aux[j++];
            else a[k] = aux[i++];
        }
        StdOut.printf("merge(%d, %d, %d): ", lo, mid, hi);
            for(int k=0;k<a.length;k++) {
                StdOut.printf("%s ", a[k]);
            }
            StdOut.println();
    }
    public static void main(String[] args) {
        String[] a = new String[12];
        a[0] = "jade";
        a[1] = "plum";
        a[2] = "lust";
        a[3] = "corn";
        a[4] = "ecru";
        a[5] = "pine";
        a[6] = "navy";
        a[7] = "mist";
        a[8] = "leaf";
        a[9] = "rose";
        a[10] = "silk";
        a[11] = "blue";
        sort(a);
    }
}