public class QuickSort_3Way_Integer {
    private static void sort(Integer[] a, int lo, int hi) {
        if(hi<=lo) return;
        int lt = lo, gt = hi;
        int v = a[lo];
        int i=lo;
        while(i<=gt) {
            if (a[i]<v) {
                int tmp = a[lt];
                a[lt] = a[i];
                a[i] = tmp;
                lt++;
                i++;
            } else if(a[i]>v) {
                int tmp = a[i];
                a[i] = a[gt];
                a[gt] = tmp;
                gt--;
            } else {
                i++;
            }
        }
        sort(a,lo,lt -1);
        sort(a,gt+1,hi);
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
        for(int k=0;k<a.length;k++) {
            StdOut.printf("%d ", a[k]);
        }
        StdOut.println();
    }
}