public class Point2D {
    public final Comparator<Point2D> POLAR_ORDE = new PolarOrder();
    private final double x,y;
    private static int ccw(Ponit2D a, Point2D b, Point2D c) {
        double area2 = (b.x-a.x)*(c.y-a.y) - (b.y-a.y)*(c.x-a.x);
        if(area2<0) {
            //clockwise
            return -1;
        } else if(area2>0) {
            //anticlockwise
            return +1;
        } else {
            //collinear
            return 0;
        }
    }
    private class PolarOrder implements Comparator<Point2D> {
        public int compare (Point2D q1, Point2D q2) {
            double dy1 = q1.y - y;
            double dy2 = q2.y -y;
            if(dy1 == 0 && dy2 == 0) {
                //p,q1,q2 horizontal
            } else if(dy1>=0 && dy2<0) {
                //q1 above p, q2 below p
                return -1;
            } else if(dy2 >=0 && dy1 < 0) {
                //q1 below p, q2 above p
                return +1; 
            } else {
                //both above or below p
                return -ccw(Point 2D.this, q1, q1);
            }
        }
    }