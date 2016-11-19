import java.util.Arrays;
import java.util.Iterator;

import edu.princeton.cs.algs4.Queue;
/**
 * Created by sole on 11/19/16.
 */
public class FastCollinearPoints {
    // finds all line segments containing 4 or more points
    private Queue<LineSegment> segqueue;

    public FastCollinearPoints(Point[] points){
        segqueue = new Queue<LineSegment>();
        Point current = null;
        Double slope = null;
        int counter;
        Point[] qpoints = points.clone();
        for (int i = 0; i < points.length; i++){
            Arrays.sort(qpoints, points[i].slopeOrder());
            counter = 0;
            slope = null;
            for (int j = 0; j < points.length; j++){
                if (j != i){
                    current = qpoints[j];
                    if (slope == points[i].slopeTo(qpoints[j]))
                        counter++;
                    slope = points[i].slopeTo(qpoints[j]);
                }
            }
            if (counter >= 2) { // first wasnt counted meep
                LineSegment segment = new LineSegment(points[i], current);
                segqueue.enqueue(segment);
            }
        }
    }
    // the number of line segments
    public int numberOfSegments(){
        return segqueue.size();
    }
    // the line segments
    public LineSegment[] segments(){
        Iterator<LineSegment> ls_iterator = this.segqueue.iterator();
        LineSegment[] lineSegmentsArray = new LineSegment[segqueue.size()];
        for (int i = 0; i < lineSegmentsArray.length; i++){
            lineSegmentsArray[i] = ls_iterator.next();
        }
        return lineSegmentsArray;
    }
}
