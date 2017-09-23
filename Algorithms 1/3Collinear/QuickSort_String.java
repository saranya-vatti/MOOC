public class QuickSort_String {
    private static void sort(String[] a, int lo, int hi) {
        if(hi<=lo) return;
        int pivotIndex = partition(a, lo, hi);
        sort(a,lo,pivotIndex -1);
        sort(a,pivotIndex+1,hi);
    }
    private static int partition(String[] a, int lo, int hi) {
        //assert isSorted(a, lo, mid); //precondition: a[lo..mid] sorted
        //assert isSorted(a, mid+1, hi); //predondition: a[mid+1..hi] sorted
        int i=lo,j=hi+1;
        String tmp;
        while(true) {
            while(a[++i].compareTo(a[lo]) < 0) {
                if(i==hi) break;
            }
            while(a[lo].compareTo(a[--j])<0) {
                if(j==lo) break;
            }
            if(i>=j) break;
            tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
        tmp = a[lo];
        a[lo] = a[j];
        a[j] = tmp;
        StdOut.printf("quicksort(%d, %d, %d): ", lo, j, hi);
        for(int k=0;k<a.length;k++) {
            StdOut.printf("%s ", a[k]);
        }
        StdOut.println();
        return j;
    }
    public static void main(String[] args) {
        String[] a = new String[12];
        a[0] = "A";
        a[1] = "A";
        a[2] =  "A";
        a[3] = "B";
        a[4] = "A";
        a[5] = "B";
        a[6] = "A";
        a[7] = "A";
        a[8] = "B";
        a[9] = "B";
        a[10] ="B";
        a[11] ="B";
        //StdRandom.shuffle(a);
        sort(a,0,a.length-1);
    }
}