import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

/**
 * Created by sole on 11/19/16.
 */
public class Point implements Comparable<Point> {
    private int x, y;
    private static Comparator<Point> BY_SLOPE_ORDER;
    // constructs the point (x, y)
    public Point(int x, int y){
        BY_SLOPE_ORDER = new BySlopeOrder(this);
        this.x = x;
        this.y = y;
    }
    // draws this point
    public void draw(){
        StdDraw.point(x, y);
    }
    // draws the line segment from this point to that point
    public void drawTo(Point that){
        StdDraw.line(this.x, this.y, that.x, that.y);
    }
    // string representation
    public String toString(){
        return "(" + x + ", " + y + ")";
    }
    // compare two points by y-coordinates, breaking ties by x-coordinates
    public int compareTo(Point that){
        if (this.y < that.y) return -1;
        if (this.y > that.y) return 1;
        if (this.x < that.x) return -1;
        if (this.x > that.x) return 1;
        return 0; //this should never happen since the 2 dots are on top one another on the graph and that's meaningless
    }
    // the slope between this point and that point
    public double slopeTo(Point that){
        Double delta_x, delta_y, slope;
        delta_x = (double)(that.x - this.x);
        delta_y = (double)(that.y - this.y);
        if (this.compareTo(that) == 0) return Double.NEGATIVE_INFINITY;
        if (delta_y == 0) return 0;
        if (delta_x == 0) return Double.POSITIVE_INFINITY;
        slope = delta_y / delta_x;
        return slope;
    }
    // compare two points by slopes they make with this point
    public Comparator<Point> slopeOrder(){
        return BY_SLOPE_ORDER;

    }

    private static class BySlopeOrder implements Comparator<Point> {
        private Point _p;
        public BySlopeOrder(Point p){
             _p = p;
        }
        private Double slope_a, slope_b;
        public int compare(Point a, Point b) {
            slope_a = _p.slopeTo(a);
            slope_b = _p.slopeTo(b);
            if (slope_a < slope_b) return -1;
            if (slope_b < slope_a) return 1;
            return 0;
        }
    }
}
