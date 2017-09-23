import java.lang.*;
import java.util.*;
/*
 * import stdlib.*;
 * import algs4.*;
 */
public class Brute {
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
        int m = 0;
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            arr[m++] = p;
        }
        Arrays.sort(arr);
        for (int i = 0; i < N - 3; i++) {
            for (int j = i + 1; j < N - 2; j++) {
                for (int k = j + 1; k < N - 1; k++) {
                    for (int l = k + 1; l < N; l++) {
                        Point p = arr[i];
                        Point q = arr[j];
                        Point r = arr[k];
                        Point s = arr[l];       
                        Double slope1 = new Double(p.slopeTo(q));
                        Double slope2 = new Double(p.slopeTo(r));
                        Double slope3 = new Double(p.slopeTo(s));
                        if (slope1.equals(slope2) && slope1.equals(slope3)) {
                            StdOut.println(p + " -> " + q + " -> " + r + " -> " + s);
                            p.draw();
                            s.draw();
                            p.drawTo(s);
                        }
                    }
                }
            }
        }
        
        // display to screen all at once
        StdDraw.show(0);

        // reset the pen radius
        StdDraw.setPenRadius();
    }
}
            
            