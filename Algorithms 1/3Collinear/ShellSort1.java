public class ShellSort1 {
    //3x+3
    public static void main(String[] args) {
        Integer[] a = new Integer[10];
        a[0] = 84;
        a[1] = 35;
        a[2] = 87;
        a[3] = 88;
        a[4] = 73;
        a[5] = 79;
        a[6] = 13;
        a[7] = 28;
        a[8] = 63;
        a[9] = 93;
        int N = a.length;
        int h = 1;
        while (h<N/3) h = 3*h + 1; //1,4,13,40,121,364,..
        while(h>=1) {
            //h-sorting array
            for(int i = h;i<N;i++){
                for(int j=i;j>=h&& a[j]<a[j-h]; j-=h) {
                    int tmp = a[j];
                    a[j] = a[j-h];
                    a[j-h] = tmp;
                }
            }
            if(h==4) {
                for(int i=0;i<a.length;i++){
                    StdOut.printf("%d ", a[i]);
                }
                StdOut.println();
            }
            h=h/3;
        }
        for(int i=0;i<a.length;i++){
            StdOut.printf("%d ", a[i]);
        }
        StdOut.println();
    }
}