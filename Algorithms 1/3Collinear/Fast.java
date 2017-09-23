import java.util.Comparator;
import java.lang.*;
import java.util.*;
/*
 * import stdlib.*;
 * import algs4.*;
 */

public class Fast {
    public static void main(String[] args) {
        
        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(0.01);  // make the points a bit larger
        
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Point[] arr = new Point[N];
        Point[] aux = new Point[N];
        int m = 0;
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            aux[m] = p;
            arr[m++] = p;
            p.draw();
        }
        Arrays.sort(arr);
        
        /*StdOut.printf("[DEBUG1]->");
        for (int z = 0; z < N; z++) {
            StdOut.printf("%s ->", arr[z]);
        }
        StdOut.println();
        StdOut.println();*/
            
        for (int i = 0; i < N; i++) {
            Point p = arr[i];
            Arrays.sort(aux, p.SLOPE_ORDER);
            
            /*StdOut.printf("[DEBUG2]->");
            for (int z = 0; z < N; z++) {
                StdOut.printf("%s ->", aux[z]);
            }
            StdOut.println();
            
            StdOut.printf("[DEBUG3]->");
            for (int z = 0; z < N; z++) {
                StdOut.print(p.slopeTo(aux[z]));
                StdOut.printf(" -> ");
            }
            StdOut.println();
            StdOut.println();*/
            
            Double slope1 = new Double(p.slopeTo(aux[0]));
            int counter = 1;
            String str = "" + p;
            int lo,hi;
            for (int j = 1; j < N; j++) {
                Double slope2 = p.slopeTo(aux[j]);
                if (slope1.equals(slope2)) {
                    counter++;
                }
                if (!slope1.equals(slope2) || j == N - 1){
                    if (counter >= 3) {
                        
                        /*StdOut.println();
                        StdOut.printf("[DEBUG4] Counter is %d. j is %d. ", counter, j);
                        StdOut.println();*/
                        
                        if( slope1.equals(slope2) && j == N - 1) {
                            lo = j - counter + 1;
                            hi = j;
                        } else {
                            lo = j - counter;
                            hi = j - 1;
                        }
                        
                        /*StdOut.printf("[DEBUG5] Printing the points");
                        for (int z = lo; z <= hi; z++) {
                            StdOut.print(aux[z]);
                            StdOut.printf(" -> ");
                        }
                        StdOut.println();
                        StdOut.println();*/
                                             
                        Arrays.sort(aux, lo, hi + 1);
                        
                        if (p.compareTo(aux[lo]) <= 0) {
                            for (int n = lo; n <= hi; n++) {
                                str = str + " -> " + aux[n];
                            }
                            StdOut.println(str);
                            p.drawTo(aux[hi]);
                        }
                    }
                    slope1 = slope2;
                    counter = 1;
                    str = "" + p;
                }
            }
        }
        
        // display to screen all at once
        StdDraw.show(0);

        // reset the pen radius
        StdDraw.setPenRadius();
    }
}