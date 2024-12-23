//  Refael Avrahami 206482655
package ShownObject;
import Collides.CollisionInfo;
import Collides.GameEnvironment;
import Default.GameLevel;
import Default.Velocity;
import Interface.Collidable;
import Interface.HitListener;
import Interface.Sprite;
import biuoop.DrawSurface;
import geometryShape.Line;
import geometryShape.Point;
import geometryShape.Rectangle;

import java.awt.Color;
import java.util.ArrayList;

/**
 * ball class - Balls have size (radius), color, and location (a Point), velocity and frames (two points).
 */
public class Ball implements Sprite {
    private int r;
    private final java.awt.Color color;
    private Point center;
    private Velocity v;
    private Point startFrame;
    private Point endFrame;
    private GameEnvironment gameEnvironment;
    private Line trajectory;
    private Line xLine;
    private Line yLine;
    private final ArrayList<HitListener> hitListeners;

    /**
     * constructor.
     * @param center - a center point of the ball
     * @param r      - the size of the ball
     * @param color  - the color of the ball
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * setting the axis of the radius of the ball.
     */
    private void setAxis() {
        xLine = new Line(this.center.getX() - this.r, this.center.getY(),
                this.center.getX() + this.r, this.center.getY());
        yLine = new Line(this.center.getX(), this.center.getY() - this.r,
                this.center.getX(), this.center.getY() + this.r);
    }

    /**
     * check If The Ball Into Block, and setting the center of the ball accordingly.
     */
    public void checkIfTheBallIntoBlock() {
        int index = 0;
        ArrayList<Collidable> colls = gameEnvironment.getCollidables();
        for (Collidable c : colls) {
            Rectangle rec = c.getCollisionRectangle();
            Point p1 = rec.getUpperLeft(); // upper left
            Point p2 = new Point(p1.getX() + rec.getWidth(), p1.getY() + rec.getHeight()); // down right
            if ((this.center.getX() > p1.getX()) && (this.center.getX() < p2.getX())) {
                if ((this.center.getY() > p1.getY()) && (this.center.getY() < p2.getY())) {
                    if (index == 0) { // up
                        this.center.setY(p2.getY() + r);
                    } else if (index == 1) { // left
                        this.center.setX(p2.getX() + r);
                    } else if (index == 2) { // right
                        this.center.setX(p1.getX() - r);
                    } else if (index == colls.size() - 1) { // paddle
                        this.v.changeDyDirection();
                        if (this.center.distance(p1) > this.center.distance(p2)) {
                            this.center.setX(p2.getX() + r);
                        } else {
                            this.center.setX(p1.getX() - r);
                        }
                    }
                }
            }
            index++;
        }
    }
    /**
     * @return - the x value of the ball's center point
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return - the y value of the ball's center point
     */
    public int getY() {
        return (int) this.center.getY();
    }
    /**
     * @return - the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * @param surface - helps to draw the ball
     */
    public void drawOn(DrawSurface surface) {
        if (this.startFrame == null) {
            this.startFrame = new Point(0, 0);
        }
        if (this.endFrame == null) {
            this.endFrame = new Point(surface.getWidth(), surface.getHeight());
        }
        check();
        surface.setColor(Color.black);
        surface.drawCircle(getX(), getY(), r);
        surface.setColor(color);
        surface.fillCircle(getX(), getY(), r);
    }

    /**
     * check function checking Cases where the ball values are incorrect and changes them accordingly.
     */
    public void check() {
        if (this.r < 0) { // if the size of the ball negative
            this.r = this.r * -1;
        }
        if (this.r > Math.max(this.endFrame.getX(), this.endFrame.getY()) / 2) { // if the size of the ball too big
            this.r = (int) Math.max(this.endFrame.getX(), this.endFrame.getY()) / 2;
        }
        if (this.r == 0) { // if the size of the ball zero
            this.r = this.r + 1;
        }
        if (this.center.getX() < this.startFrame.getX() + this.r) { // if the center point of the ball not in the range
            if (this.center.getX() < 0) {
                this.center.setX(this.startFrame.getX() + (-1 * this.center.getX()) + this.r);
            } else {
                this.center.setX(this.startFrame.getX() + this.r);
            }
        }
        if (this.center.getY() < this.startFrame.getY() + this.r) { // if the center point of the ball not in the range
            if (this.center.getY() < 0) {
                this.center.setY(this.startFrame.getY() + (-1 * this.center.getY()) + this.r);
            } else {
                this.center.setY(this.startFrame.getY() + this.r);
            }
        }
        if (this.center.getX() > endFrame.getX() - this.r) { // if the center point of the ball not in the range
            this.center.setX(this.endFrame.getX() - this.r);
        }
        if (this.center.getY() > endFrame.getY() - this.r) { // if the center point of the ball not in the range
            this.center.setY(this.endFrame.getY() - this.r);
        }
    }

    /**
     * @param v - get a velocity and setting this.v
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * @param dx - get the change in the coordinate of x
     * @param dy - get the change in the coordinate of y
     *           setting the velocity.
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * @return the velocity of this ball
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * setting the movement of the ball by the change in the coordinates of X and y.
     */
    public void moveOneStep() {
        this.checkIfTheBallIntoBlock();
        Point newPoint = this.getVelocity().applyToPoint(this.center);
        this.trajectory = new Line(this.center, newPoint);
        setAxis();
        CollisionInfo collidedTrajectroy = this.gameEnvironment.getClosestCollision(trajectory);
        CollisionInfo collidedxAxis = this.gameEnvironment.getClosestCollision(xLine);
        CollisionInfo colidedyAxis = this.gameEnvironment.getClosestCollision(yLine);
        if (collidedTrajectroy != null) { //collision!
            this.setVelocity(collidedTrajectroy.collisionObject().hit(this, collidedTrajectroy.collisionPoint(),
                    this.v));
            // moveOneStepWithoutAlmostCollision();
            this.center = this.getVelocity().applyToPoint(this.center);
        } else if (collidedxAxis != null) {
            this.setVelocity(collidedxAxis.collisionObject().hit(this, collidedxAxis.collisionPoint(), this.v));
            // moveOneStepWithoutAlmostCollision();
            this.center = this.getVelocity().applyToPoint(this.center);
            newPoint = new Point(this.center.getX() + this.getVelocity().getDx(),
                    this.center.getY() + this.getVelocity().getDy());
            this.trajectory = new Line(this.center, newPoint);
        } else if (colidedyAxis != null) {
            Velocity newV = colidedyAxis.collisionObject().hit(this, colidedyAxis.collisionPoint(), this.v);
            this.setVelocity(newV);
            // moveOneStepWithoutAlmostCollision();
            this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            //
            this.center = this.getVelocity().applyToPoint(this.center);
            newPoint = new Point(this.center.getX() + this.getVelocity().getDx(),
                    this.center.getY() + this.getVelocity().getDy());
            this.trajectory = new Line(this.center, newPoint);
        }
    }

    /**
     * setting the environment game.
     * @param gameEnvironment - our environment game.
     */
    public void setGameEnv(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }
    /**
     * takes a step.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }
    /**
     * add ball to the game.
     * @param game - our game.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * remove the ball from the game.
     * @param game - our game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}
