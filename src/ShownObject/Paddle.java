//  Refael Avrahami 206482655
package ShownObject;
import Default.GameLevel;
import Default.Velocity;
import Interface.Collidable;
import Interface.Sprite;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import geometryShape.Line;
import geometryShape.Point;
import geometryShape.Rectangle;

import java.awt.Color;

/**
 * The Paddle is the player in the game. It is a rectangle that is controlled by the arrow keys, and moves according
 * to the player key presses. It should implement the Sprite and the Collidable interfaces.
 */
public class Paddle implements Sprite, Collidable {
    private final biuoop.KeyboardSensor keyboard;
    private final Block block;
    private GUI gui;
    private static final int LEFT_LIM = 10;
    private static final int RIGHT_LIM = 790;
    private int speed = 5;
    private static final int AREA_FOUR_DEG = 30;
    private static final int AREA_FIVE_DEG = 60;
    private static final int AREA_ONE_DEG = 300;
    private static final int AREA_SEC_DEG = 330;

    /**
     * @param upperLeft - the upper-left point of the paddle.
     * @param width - the width of the paddle.
     * @param height - the height of the paddle.
     * @param color - the color of the paddle.
     * @param keyboard - KeyboardSensor interface from the biuoop class.
     */
    public Paddle(Point upperLeft, double width, double height, Color color, KeyboardSensor keyboard) {
        Rectangle rec = new Rectangle(upperLeft, width, height);
        this.block = new Block(rec, color);
        this.keyboard = keyboard;
    }
    /**
     * Move the paddle to the left.
     */
    public void moveLeft() {
        Point upperLeft = block.getCollisionRectangle().getUpperLeft();
        double upperLeftX = upperLeft.getX();
        upperLeft.setX(upperLeftX - this.speed);
        if (upperLeft.getX() <= LEFT_LIM) {
            upperLeft.setX(LEFT_LIM);
        }
    }
    /**
     * Move the paddle to the right.
     */
    public void moveRight() {
        Point upperLeft = this.block.getCollisionRectangle().getUpperLeft();
        double upperLeftX = upperLeft.getX();
        upperLeft.setX(upperLeftX + this.speed);
        if (upperLeft.getX() >= RIGHT_LIM - this.block.getCollisionRectangle().getWidth()) {
            upperLeft.setX(RIGHT_LIM - this.block.getCollisionRectangle().getWidth());
        }
    }

    /**
     * How does the Paddle move? its timePassed method should check if the "left" or "right" keys are pressed,
     * and if so move it accordingly.
     */
    public void timePassed() {
        if (keyboard.isPressed(keyboard.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(keyboard.RIGHT_KEY)) {
            moveRight();
        }
    }
    /**
     * Draws the block on the surface.
     *
     * @param d - surface on which to draw.
     */
    public void drawOn(DrawSurface d) {
        block.drawOn(d);
    }

    /**
     * @return the collision rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.block.getCollisionRectangle();
    }

    /**
     * @param hitter - the hitting ball.
     * @param collisionPoint  - collision point where there was a hit of block or paddle.
     * @param currentVelocity - velocity before the collision.
     * @return the new velocity after the collision.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Point upperLeftPaddle = this.block.getCollisionRectangle().getUpperLeft();
        Point upperRightPaddle = new Point(upperLeftPaddle.getX()
                + this.block.getCollisionRectangle().getWidth(), upperLeftPaddle.getY());
        Point downerLeftPaddle = new Point(upperLeftPaddle.getX(), upperLeftPaddle.getY()
                + this.block.getCollisionRectangle().getHeight());
        Point downerRightPaddle = new Point(upperLeftPaddle.getX() + this.block.getCollisionRectangle().getWidth(),
                upperLeftPaddle.getY() + upperLeftPaddle.getY() + this.block.getCollisionRectangle().getHeight());
        double speed = Math.sqrt((currentVelocity.getDx() * currentVelocity.getDx()) + (currentVelocity.getDy()
                * currentVelocity.getDy()));
        double areaWidth = this.block.getCollisionRectangle().getWidth() / 5;
        Line area1 = new Line(upperLeftPaddle.getX(), upperLeftPaddle.getY(), upperLeftPaddle.getX() + areaWidth,
                upperLeftPaddle.getY());
        Line area2 = new Line(upperLeftPaddle.getX() + areaWidth, upperLeftPaddle.getY(), upperLeftPaddle.getX()
                + (2 * areaWidth) - 1,
                upperLeftPaddle.getY());
        Line area3 = new Line(upperLeftPaddle.getX() + (2 * areaWidth), upperLeftPaddle.getY(), upperLeftPaddle.getX()
                + (3 * areaWidth) - 1,
                upperLeftPaddle.getY());
        Line area4 = new Line(upperLeftPaddle.getX() + (3 * areaWidth), upperLeftPaddle.getY(), upperLeftPaddle.getX()
                + (4 * areaWidth) - 1,
                upperLeftPaddle.getY());
        Line area5 = new Line(upperLeftPaddle.getX() + (4 * areaWidth), upperLeftPaddle.getY(), upperLeftPaddle.getX()
                + (5 * areaWidth), upperLeftPaddle.getY());
        Line leftLinePaddle = new Line(upperLeftPaddle, downerLeftPaddle);
        Line rightLinePaddle = new Line(upperRightPaddle, downerRightPaddle);
        if (area1.isPointOnTheLine(collisionPoint)) {
           // System.out.println("area1");
            currentVelocity = Velocity.fromAngleAndSpeed(AREA_ONE_DEG, speed);
            return currentVelocity;
        } else if (area2.isPointOnTheLine(collisionPoint)) {
         //   System.out.println("area2");
            currentVelocity = Velocity.fromAngleAndSpeed(AREA_SEC_DEG, speed);
            return currentVelocity;
        } else if (area3.isPointOnTheLine(collisionPoint)) {
        //    System.out.println("area3");
            currentVelocity.changeDyDirection();
            return currentVelocity;
        } else if (area4.isPointOnTheLine(collisionPoint)) {
         //   System.out.println("area4");
            currentVelocity = Velocity.fromAngleAndSpeed(AREA_FOUR_DEG, speed);
            return currentVelocity;
        } else if (area5.isPointOnTheLine(collisionPoint)) {
       //     System.out.println("area5");
            currentVelocity = Velocity.fromAngleAndSpeed(AREA_FIVE_DEG, speed);
            return currentVelocity;
        }
            // If there is a collision on the left or right side of the tray the speed will change on the X axis
        if (leftLinePaddle.isPointOnTheLine(collisionPoint) || rightLinePaddle.isPointOnTheLine(collisionPoint)) {
           // System.out.println("left paddle");
            currentVelocity.changeDxDirection();
            return currentVelocity;
        }
        return currentVelocity;
    }
    /**
     * the function add the collidables and the sprite to the game.
     * @param g - is the object that the collidables and the sprite add to it
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
        this.gui = g.getGui();
    }

    /**
     * @param v - setting to this velocity.
     */
    public void setSpeed(int v) {
        this.speed = v;
    }

}