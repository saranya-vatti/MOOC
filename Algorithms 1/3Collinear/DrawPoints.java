public class DrawPoints {
    public static void main(String[] args) {
        
        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 20);
        StdDraw.setYscale(0, 20);
        StdDraw.show(0);
        StdDraw.setPenRadius(0.01);  // make the points a bit larger
        In in = new In(args[0]);
        for (int i=0;i<7;i++) {
            int x = in.readInt();
            int y = in.readInt();
            StdOut.printf("(%d, %d)\n", x, y);
            Point p1 = new Point(x,y);
            p1.draw();
            x = in.readInt();
            y = in.readInt();
            StdOut.printf("(%d, %d)\n", x, y);
            Point p2 = new Point(x,y);
            p1.drawTo(p2);
        }
        StdDraw.show(0);

        // reset the pen radius
        StdDraw.setPenRadius();
    }
}