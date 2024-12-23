//  Refael Avrahami 206482655
package Default;

import geometryShape.Point;

/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * constructor.
     * @param dx - the change in position on the x axes
     * @param dy - the change in position on the y axes
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * @return dx - the change in position on the x axes
     */
    public double getDx() {
        return this.dx;
    }
    /**
     * @return dy - the change in position on the y axes
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * @param p - get a point
     * @return -  a new point with position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * @param angle - A number that represents the angle of motion
     * @param speed - the speed of the ball
     * @return the velocity at the appropriate angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = (speed * Math.sin(Math.toRadians(angle)));
        double dy = -(speed * Math.cos(Math.toRadians(angle)));
        return new Velocity(dx, dy);
    }
    /**
     * Changes velocity of y by 180 degrees.
     */
    public void changeDyDirection() {
        this.dy = dy * (-1);
    }
    /**
     * Changes velocity of x by 180 degrees.
     */
    public void changeDxDirection() {
        this.dx = dx * (-1);
    }
}

