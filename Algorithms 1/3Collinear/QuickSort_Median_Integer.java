public class QuickSort_Median_Integer {
    private static void sort(Integer[] a, int lo, int hi) {
        if(hi<=lo) return;
        int m = medianOf3(a, lo, lo+(hi-lo)/2);
        swap(a,lo,m);
        int pivotIndex = partition(a, lo, hi);
        sort(a,lo,pivotIndex -1);
        sort(a,pivotIndex+1,hi);
    }
    private static int partition(Integer[] a, int lo, int hi) {
        //assert isSorted(a, lo, mid); //precondition: a[lo..mid] sorted
        //assert isSorted(a, mid+1, hi); //predondition: a[mid+1..hi] sorted
        int i=lo,j=hi+1,tmp;
        while(true) {
            while(a[++i]<a[lo]) {
                if(i==hi) break;
            }
            while(a[lo]<a[--j]) {
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
            StdOut.printf("%d ", a[k]);
        }
        StdOut.println();
        return j;
    }
    public static void main(String[] args) {
        Integer[] a = new Integer[12];
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
        StdRandom.shuffle(a);
        sort(a,0,a.length-1);
    }
}