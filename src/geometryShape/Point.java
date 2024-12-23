//  Refael Avrahami 206482655
package geometryShape;
/**
 * class Point - The class Responsible for each feature of each point.
 */
public class Point {
    private double x, y;

    /**
     *
     * @param x - a double number, the x coordinate of the point
     * @param y - a double number, the y coordinate of the point
     * the constructor Creates a point from the coordinate we get
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param other - a point with an x and a y value
     * @return Math.sqrt(d) - the distance of this point to the other point
     */
    public double distance(Point other) {
        double d;
        d = ((other.getX() - this.x) * (other.getX() - this.x)) + ((other.getY() - this.y) * (other.getY() - this.y));
        return Math.sqrt(d);
    }

    /**
     * @param other - a point with an x and a y value
     * @return true/false - return true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        if (other.getX() == this.x && other.getY() == this.y) {
            return true;
        }
        return false;

    }

    /**
     * @return - the x value of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * @param x - the x value of point
     * setting the x value of this point
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     *
     * @param y -the y value of point
     * setting the y value of this point
     */
    public void setY(double y) {
        this.y = y;
    }
    /**
     * @return -the y value of this point
     */
    public double getY() {
        return this.y;
    }
}
