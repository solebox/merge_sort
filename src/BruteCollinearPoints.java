
/**
 * Created by sole on 11/19/16.
 */

import edu.princeton.cs.algs4.*;

import javax.sound.sampled.Line;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class BruteCollinearPoints {
    // finds all line segments containing 4 points
    private Queue<LineSegment> segments = new Queue<LineSegment>();

    public BruteCollinearPoints(Point[] points){
        Point point1, point2, point3, point4;

        if (points == null)
            throw new NullPointerException();
        Arrays.sort(points);
        for (int p1 = 0; p1 < points.length-3; p1++){
            for (int p2 = p1+=1; p2 < points.length; p2++){
                for (int p3 = p1+=2; p3 < points.length; p3++){
                    for (int p4 = p1+=3; p4 < points.length; p4++){
                        if (p1 != p2 && p1 != p3 && p1 != p4 && p2 != p3 && p2 != p4 && p3 != p4){
                            point1 = points[p1];
                            point2 = points[p2];
                            point3 = points[p3];
                            point4 = points[p4];

                            checkPointsDifferent(point1, point2, point3, point4); //throws exception if 2 p are the same
                            if (fourOnLine(point1, point2, point3, point4)){
                                segments.enqueue(new LineSegment(point1, point4));
                            }

                        }else{
                            continue;
                        }
                    }
                }
            }

        }
    }
    private boolean fourOnLine(Point p1, Point p2, Point p3, Point p4){
        Double slope12 = p1.slopeTo(p2);
        Double slope13 = p1.slopeTo(p3);
        Double slope14 = p1.slopeTo(p4);
        return Objects.equals(slope12, slope13) && Objects.equals(slope13, slope14);
    }

    private void checkPointsDifferent(Point point1, Point point2, Point point3, Point point4){
        if (point1.compareTo(point2) == 0 || point1.compareTo(point3) == 0 ||
                point1.compareTo(point4) == 0){
            throw new IllegalArgumentException();
        }
        if (point2.compareTo(point3) == 0 || point2.compareTo(point4) == 0){
            throw new IllegalArgumentException();
        }
        if (point3.compareTo(point4) == 0){
            throw new IllegalArgumentException();
        }
    }
    // the number of line segments
    public int numberOfSegments(){
        return segments.size();
    }
    // the line segments
    public LineSegment[] segments(){
        Iterator<LineSegment> segmentsIterator = segments.iterator();
        LineSegment[] retSegments = new LineSegment[segments.size()];
        for (int i = 0; i < segments.size(); i++){
            retSegments[i] = segmentsIterator.next();
        }
        return retSegments;
    }
    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}