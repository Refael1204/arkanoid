//  Refael Avrahami 206482655
package geometryShape;
import java.util.List;
/**
 * class Line - A line connects two points - a start point and an end point.
 * Lines have lengths, and may intersect with other lines. It can also tell if it is the same as another line segment.
 */
public class Line {
    private final Point start, end;
    private final double epsilon = Math.pow(10, -6);
    /**
     * constructor.
     * @param start - a start point of this line
     * @param end - an end point of this line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor.
     * @param x1 -  a double number, the x coordinate of one point
     * @param y1 -  a double number, the y coordinate of one point
     * @param x2 -  a double number, the x coordinate of the second point
     * @param y2 - a double number, the x coordinate of the second point
     */
    public Line(double x1, double y1, double x2, double y2) {
        if (y1 > y2) {
            start = new Point(x2, y2);
            end = new Point(x1, y1);
        } else {
            start = new Point(x1, y1);
            end = new Point(x2, y2);
        }
    }
    /**
     * @return this.start - the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return the incline of the line
     */
    public double incline() {
        if (this.start.getX() == this.end.getX()) {
            return Integer.MAX_VALUE;
        }
        return (start.getY() - end.getY()) / (start.getX() - end.getX());
    }
    /**
     * @return the y value of the intersecting  point between  the line and y-axis
     */
    public double intersectingY() {
        return (start.getY() - (incline() * start().getX()));
    }
    /**
     * @param other - another line.
     * @return the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        Point p1;
        if (other.incline() == this.incline()) { // if the incline of the lines equal the lines no intersecting
            if (this.end.equals(other.start)) {
                return this.end;
            }
            if (this.start.equals(other.end)) {
                return this.start;
            }
            return null;
        }
        double x1 =  (this.intersectingY() - other.intersectingY()) / (other.incline() - this.incline());
        double y1 = (this.incline() * x1) + this.intersectingY();
        p1 = new Point(x1, y1); // the intersection point
        if (this.start.getX() < this.end.getX()) { // if the intersection point not on the lines
            if ((p1.getX() > this.end.getX()) || (p1.getX() < this.start.getX())) {
                return null;
            }
        }
        if (this.start.getY() < this.end.getY()) { // if the intersection point not on the lines
            if ((p1.getY() > this.end.getY()) || (p1.getY() < this.start.getY())) {
                return null;
            }
        }
        if (this.start.getX() > this.end.getX()) { // if the intersection point not on the lines
            if ((p1.getX() < this.end.getX()) || (p1.getX() > this.start.getX())) {
                return null;
            }
        }
        if (this.start.getY() > this.end.getY()) { // if the intersection point not on the lines
            if ((p1.getY() < this.end.getY()) || (p1.getY() > this.start.getY())) {
                return null;
            }
        }
        if (other.start.getX() > other.end.getX()) { // if the intersection point not on the lines
            if ((p1.getX() < other.end.getX()) || (p1.getX() > other.start.getX())) {
                return null;
            }
        }
        if (other.start.getY() > other.end.getY()) { // if the intersection point not on the lines
            if ((p1.getY() < other.end.getY()) || (p1.getY() > other.start.getY())) {
                return null;
            }
        }
        if (other.start.getX() < other.end.getX()) { // if the intersection point not on the lines
            if ((p1.getX() > other.end.getX()) || (p1.getX() < other.start.getX())) {
                return null;
            }
        }
        if (other.start.getY() < other.end.getY()) { // if the intersection point not on the lines
            if ((p1.getY() > other.end.getY()) || (p1.getY() < other.start.getY())) {
                return null;
            }
        }
        return p1;
    }

    /**
     * @param rect - collision rectangle.
     * @return points.get(closestIntersection) - the closest collision point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> points = rect.intersectionPoints(this);
        if (points.isEmpty()) {
            return null;
        }
        double minDistance = this.start.distance(points.get(0)); // the distance between the first point in the list
        int closestIntersection = 0;
        // Goes over all the points in the list and keeps the position of the point with the minimum distance
        for (int i = 1; i < points.size(); i++) {
            double tempDistance = this.start.distance(points.get(i));
            if (tempDistance < minDistance) {
                minDistance = tempDistance;
                closestIntersection = i;
            }
        }

        return points.get(closestIntersection);
    }
    /**
     * if a point is on the line.
     * @param p point.
     * @return true or false.
     */
    public boolean isPointOnTheLine(Point p) {
        if (p.distance(start) + p.distance(end) <= start.distance(end) + epsilon
                && p.distance(start) + p.distance(end) >= start.distance(end) - epsilon) {
            return true;
        }
        return false;
    }
}