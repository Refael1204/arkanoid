//  Refael Avrahami 206482655
package geometryShape;
import java.util.ArrayList;
import java.util.List;
/**
 * This class define the rectangle structure with some methods using it.
 */
public class Rectangle {
    private Point upperLeft;
    private final double width, height;
    /**
     * Enum to define line positions nicely.
     */
    public enum LinePosition {
        Undefined, Up, Down, Right, Left
    }
    /**
     * constructor - Create a new rectangle with location and width/height.
     * @param upperLeft - the upper-left point of the rectangle.
     * @param width - the width of the rectangle.
     * @param height - the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.width = width;
        this.height = height;
        this.upperLeft = upperLeft;
    }
    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     * @param line trajectory.
     * @return list of collision points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> points = new ArrayList<>();
        Line upLine = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX() + this.width, upperLeft.getY());
        Line downLine = new Line(upperLeft.getX(), upperLeft.getY() + this.height, upperLeft.getX() + this.width,
                upperLeft.getY() + this.height);
        Line leftLine = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX(), upperLeft.getY()
                + this.height);
        Line rightLine = new Line(upperLeft.getX() + this.width, upperLeft.getY(), upperLeft.getX() + this.width,
                upperLeft.getY() + this.height);

        if (upLine.intersectionWith(line) != null) { // check intersection with the upper line
            points.add(upLine.intersectionWith(line));
        }
        if (downLine.intersectionWith(line) != null) { // check intersection with the downer line
            points.add(downLine.intersectionWith(line));
        }
        if (leftLine.intersectionWith(line) != null) { // check intersection with the left line
            points.add(leftLine.intersectionWith(line));
        }
        if (rightLine.intersectionWith(line) != null) { // check intersection with the right line
            points.add(rightLine.intersectionWith(line));
        }
        return points;
    }
    /**
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * let know which side of object has been hit.
     * @param p - point of collision with the object.
     * @return which side was hit.
     */
    public LinePosition getIntersectionLinePosition(Point p) {
        Line upLine = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX() + this.width, upperLeft.getY());
        Line downLine = new Line(upperLeft.getX(), upperLeft.getY() + this.height, upperLeft.getX() + this.width,
                upperLeft.getY() + this.height);
        Line leftLine = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX(), upperLeft.getY()
                + this.height);
        Line rightLine = new Line(upperLeft.getX() + this.width, upperLeft.getY(), upperLeft.getX() + this.width,
                upperLeft.getY() + this.height);

        if (upLine.isPointOnTheLine(p)) {
            return LinePosition.Up;
        }

        if (downLine.isPointOnTheLine(p)) {
            return LinePosition.Down;
        }

        if (rightLine.isPointOnTheLine(p)) {
            return LinePosition.Right;
        }

        if (leftLine.isPointOnTheLine(p)) {
            return LinePosition.Left;
        }
        return LinePosition.Undefined;
    }
}
