/**
 * Created by sole on 11/19/16.
 */
public class Point implements Comparable<Point> {
    private int _x, _y;
    // constructs the point (x, y)
    public Point(int x, int y){
        this._x = x;
        this._y = y;
    }
    // draws this point
    public void draw()
    // draws the line segment from this point to that point
    public void drawTo(Point that)
    // string representation
    public String toString(){
        return formatter.format("(%d,%d)", _x, _y);
    }
    // compare two points by y-coordinates, breaking ties by x-coordinates
    public int compareTo(Point that){
        if (this._y < that._y) return -1;
        if (this._y > that._y) return 1;
        if (this._x < that._x) return -1;
        if (this._x > that._x) return 1;
        return 0; //this should never happen since the 2 dots are on top one another on the graph and that's meaningless
    }
    // the slope between this point and that point
    public double slopeTo(Point that){
        Double delta_x, delta_y, slope;
        delta_x = (double)(that._x - this._x);
        delta_y = (double)(that._y - this._y);
        if (this.compareTo(that) == 0) return Double.NEGATIVE_INFINITY;
        if (delta_y == 0) return 0;
        if (delta_x == 0) return Double.POSITIVE_INFINITY;
        slope = delta_y / delta_x;
        return slope;
    }
    // compare two points by slopes they make with this point
    public Comparator<Point> slopeOrder(){
        
    }
}
