import java.lang.*;
import stdlib.*;
import java.util.*;
public class SelectionSort {
    public static void main(String[] args) {
        Integer[] a = new Integer[10]; 
        a[0] = 78;
        a[1] = 24;
        a[2] = 18;
        a[3] = 62;
        a[4] = 79;
        a[5] = 43;
        a[6] = 52;
        a[7] = 85;
        a[8] = 19;
        a[9] = 94;
        for(int i = 0;i<4;i++) {
            int min = i;
            for (int j = i+1; j < a.length; j++) { 
                if(a[j]<a[min]) {
                    min = j;
                }
            }
            int tmp = a[i];
            a[i] = a[min];
            a[min] = tmp;
        }        
        for(int i=0;i<a.length;i++){
            StdOut.printf("%d ", a[i]);
        }
        StdOut.println();
    }
}